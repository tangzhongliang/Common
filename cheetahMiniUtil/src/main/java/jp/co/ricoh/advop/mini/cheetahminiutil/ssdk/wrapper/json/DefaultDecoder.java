/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.json;

import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

class DefaultDecoder implements Decoder {
	
	@Override
	public <T> T decode(String source, Class<? extends T> clazz) throws DecodedException {
		try {
			return JSON.decode(source, clazz);
		} catch (JSONException e) {
			throw new DecodedException(e.getMessage());
		}
	}
	
}
