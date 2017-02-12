/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common;

import java.util.Map;

/**
 * 空のリクエストボディを示すクラス
 */
public class EmptyResponseBody extends Element implements ResponseBody {
	
	public EmptyResponseBody(Map<String, Object> values) {
		super(values);
	}

}
