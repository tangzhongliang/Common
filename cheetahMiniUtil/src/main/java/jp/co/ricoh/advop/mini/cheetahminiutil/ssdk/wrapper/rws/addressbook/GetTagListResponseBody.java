/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.rws.addressbook;

import java.util.List;
import java.util.Map;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.Element;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.ResponseBody;

public class GetTagListResponseBody extends Element implements ResponseBody {
    
    private static final String KEY_TAGS = "tags";

	GetTagListResponseBody(Map<String, Object> value) {
		super(value);
	}
	
	/*
	 * tags (Array[Object])
	 */
	public TagArray getTags() {
	    List<Map<String, Object>> value = getArrayValue(KEY_TAGS);
	    if (value == null) {
	        return null;
	    }
	    return new TagArray(value);
	}
	
}
