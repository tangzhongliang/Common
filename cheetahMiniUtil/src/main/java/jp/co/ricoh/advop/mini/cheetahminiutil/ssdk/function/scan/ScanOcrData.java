/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.GetOcrTextResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Job;

import java.io.IOException;

/**
 * OCRデータを操作するための機能を提供します。
 * Provides the function to operate OCR data.
 */
public class ScanOcrData {

    private Job mJob = null;

    /**
     * ジョブに紐付くOCRデータオブジェクトを生成します。
     * ジョブは正常に終了した場合だけ、画像操作を行えます。
     * 正常に終了していない場合(ジョブ実行中/ジョブ異常終了など)は、本オブジェクトメソッドを実行しても
     * 操作に失敗します。
     * Creates the OCR data object associated with the job.
     * Image can be operated only when the job ends successfully.
     * If the job does not end correctly (e.g. job processing, job ended with error), the operation fails
     * even if this object method is executed.
     *
     * @param job
     */
    public ScanOcrData(ScanJob job){
        this.mJob = new Job(job.getCurrentJobId());
    }

    /**
     * OCRデータのテキストを取得します。
     * 取得に失敗した場合は、nullが返ります。
     * Obtains OCR data text.
     * If failing to obtain the text, null is returned.
     *
     * @param pageNo 取得するページ番号
     *               Page number to obtain
     * @return OCRテキストデータ
     *         Ocr text data
     */
    public GetOcrTextResponseBody getOcrDataText(int pageNo) {
        RequestQuery query = new RequestQuery();
        query.put("pageNo", Integer.toString(pageNo));
        query.put("format", "text");
        query.put("getMethod", "direct");

        Request request = new Request();
        request.setQuery(query);

        try{
            Response<GetOcrTextResponseBody> response = this.mJob.getOcrText(request);
            return response.getBody();
        } catch (InvalidResponseException e) {
            LogC.w(e);
        } catch (IOException e) {
            LogC.w(e);
        }

        return null;
    }

}
