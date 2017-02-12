package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.os.Handler;
import android.os.Looper;

/**
 * Copyright (C) 2013-2016 RICOH Co.,LTD
 * All rights reserved
 */

public abstract class CancelThread<A, B, C> extends Thread {
    private A[] a;

    Handler handler = new Handler(Looper.getMainLooper());
    private Thread thread;

    @Override
    public synchronized void start() {
        super.start();
    }

    public void execute(A... parms) {
        this.a = parms;
        onPreExecute();
        start();
    }

    @Override
    public void run() {
        super.run();
        thread = currentThread();
        final C c = doInBackground(a);
        handler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute(c);
            }
        });
    }

    protected abstract C doInBackground(A... params);

    protected void onPostExecute(C aBoolean) {

    }

    protected void onPreExecute() {

    }
    public void cancel(){
        thread.interrupt();
    }
    public boolean cancel(boolean b){
        thread.interrupt();
//        try {
//            thread.destroy();
//        }catch (UnsupportedOperationException e){
//
//        }
        return thread.isInterrupted();
    }

    public boolean isCancelled() {
        return isInterrupted();
    }
}
