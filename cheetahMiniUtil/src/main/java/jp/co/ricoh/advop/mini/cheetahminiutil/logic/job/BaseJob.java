package jp.co.ricoh.advop.mini.cheetahminiutil.logic.job;

import android.os.AsyncTask;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.CHolder;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.EXE_RESULT;
import jp.co.ricoh.advop.mini.cheetahminiutil.logic.JobManager.ExeCallback;

public abstract class BaseJob {

    protected ExeCallback exeCallback = null;
    protected JobData jobData;
    protected boolean enableWait = false;
    protected boolean enablePreview = false;
    protected int count = 0;
    protected String jobId;

    public BaseJob() {
        jobData = CHolder.instance().getJobData();
    }

    public BaseJob(ExeCallback exeCallback) {
        this();
        this.exeCallback = exeCallback;
    }

    public boolean start() {
        if (jobData.getCurrentJob() != null) {
            LogC.d("start job fail");
            return false;
        }
        jobData.setCurrentJob(this);

        new AsyncTask<Void, Void, EXE_RESULT>() {
            @Override
            protected EXE_RESULT doInBackground(Void... params) {
                // lock 
                if (!CHolder.instance().getLockManager().lock()) {
                    LogC.d("lock fail, can not start!");
                    return EXE_RESULT.FAILED;
                }
                return EXE_RESULT.SUCCESSED;
            }

            @Override
            protected void onPostExecute(EXE_RESULT ret) {
                if (ret == EXE_RESULT.SUCCESSED) {
                    onStart();
                } else {
                    complete(ret);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);//.execute();

        return true;
    }

    abstract public void onStart();

    public void complete(EXE_RESULT ret) {
        onComplete(ret);
        jobData.setCurrentJob(null);
        CHolder.instance().getLockManager().unlock();

//        CHolder.instance().restartAppWhenJobEnd();
    }

    public void onComplete(EXE_RESULT ret) {
        if (exeCallback != null) {
            exeCallback.onComplete(ret);
        }
    }

    public static void forceComplete(EXE_RESULT ret) {
        BaseJob job = CHolder.instance().getJobData().getCurrentJob();
        if (job != null) {
            job.complete(ret);
        }
    }

    public boolean isEnableWait() {
        return enableWait;
    }

    public void setEnableWait(boolean enableWait) {
        this.enableWait = enableWait;
    }

    public boolean isEnablePreview() {
        return enablePreview;
    }

    public void setEnablePreview(boolean enablePreview) {
        this.enablePreview = enablePreview;
    }

    public int getCount() {
        return Math.max(count, CHolder.instance().getScanManager().getScannedPages());
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getJobId() {
        return CHolder.instance().getScanManager().getScanJob().getCurrentJobId();
    }
}
