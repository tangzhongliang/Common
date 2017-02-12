/*
 *  Copyright (C) 2013-2014 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.scan;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.BinaryResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestQuery;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.FilePathResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner.Job;

import java.io.IOException;
import java.io.InputStream;

/**
 * サムネイルを操作する機能を提供します
 * Provides the functions to operate thumbnails.
 */
public class ScanThumbnail {

    private Job mJob = null;

    /**
     * ジョブに紐付くサムネイル操作オブジェクトを生成します。
     * ジョブ発行後の場合だけ、生成することが可能です。
     * また、サムネイルが操作できるタイミング（ジョブの停止中)の場合のみ、本クラスのメソッドを操作できます
     * それ以外の状態で実行した場合は、メソッドが失敗となり期待した動作となりません。
     * Creates the thumbnail operation object associated with the job.
     * The object can be created only after the job is issued.
     * A method of this class can be operated only at the time the thumbnail can be operated
     * (while the job is being paused).
     * If running the method for any other states, the method fails and does not behave as expected.
     *
     * @param job
     */
    public ScanThumbnail(ScanJob job) {
        mJob = new Job(job.getCurrentJobId());
    }

    /**
     * サムネイルを取得します。
     * 本APIはMultiLink-Panelでのみ動作します。
     * 取得するパスは絶対パスで取得できます。
     * 取得に失敗した場合は、nullが返ります。
     * Obtains the thumbnail.
     * This API runs only on MultiLink-Panel.
     * The path can be obtained in absolute path.
     * If failing to obtain the path, null is returned.
     *
     * @param pageNo 取得対象のページ番号
     *               Page number to obtain
     * @return サムネイルの絶対パス
     *         Absolute path of the thumbnail.
     */
    public String getThumbnailFilePath(int pageNo) {
        Response<FilePathResponseBody> response = getThumbnailFilePathResponse(pageNo);
        if (response != null) {
            return response.getBody().getFilePath();
        }
        return null;
    }

    public Response<FilePathResponseBody> getThumbnailFilePathResponse(int pageNo) {
        RequestQuery query = new RequestQuery();
        query.put("pageNo", Integer.toString(pageNo));
        query.put("getMethod", "filePath");

        Request request = new Request();
        request.setQuery(query);

        try{
            Response<FilePathResponseBody> response = mJob.getThumbnailPath(request);
            return response;
        } catch (InvalidResponseException e) {
            LogC.w(e);
        } catch (IOException e) {
            LogC.w(e);
        }

        return null;
    }

    /**
     * ページ単位でサムネイル画像をインプットストリームとして取得します。
     * 取得に失敗した場合は、nullが返ります。
     * Obtains the thumbnail images as input stream by page.
     * If failing to obtain the images, null is returned.
     *
     * @param  pageNo 取得するページ番号
     *                Page number to obtain
     * @return サムネイル画像のインプットストリーム
     *         Thumbnail image input stream
     */
    public InputStream getThumbnailInputStream(int pageNo) {
        Response<BinaryResponseBody> response = getThumbnailBinaryResponse(pageNo);
        if (response != null) {
            return response.getBody().getInputStream();
        }
        return null;
    }

    public Response<BinaryResponseBody> getThumbnailBinaryResponse(int pageNo) {
        RequestQuery query = new RequestQuery();
        query.put("pageNo", Integer.toString(pageNo));
        query.put("getMethod", "direct");
        Request request = new Request();
        request.setQuery(query);

        try{
            Response<BinaryResponseBody> response = mJob.getThumbnail(request);
            return response;
        } catch (InvalidResponseException e) {
            LogC.w(e);
        } catch (IOException e) {
            LogC.w(e);
        }

        return null;
    }

}
