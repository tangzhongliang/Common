/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.printer;

import java.io.InputStream;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.BinaryRequestBody;

public class CreatePrintFileRequestBody implements BinaryRequestBody {

	private static final String CONTENT_TYPE_STREAM = "application/octet-stream";

    private InputStream mInputStream;
    private int mFileSize;

	public CreatePrintFileRequestBody(InputStream is, int size) {
        this.mInputStream = is;
        this.mFileSize = size;
	}

	@Override
	public String getContentType() {
		return CreatePrintFileRequestBody.CONTENT_TYPE_STREAM;
	}

	@Override
	public String toEntityString() {
		return null;
	}

    @Override
    public InputStream getInputStream() {
        return this.mInputStream;
    }

    @Override
    public int getSize() {
        return this.mFileSize;
    }

}
