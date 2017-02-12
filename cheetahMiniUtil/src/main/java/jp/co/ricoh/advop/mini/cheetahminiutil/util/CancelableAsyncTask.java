package jp.co.ricoh.advop.mini.cheetahminiutil.util;

import android.os.AsyncTask;

public abstract class CancelableAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements Cancelable {

    public final AsyncTask<Params, Progress, Result> executeAsync(Params... params) {
        return executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
    }
}
