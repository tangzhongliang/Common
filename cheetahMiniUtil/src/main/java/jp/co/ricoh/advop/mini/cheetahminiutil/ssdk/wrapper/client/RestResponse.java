/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * RESTのレスポンスクラス<BR>
 * <BR>
 * RFC2616に準拠し、WebAPIからのレスポンスから必要なオブジェクトへのアクセスを容易にするためのクラスです<BR>
 * @see RestClient
 * @see RestRequest
 */
public class RestResponse {

    private static final String RESPONSE_HEADER_CONTENT_TYPE = "Content-Type";

    private int statusCode = -1;
    private String reasonPhrase = null;
    private byte[] bytes = null;
    private Header[] headers;
    private String contentType = null;

    /**
     * コンストラクタ<BR>
     * HttpResponseを元に、WebAPIの応答で必要なリソースの構築を行う
     * @param resp 構築する際の元となるHttpResponse
     * @throws IOException 指定したHttpResponseのストリームにアクセスした際の例外
     */
    RestResponse(HttpResponse resp) throws IOException {
    	if( resp == null ){
    		throw new IllegalArgumentException("resp is null");
    	}

    	/* ResponseBodyにアクセス(Entityのbodyをbyte配列として全て取得 */
    	bytes = EntityUtils.toByteArray(resp.getEntity());

		/* StatusLineのコピー */
    	statusCode = resp.getStatusLine().getStatusCode();
		reasonPhrase = resp.getStatusLine().getReasonPhrase();

		/* HttpHeaderのコピー */
		headers = resp.getAllHeaders().clone();

		/* Content-Typeのコピー */
		Header header = resp.getFirstHeader(RESPONSE_HEADER_CONTENT_TYPE);
		if (header != null) {
		    contentType = header.getValue();
		} else {
	        contentType = ""; 
		}
	}

    public int getStatusCode(){
        return this.statusCode;
    }

    public String getReasonPhrase() {
    	return this.reasonPhrase;
    }

    public String getStatusLine() {
    	return String.format("%d %s", statusCode, reasonPhrase);
    }

    public Map<String, String> getAllHeaders() {
    	Map<String, String> result = new LinkedHashMap<String, String>(headers.length);
    	for (Header header : headers) {
    		result.put(header.getName(), header.getValue());
    	}
    	return result;
    }

    public String getContentType() {
    	return contentType;
    }
    
    public byte[] getBytes() {
    	if (bytes == null) {
    		return new byte[0];
    	}
    	return bytes;
    }
    
    public String makeContentString(String charsetName) throws UnsupportedEncodingException {
    	if (bytes == null) {
    		return null;
    	}
    	String json = new String(bytes, charsetName);
        Logger.debug(json);
    	return json;
    }
    
}
