/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.impl.service;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.GetJobStatusResponseBody;

/**
 * ジョブごとの非同期通信用リスナーです。
 * Listener for asynchronous communication by job
 */
public interface AsyncJobEventHandler {

    void onReceiveJobEvent(GetJobStatusResponseBody event);

    String getJobId();
}
