/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.common.SmartSDKApplication;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.function.print.attribute.PrintResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.InvalidResponseException;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Request;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Response;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.CreatePrintFileRequestBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.CreatePrintFileResponseBody;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer.Printer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 印刷対象ファイルを示すクラスです。
 * This class indicates the print file.
 */
public class PrintFile {

    private String mFileId;
    private String mFilePath;
    private PDL mPDL;

    private PrintFile(String filePath, String fileId,  PDL pdl) {
        this.mFilePath = filePath;
        this.mFileId = fileId;
        this.mPDL = pdl;
    }

    public String getFileId() {
        return mFileId;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public PDL getPDL() {
        return mPDL;
    }

    public static class Builder {
        private String printerFilePath;
        private InputStream localFileInputStream;
        private PDL pdl;

        public Builder() {
        }

        public Builder printerFilePath(String path) {
            this.printerFilePath = path;
            return this;
        }

        public Builder localFileInputStream(InputStream is) {
            this.localFileInputStream = is;
            return this;
        }

        public Builder pdl(PDL pdl) {
            this.pdl = pdl;
            return this;
        }

        public PrintFile build() throws PrintException {
            if(pdl == null) throw new IllegalStateException("pdl is null");

            String fileId = null;

            if(localFileInputStream != null) {

                CreatePrintFileRequestBody body;
                try {
                    body = new CreatePrintFileRequestBody(localFileInputStream, localFileInputStream.available());
                } catch (IOException e) {
                    throw new PrintException(e.getMessage());
                }

                RequestHeader header = new RequestHeader();
                header.put(RequestHeader.KEY_X_APPLICATION_ID, SmartSDKApplication.getProductId());

                Request request = new Request();
                request.setHeader(header);
                request.setBody(body);

                Printer printer = new Printer();
                try {
                    Response<CreatePrintFileResponseBody> resp = printer.createPrintFile(request);
                    fileId = resp.getBody().getFileId();

                } catch (IOException e) {
                    throw new PrintException(e.getMessage());
                } catch (InvalidResponseException e) {
                    throw new PrintResponseException(e);
                }

                if(fileId == null && printerFilePath == null) {
                    throw new PrintException("printerFilePath is null. or failed getID.");
                }
            }

            return new PrintFile(printerFilePath, fileId, pdl);
        }
    }


    public enum PDL {
        PDF("pdf"),
        XPS("xps"),
        RPCS("rpcs"),
        PCL("pcl"),
        PS("ps"),
        TIFF("tiff");

        private String mPdl;
        private PDL(String value){
            this.mPdl = value;
        }

        public String toString(){
            return this.mPdl;
        }

        private static volatile Map<String, PDL> directory = null;
        private static Map<String,PDL> getDirectory(){
            if(directory == null) {
                Map<String, PDL> d = new HashMap<String, PDL>();
                for(PDL pdl : values()) {
                    d.put(pdl.toString(), pdl);
                }
                directory = d;
            }
            return directory;
        }
        public static PDL fromString(String value) {
            return getDirectory().get(value);
        }
    }
}
