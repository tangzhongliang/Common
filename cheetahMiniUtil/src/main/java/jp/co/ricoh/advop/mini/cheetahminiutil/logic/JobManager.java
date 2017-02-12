package jp.co.ricoh.advop.mini.cheetahminiutil.logic;

import jp.co.ricoh.advop.mini.cheetahminiutil.R;
import jp.co.ricoh.advop.mini.cheetahminiutil.application.BaseApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobScan;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.job.JobScanMulti;
import jp.co.ricoh.advop.mini.cheetahminiutil.print.PrintManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanManager;
import jp.co.ricoh.advop.mini.cheetahminiutil.scan.ScanStateMachine.ScanEvent;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.event.PrintJobListener;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan.attribute.ScanRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client.BasicRestContext;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property.DeviceProperty;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.property.GetDeviceInfoResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Cancelable;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CancelableAsyncTask;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.Const;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.HDDUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.ToastUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// all public functions should be run in UI thread
public class JobManager {
    //    private final static String TAG = JobManager.class.getSimpleName();
    private BaseApplication application;
    private ScanManager scanManager;
    private PrintManager printManager;
    private boolean isNorthAmerica;

    private CancelableAsyncTask<Void, Void, EXE_RESULT> initAppTask = null;

    public enum EXE_RESULT {
        CANCELED,
        SUCCESSED,
        FAILED,

        ERR_HDD_NOT_AVAILABLE,
        ERR_LOGIN_USER_FAILED,
        ERR_SCAN,
        ERR_GET_SCANNED_FILE,
        ERR_PRINT,
        ERR_INIT,
    }

    public interface ExeCallback {
        public void onComplete(EXE_RESULT ret);
    }

    public interface ExeCallbackScan extends ExeCallback {
        public void onGetFilePath(String path, int imgRotate);
    }

    public interface ExeCallbackScanMulti extends ExeCallback {
        public class ScanedFileInfo {
            private String path;
            private int rotate;

            public ScanedFileInfo(String path, int rotate) {
                this.path = path;
                this.rotate = rotate;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getRotate() {
                return rotate;
            }

            public void setRotate(int rotate) {
                this.rotate = rotate;
            }
        }

        public void onGetFilePath(List<ScanedFileInfo> fileInfos);
    }

    public interface ExeCallbackMediaState extends ExeCallback {
        public void onGetMediaState(ArrayList pathList);
    }

    public JobManager() {
        application = CHolder.instance().getApplication();
    }

    public void cancelInitTask() {
        if (initAppTask != null && initAppTask.getStatus() == Status.RUNNING) {
            initAppTask.cheetahCancel();
        }
    }

    // should be canceled when onDestroy
    public Cancelable initApp(final ExeCallback callback) {
        scanManager = CHolder.instance().getScanManager();
        printManager = CHolder.instance().getPrintManager();
        scanManager.getStateMachine().procScanEvent(ScanEvent.ACTIVITY_CREATED);
//        printManager.getStateMachine().procPrintEvent(PrintEvent.CHANGE_APP_ACTIVITY_INITIAL);
        CHolder.instance().getJobData().setCurrentJob(null);
        CHolder.instance().getLockManager().unlock();

        initAppTask = new CancelableAsyncTask<Void, Void, EXE_RESULT>() {
            @Override
            protected EXE_RESULT doInBackground(Void... params) {
                EXE_RESULT ret = EXE_RESULT.SUCCESSED;


                // init scan service
                if (CHolder.instance().getInitParameters().isScanAvailable()) {
                    LogC.d("intiScanService");
                    ret = scanManager.intiScanService(this);
                    if (ret != EXE_RESULT.SUCCESSED) {
                        return ret;
                    }
                }

                //init mediaState
                if (CHolder.instance().getInitParameters().isStorageAvailable()) {
                    MediaEventReceiver.getInstance().mediaInfos = application.getMediaState();
                }

                // init Print service
                if (CHolder.instance().getInitParameters().isPrintAvailable()) {
                    LogC.d("intiPrintService");
                    ret = printManager.intiPrintService(this);
                    if (ret != EXE_RESULT.SUCCESSED) {
                        return ret;
                    }
                }

                // wait hdd
                LogC.d("waitHDDStart");
                if (!application.waitHDDStart()) {
                    return EXE_RESULT.ERR_HDD_NOT_AVAILABLE;
                }

                //setMFPIsNA();
                LogC.d("initLoginUserInfo");
                if (!application.initLoginUserInfo()) {
                    return EXE_RESULT.ERR_LOGIN_USER_FAILED;
                }

                LogC.d("init folder");
                //init folder
                File tmpFile = new File(Const.PATH_TMP_FOLDER);
                if (tmpFile.exists()) {
                    HDDUtil.deleteFile(Const.PATH_TMP_FOLDER);
                }
                if (!tmpFile.exists()) {
                    tmpFile.mkdirs();
                }

                //init imageLoad
                if (CHolder.instance().getInitParameters().isLoadImageAvailable()) {
                    File cacheDir = new File(Const.PATH_PACKAGE_FOLDER + "tmp/cache");
                    ImageLoaderConfiguration config = new ImageLoaderConfiguration
                            .Builder(application)
                            .memoryCacheExtraOptions(500, 500)
                            .threadPoolSize(1)//checkList 要求cpu占用率60%以下
                            .threadPriority(Thread.NORM_PRIORITY - 2)
                            .denyCacheImageMultipleSizesInMemory()
                            .memoryCache(new UsingFreqLimitedMemoryCache(5 * 1024 * 1024))
                            .memoryCacheSize(5 * 1024 * 1024)
                            .diskCacheSize(50 * 1024 * 1024)
                            .tasksProcessingOrder(QueueProcessingType.LIFO)
                            .diskCacheFileCount(100)
                            .diskCache(new UnlimitedDiskCache(cacheDir))
                            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                            .imageDownloader(new BaseImageDownloader(application, 5 * 1000, 7 * 1000))
                            .writeDebugLogs()
                            .build();
                    ImageLoader.getInstance().init(config);
                }

                return ret;
            }

            @Override
            protected void onPostExecute(EXE_RESULT ret) {
                if (isCancelled()) {
                    return;
                }
                if (!ret.equals(EXE_RESULT.SUCCESSED)) {
                    ToastUtil.showToast(application, R.string.error_failed_to_start, ToastUtil.ToastLengthType.TYPE_LONG);
                    CHolder.instance().getActivity().finish();
                    return;
                }
                callback.onComplete(ret);
            }

            @Override
            public void cheetahCancel() {
                this.cancel(false);
            }
        };
        initAppTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        return initAppTask;
    }

    public boolean isNorthAmerica() {
        return isNorthAmerica;
    }

    private void setMFPIsNA() {
        isNorthAmerica = false;

        final RequestHeader reqHeader = new RequestHeader();
        reqHeader.put(RequestHeader.KEY_X_APPLICATION_ID, application.getProductId());
        // 1-2
        final Request request = new Request();
        // 1-3
        request.setHeader(reqHeader);

        // 2
        // 2-1
        final BasicRestContext context = new BasicRestContext();
        // 2-2
        final DeviceProperty deviceProperty = new DeviceProperty(context);
        Response<GetDeviceInfoResponseBody> response = null;
        // 2-3
        try {
            response = deviceProperty.getDeviceInfo(request);
            final GetDeviceInfoResponseBody body = response.getBody();
            String dest = body.getDeviceDescription().getDestination();
            if (dest.equals("north_america")) {
                isNorthAmerica = true;
            }
        } catch (InvalidResponseException ire) {
            LogC.e("get MFP status failed!", ire);
        } catch (IOException ioe) {
            LogC.e("get MFP status failed!", ioe);
        } catch (Exception e) {
            LogC.e("get MFP status failed!", e);
        }
    }

    public void destroyApp() {
        scanManager.destroyScanService();
        printManager.destroyPrintService();
        HDDUtil.deleteFile(Const.PATH_TMP_FOLDER);
    }

    public void startScan(ExeCallbackScan exeCallback, ScanRequestAttributeSet scanRequestAttributeSet) {
        scanManager.setScanRequestAttributeSet(scanRequestAttributeSet);
        new JobScan(exeCallback).start();
    }

    public void startScanMulti(ExeCallbackScanMulti exeCallback, ScanRequestAttributeSet scanRequestAttributeSet) {
        scanManager.setScanRequestAttributeSet(scanRequestAttributeSet);
        new JobScanMulti(exeCallback).start();
    }

    public void sendPrintJob(ExeCallback exeCallback, PrintRequestAttributeSet printConditions, String path) {
        sendPrintJob(exeCallback, printConditions, path, null);
    }

    public void sendPrintJob(ExeCallback exeCallback, PrintRequestAttributeSet printConditions, String path, PrintJobListener listener) {
        printManager.setPrintJobListener(listener);
        printManager.startPrint(exeCallback, printConditions, path);

    }

    public EXE_RESULT sendPrintJobSync(PrintRequestAttributeSet printConditions, String path) {
        return printManager.startPrintSync(printConditions, path);
    }

}
