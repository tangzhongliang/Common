/*
 *  Copyright (C) 2013-2015 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * WebAPIへのアクセスする際のリクエストを管理するクラス。<BR>
 * 生成する際は必ずビルダークラスを通して生成してください。<BR>
 * @see RestClient
 */
public final class RestRequest {

    /**
     * コンストラクタで利用するRESTのMETHODタイプ
     */
    public static final String METHOD_GET       = "GET";
    public static final String METHOD_POST      = "POST";
    public static final String METHOD_PUT       = "PUT";
    public static final String METHOD_DELETE    = "DELETE";

    /** クラスで管理するリクエスト */
    private HttpRequestBase request = null;


    /**
     * コンストラクタ<BR>
     * メソッドの種類に応じて、内部で管理するリクエストタイプを生成します。<BR>
     * ビルダー経由以外からオブジェクトを生成しないように不可視とする。<BR>
     * @param method メソッド種類
     * @see RestRequest#METHOD_DELETE
     * @see RestRequest#METHOD_GET
     * @see RestRequest#METHOD_POST
     * @see RestRequest#METHOD_PUT
     */
    private RestRequest(String method){

        if(method == null){
            throw new IllegalArgumentException("method is null");
        }

        if( method.equals(METHOD_GET) ){
            request = new HttpGet();
        } else if( method.equals(METHOD_POST) ) {
            request = new HttpPost();
        } else if( method.equals(METHOD_PUT) ) {
            request = new HttpPut();
        } else if(method.equals(METHOD_DELETE) ) {
            request = new HttpDelete();
        } else {
            throw new IllegalArgumentException("Unknown method type");
        }
    }

    /**
     * 設定しているURIを取得する<BR>
     * @return 設定しているURI
     * @throws IllegalStateException 内部で管理しているリクエストがnullの場合
     */
    public URI getURI(){
        if(request == null){
            throw new IllegalStateException("request is null");
        }

        return request.getURI();
    }

    /**
     * 引数で指定したURIで、内部管理しているリクエストのURIを設定する
     * @param uri
     */
    public void setURI(URI uri){
        if(uri == null){
            throw new IllegalArgumentException("uri is null");
        }

        request.setURI(uri);
    }

    /**
     * リクエスト時に指定するボディメッセージの設定<BR>
     * リクエストメソッドがPOST/PUTのみ有効<BR>
     * @param bodyMsg
     */
    public void setBodyMessage(String bodyMsg) throws UnsupportedEncodingException {
        if( bodyMsg == null) {
            throw new IllegalArgumentException("bodyMsg is null");
        }

        if(!(request instanceof HttpEntityEnclosingRequestBase)) {
            throw new IllegalStateException("This method execute only Post/Put method");
        }

        StringEntity entity = new StringEntity(bodyMsg, HTTP.UTF_8);
        entity.setContentType("application/json; charset=utf-8");
        ((HttpEntityEnclosingRequestBase)request).setEntity(entity);
    }

    /**
     * リクエスト時に指定するボディーデータの設定</BR>
     * @param is
     * @param size
     */
    public void setBodyInputStream(InputStream is, int size) {
        if(is == null){
            throw new IllegalArgumentException("data is null");
        }

        if(!(request instanceof HttpEntityEnclosingRequestBase)) {
            throw new IllegalStateException("This method execute only Post/Put method");
        }

        InputStreamEntity entity = new InputStreamEntity(is, size);
        entity.setContentType("application/octet-stream");
        ((HttpEntityEnclosingRequestBase)request).setEntity(entity);
    }

    /**
     * リクエストメソッドを取得<BR>
     * @return メソッド種別文字列
     * @see RestRequest#METHOD_DELETE
     * @see RestRequest#METHOD_GET
     * @see RestRequest#METHOD_POST
     * @see RestRequest#METHOD_PUT
     */
    public String getRequestMethod() {
        if( request instanceof HttpGet){
            return METHOD_GET;
        }else if( request instanceof HttpPost){
            return METHOD_POST;
        }else if( request instanceof HttpPut){
            return METHOD_PUT;
        }else if( request instanceof HttpDelete){
            return METHOD_DELETE;
        }

        return null;
    }

    /**
     * リクエストを取得<BR>
     * @return 構築したリクエスト
     */
    HttpRequestBase getRequest(){
        return this.request;
    }

    /**
     * 任意のヘッダーを付与する<BR>
     * ヘッダーリストの最後に指定したヘッダーを付与するため、重複可能<BR>
     * 重複した場合は、そのまま送信されサーバー実装に依存した結果が得られます。<BR>
     * @param header 追加するヘッダー
     */
    private void addHeader(Header header){
        this.request.addHeader(header);
    }


    /**
     * RestRequestを生成するビルダークラス<BR>
     *
     */
    public static class Builder {
        private String scheme = null;
        private String method = null;
        private String host = null;
        private int port = -1;
        private String path = null;
        private Map<String, String> headers = null;
        private List<BasicNameValuePair> queries = null;
        private String bodyMsg = null;
        private InputStream bodyData = null;
        private int bodySize = -1;

        private static final String HEADER_ACCEPT   = "Accept";
        private static final String HEADER_ACCEPT_DEFAULT = "application/json";

        public Builder() {
        }

        public Builder scheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder method(String method){
            this.method = method;
            return this;
        }

        public Builder host(String host){
            this.host = host;
            return this;
        }

        public Builder port(int port){
            this.port = port;
            return this;
        }

        public Builder path(String path){
            this.path = path;
            return this;
        }

        public Builder body(String bodyMsg){
            this.bodyMsg = bodyMsg;
            return this;
        }

        public Builder header(Map<String, String> headers){
            if (this.headers == null) {
                this.headers = new LinkedHashMap<String, String>();
            }
            this.headers.putAll(headers);
            return this;
        }

        public Builder query(Map<String,String> params){
            if (this.queries == null) {
                this.queries = new ArrayList<BasicNameValuePair>();
            }

            Set<String> keys = params.keySet();
            for(String key : keys){
                this.queries.add(new BasicNameValuePair(key, params.get(key)));
            }

            return this;
        }

        public Builder bodyInputStream(InputStream is) {
            this.bodyData = is;
            return this;
        }

        public Builder bodySize(int size) {
            this.bodySize = size;
            return this;
        }

        public RestRequest build() throws IllegalStateException,URISyntaxException,UnsupportedEncodingException{
            if( this.scheme == null ) throw new IllegalStateException("scheme is null");
            if( this.method == null ) throw new IllegalStateException("method is null");
            if( this.path == null) throw new IllegalStateException("path is null");
            if( this.host == null) throw new IllegalStateException("host is null");
            if( this.port < 0 || this.port > 65535) throw new IllegalStateException("port is invalid");

            RestRequest request = new RestRequest(this.method);

            //make header
            request.addHeader(new BasicHeader(HEADER_ACCEPT, HEADER_ACCEPT_DEFAULT));
            if( this.headers != null ) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    request.addHeader(new BasicHeader(entry.getKey(), entry.getValue()));
                }
            }

            //make query
            String queryString = null;
            if ((this.queries != null) && (this.queries.size() > 0)) {
                queryString = URLEncodedUtils.format(this.queries, HTTP.UTF_8);
            }

            //make body (only POST, PUT methods)
            if (METHOD_POST.equals(this.method) || METHOD_PUT.equals(this.method)) {
                if(this.bodyMsg != null) {
                    request.setBodyMessage(this.bodyMsg);
                }else if(this.bodyData != null) {
                    if(this.bodySize >= 0)
                        request.setBodyInputStream(this.bodyData, this.bodySize);
                }
            }

            URI uri = new URI( this.scheme, null, this.host, this.port, this.path, queryString, null );
            request.setURI(uri);

            return request;
        }
    }

}
