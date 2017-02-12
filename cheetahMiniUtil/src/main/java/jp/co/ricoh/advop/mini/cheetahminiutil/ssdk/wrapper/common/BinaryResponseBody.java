/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * WebAPI通信のレスポンスのデータを表すクラス。
 */
public class BinaryResponseBody implements ResponseBody {
	
	private final byte[] bytes;
	
	public BinaryResponseBody(byte[] bytes) {
		if (bytes == null) {
			throw new NullPointerException("bytes must not be null.");
		}
		this.bytes = bytes.clone();
	}
	
	public int getLength() {
		return bytes.length;
	}
	
	public byte[] toByteArray() {
		return bytes.clone();
	}
	
	public InputStream getInputStream() {
		return new ByteArrayInputStream(bytes);
	}
	
	public void writeTo(OutputStream stream) throws IOException {
		stream.write(bytes);
	}
	
}
