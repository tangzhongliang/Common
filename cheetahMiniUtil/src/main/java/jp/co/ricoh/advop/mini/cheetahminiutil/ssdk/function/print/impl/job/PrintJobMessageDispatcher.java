/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.job;

import static jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobState.CANCELED;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.CUtil;
import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.attribute.Attribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintFile;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.PrintUserCode;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.HashPrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintAttributeException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintJobAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttribute;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintRequestAttributeSet;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobPrintingInfo;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobState;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobStateReasons;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.standard.PrintJobUserName;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service.AsyncJobEventHandler;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service.FunctionMessageDispatcher;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.EmptyResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ErrorResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.CreateJobRequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.CreateJobResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetJobStatusResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Job;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Printer;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.UpdateJobStatusRequestBody;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * プリントジョブ処理を行うクラスです。
 * The class to manage print jobs
 */
public class PrintJobMessageDispatcher implements AsyncJobEventHandler {

    private static final String TAG = PrintJobMessageDispatcher.class.getSimpleName();

    private static final String EXCLUSIVE_JOB_SETTING_KEY = "error.exclusive_job_settings";

    private JobListener mJobListener;
    private Printer mPrinter;
    private Job mJob;
    private String mProductId;

    private final LinkedBlockingQueue<GetJobStatusResponseBody> mJobEventQueue;
    private JobEventDispatcher mJobEventDispatcher;

    public PrintJobMessageDispatcher() {
        mPrinter = new Printer();
        mJob = null;
        mProductId = SmartSDKApplication.getProductId();
        mJobEventQueue = new LinkedBlockingQueue<GetJobStatusResponseBody>();
        mJobEventDispatcher = null;
    }


    public void setPrintJobListener(JobListener listener) {
        this.mJobListener = listener;
    }

    /**********************************************************
     * 上位層からの要求
     * Request from the upper layer
     **********************************************************/

    /**
     * プリントジョブ開始要求を投げます。
     * Throws the request to start the print job.
     *
     * @param attributes プリント要求属性セット
     *                   Print request attribute set
     * @throws PrintException
     */
    public boolean requestStartPrintJob(PrintFile printFile, PrintRequestAttributeSet attributes, PrintUserCode userCode) throws PrintException {
        if(this.mJob != null) {
            throw new PrintException("Can not start print(), because running print process.");
        }

        //SubscribedIdの発行とジョブの非同期イベントを登録する
        // Issues an subscribed ID and registers the asynchronous job event
        String subscribedId = FunctionMessageDispatcher.getInstance().addAsyncJobEventListener(this);
        if(subscribedId == null) {
            FunctionMessageDispatcher.getInstance().removeAsyncJobEventListener(this);
            throw new PrintException("Can not start print(), because SDKService doesn't return a response");
        }

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_SUBSCRIPTION_ID, subscribedId);
        header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

        CreateJobRequestBody body = getCreateJobRequestBody(printFile, userCode, attributes);
        body.setValidateOnly(false);

        Request req = new Request();
        req.setBody(body);
        req.setHeader(header);

        try {
            Response<CreateJobResponseBody> resp = mPrinter.createJob(req);
            LogC.d(getClass().getSimpleName(), "resp[" + resp.getBody() + "]");
            LogC.d(getClass().getSimpleName(), "respStatus[" + resp.getStatusCode() + "]");
            if(resp.getResponse().getStatusCode() == HttpStatus.SC_CREATED) {
                String jobId = resp.getBody().getJobId();

                mJobListener.setJobId(jobId);
                mJob = new Job(jobId);
                startJobEventDispatcher(jobId);

                return true;
            }

        } catch (IOException e) {
            FunctionMessageDispatcher.getInstance().removeAsyncJobEventListener(this);
            throw new PrintException(new IOException(e.getMessage()));

        } catch (InvalidResponseException e) {
            FunctionMessageDispatcher.getInstance().removeAsyncJobEventListener(this);

            if( e.getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                if(e.hasBody()) {
                    for(ErrorResponseBody.Errors errors : e.getBody().getErrors()) {
                        if(EXCLUSIVE_JOB_SETTING_KEY.equals(errors.getMessageId())) {
                            throw new PrintAttributeException(errors.getMessage());
                        }
                    }
                }
            }
            throw new PrintResponseException(e);
        }

        FunctionMessageDispatcher.getInstance().removeAsyncJobEventListener(this);
        return false;
    }

    /**
     * プリントジョブ検証を投げます。
     * Throws print job validation
     *
     * @param attributes プリント要求属性セット
     *                   Print request attribute set
     */
    public boolean verifyPrintJob(PrintFile printFile, PrintRequestAttributeSet attributes, PrintUserCode userCode)
            throws PrintException {
        CreateJobRequestBody body = getCreateJobRequestBody(printFile,userCode,attributes);
        body.setValidateOnly(true);

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request req = new Request();
        req.setBody(body);
        req.setHeader(header);

        try {
            Response<CreateJobResponseBody> resp =  mPrinter.createJob(req);
            return (resp.getResponse().getStatusCode() == HttpStatus.SC_OK);

        } catch (IOException e) {
            throw new PrintException(e.getMessage());

        } catch (InvalidResponseException e) {
            if(e.getStatusCode() == HttpStatus.SC_BAD_REQUEST) {
                ErrorResponseBody.ErrorsArray errorsArray = e.getBody().getErrors();
                String exclusiveJobSettingValue = null;
                if(errorsArray != null){
                    for(ErrorResponseBody.Errors errors : errorsArray) {
                        if(EXCLUSIVE_JOB_SETTING_KEY.equals(errors.getMessageId())) {
                            exclusiveJobSettingValue = errors.getMessage();
                        }
                    }
                }
                if(exclusiveJobSettingValue != null){
                    throw new PrintAttributeException(exclusiveJobSettingValue);
                }
            }
            throw new PrintResponseException(e);
        }
    }

    /**
     * プリントジョブ中止イベントを投げます。
     * Throws print job cancel event
     */
    public boolean requestCancelPrintJob(PrintUserCode userCode) throws PrintException {
        if(this.mJob == null) throw new PrintException("Can not cancel(), because print is not running");

        UpdateJobStatusRequestBody body = new UpdateJobStatusRequestBody();
        body.setJobStatus(CANCELED.toString());
        if(userCode != null && !CUtil.isStringEmpty(userCode.getUserCode())) {
            body.setUserCode(userCode.getUserCode());
        }

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request req = new Request();
        req.setBody(body);
        req.setHeader(header);

        try {
            Response<EmptyResponseBody> resp = mJob.updateJobStatus(req);
            return (resp.getResponse().getStatusCode() == HttpStatus.SC_ACCEPTED);
        } catch (IOException e) {
            throw new PrintException(e);
        } catch (InvalidResponseException e) {
            throw new PrintResponseException(e);
        }
    }

    /**
     * ジョブ情報を取得します。
     * Obtains job information
     *
     * @return ジョブ状態
     *         取得に失敗もしくは、内部状態として実行できない場合は空のセットが返ります。
     *         Job state.
     *         If failing to obtain information or cannot be executed as internal state,
     *         an empty set is returned.
     */
    public PrintJobAttributeSet requestJobStates(PrintUserCode userCode) {
        if(this.mJob == null) {
            return new HashPrintJobAttributeSet();
        }

        RequestQuery query = new RequestQuery();
        if(userCode != null)
            query.put("userCode", userCode.getUserCode());

        RequestHeader header = new RequestHeader();
        header.put(RequestHeader.KEY_X_APPLICATION_ID, mProductId);

        Request request = new Request();
        request.setQuery(query);
        request.setHeader(header);

        try {
            Response<GetJobStatusResponseBody> resp = mJob.getJobStatus(request);
            LogC.d(getClass().getSimpleName(), "resp[" + resp.getBody() + "]");
            return jobStatusResponseToAttribute(resp.getBody());
        } catch (IOException e) {
            LogC.w(e);
        } catch (InvalidResponseException e) {
            LogC.w(e);
        }

        return new HashPrintJobAttributeSet();
    }

    /**
     * ジョブを破棄します。
     * ジョブが終了した際に、ジョブに関するオブジェクトを破棄します。
     * Discards the job
     * The objects related to the job are discarded at the time the job ends.
     */
    public void destroyJob() {
        FunctionMessageDispatcher.getInstance().removeAsyncJobEventListener(this);
        finishJobEventDispatcher();
        mJobEventQueue.clear();
        this.mJobListener = null;
        this.mPrinter = null;
        this.mJob = null;
    }

    /**********************************************************
     * Private Method
     **********************************************************/

    /**
     * RESTジョブ情報からPrintJobAttributeSetを構築します。
     * Builds PrintJobAttributeSet from REST job information.
     *
     * @param response RESTジョブ情報取得結果
     *                 REST job information obtained result
     * @return PrintJobAttributeのセット
     *         PrintJobAttribute set
     */
    private PrintJobAttributeSet jobStatusResponseToAttribute(GetJobStatusResponseBody response) {
        PrintJobAttributeSet jobAttributes = new HashPrintJobAttributeSet();
        if(response != null) {
            putTo(jobAttributes, PrintJobState.fromString(response.getJobStatus()));
            putTo(jobAttributes, PrintJobStateReasons.getInstance(response.getJobStatusReasons()));
            putTo(jobAttributes, PrintJobPrintingInfo.getInstance(response.getPrintingInfo()));
            putTo(jobAttributes, new PrintJobName(response.getJobName()));
            putTo(jobAttributes, new PrintJobUserName(response.getUserName()));
        }
        return jobAttributes;
    }

    /**
     * attributeSetに属性を追加します。attributeがnullの場合は登録されません。
     * Adds the attribute to attributeSet. If the attribute is null, the attribute is not registered.
     *
     * @param attributeSet 追加属性を格納する属性セット
     *                     Attribute set to store additional attribute
     * @param attribute 追加する属性
     *                  Attribute to add
     */
    private void putTo(PrintJobAttributeSet attributeSet, PrintJobAttribute attribute) {
        if(attribute != null) {
            attributeSet.add(attribute);
        }
    }

    /**
     * 指定したジョブ要求属性から、RESTアクセスする際のリクエストを生成します。
     * Creates the request for accessing REST from the specified job request attribute.
     */
    private CreateJobRequestBody getCreateJobRequestBody(PrintFile printFile, PrintUserCode userCode, PrintRequestAttributeSet attributeSet) {
        CreateJobRequestBody body = new CreateJobRequestBody();

        if(printFile.getFileId() != null) {
            body.setFileId(printFile.getFileId());
        }
        if(printFile.getFilePath() != null) {
            body.setFilePath(printFile.getFilePath());
        }
        if(printFile.getPDL() != null)  {
            body.setPdl(printFile.getPDL().toString());
        }
        if(userCode != null && !CUtil.isStringEmpty(userCode.getUserCode())) {
            body.setUserCode(userCode.getUserCode());
        }

        if(attributeSet == null) {
            return body;
        }
        //属性設定
        for(Attribute attribute : attributeSet.getCollection()) {
            body.getJobSetting().setValue(attribute.getName(), ((PrintRequestAttribute)attribute).getValue());
        }

        return body;
    }


    /**********************************************************
     * SDKサービスからの非同期イベント発行(FunctionMessageDispatcher経由)
     * Issue asynchronous event from SDK service (via FunctionMessageDispatcher)
     **********************************************************/

    @Override
    public void onReceiveJobEvent(GetJobStatusResponseBody event) {
        try {
            mJobEventQueue.put(event);
        } catch (InterruptedException e) {
            LogC.w(e);
        }
    }

    @Override
    public String getJobId() {
        if(this.mJobListener == null) return null;
        return this.mJobListener.getJobId();
    }

    /**
     * ジョブイベント配信を開始します。
     * Starts job event delivery.
     *
     * @param jobId
     * @throws NullPointerException jobId がnullの場合
     *                              When jobId is null
     */
    private void startJobEventDispatcher(String jobId) {
        finishJobEventDispatcher();
        mJobEventDispatcher = new JobEventDispatcher(jobId);
        mJobEventDispatcher.start();
    }

    /**
     * ジョブイベント配信を終了します。
     * Ends job event delivery.
     */
    private void finishJobEventDispatcher() {
        if (mJobEventDispatcher != null) {
            mJobEventDispatcher.cancel();
            mJobEventDispatcher = null;
        }
    }

    /**
     * ジョブイベントを配信するスレッドクラスです。
     * ジョブ開始要求の応答レスポンスが返ってくる前に受信したジョブイベントをキューイングしておき、
     * ジョブ開始確定後に、キューイングしたイベントのリスナ通知を開始します。
     * The thread class to deliver job events.
     * Queues the job event received before the response for job start request is returned,
     * and starts listener notification of the queued event after the job start is confirmed.
     */
    private class JobEventDispatcher extends Thread {

        private final String mJobId;

        private volatile boolean mCanceled = false;

        JobEventDispatcher(String jobId) {
            if (jobId == null) {
                throw new NullPointerException("jobId must not be null.");
            }
            mJobId = jobId;
        }

        @Override
        public void run() {
            LogC.d(TAG, "print job event dispatcher start (" + mJobId + ")");

            while (!mCanceled) {
                GetJobStatusResponseBody event;
                try {
                    event = mJobEventQueue.take();

                    // only own jobId
                    if (!mJobId.equals(event.getJobId())) {
                        continue;
                    }

                    if(mJobListener != null) {
                        mJobListener.onChangeJobStatus(jobStatusResponseToAttribute(event));
                    }

                } catch (InterruptedException ignore) {
                }
            }

            LogC.d(TAG, "print job event dispatcher finish (" + mJobId + ")");
        }

        void cancel() {
            mCanceled = true;
            interrupt();
        }

    }

}
