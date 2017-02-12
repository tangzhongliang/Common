/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.service.scanner;

import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;


public class FilePathResponseBody extends Element implements ResponseBody {
	
	private static final String KEY_FILE_PATH = "filePath";
    private static final String KEY_ROTATE    = "rotate";
	
	FilePathResponseBody(Map<String, Object> values) {
		super(values);
	}
	
	/*
	 * filePath (String)
	 */
	public String getFilePath() {
		return getStringValue(KEY_FILE_PATH);
	}

	public void setFilePath(String path) {
		values.put(KEY_FILE_PATH, path);
	}
	
    /*
     * rotate (Number)
     */
    public Integer getRotate() {
        return getNumberValue(KEY_ROTATE);
    }
    
}