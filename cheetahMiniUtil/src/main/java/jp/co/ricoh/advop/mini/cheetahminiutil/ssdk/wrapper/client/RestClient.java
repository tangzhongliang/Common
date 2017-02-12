/*
 *  Copyright (C) 2013 RICOH Co.,LTD.
 *  All rights reserved.
 */
package jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.client;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;
import jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.log.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * WebAPIへアクセスするための共通クライアントクラス<BR>
 * <BR>
 * RestRequestでリクエストを生成し、WebAPIClientで要求を実行してRestResponseを受け取ります。<BR>
 * WebAPIClientを生成したタイミングで、
 * <BR>
 * <BR>
 * [Sample]<BR>
 *  Map<String,String> params = new HashMap<String,String>();<BR>
 *  params.put("Key1","Value1");<BR>
 *  RestRequest request = new RestRequest.Builder().method(RestRequest.HTTP_GET).path("/rws/service/scanner/capability").params(params).build();<BR>
 *  WebAPIClient client = new WebAPIClient();<BR>
 *  RestResponse response = client.execute(request);<BR>
 */
public class RestClient {

	private final RestContext context;

	/* 内部で利用するHttpClient */
	private final HttpClient client;
	
	/*
	 * コンストラクタ<BR>
	 */
	public RestClient(RestContext context) {
		if (context == null) {
			throw new NullPointerException("context must not be null.");
		}
		this.context = context;
        this.client = getHttpClient();
	}

	/*
	 * WebAPIへのアクセス<BR>
	 * @param req
	 */
	public RestResponse execute(RestRequest req) throws IOException {
        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, context.getConnectionTimeout());
        HttpConnectionParams.setSoTimeout(params, context.getSoTimeout());
        try {
            if (Logger.isDebugEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("request: ");
            	sb.append(req.getRequestMethod());
            	sb.append(" ");
            	sb.append(req.getURI().toString());
                Logger.debug(sb.toString());
            }

            HttpResponse res =  client.execute(req.getRequest());

            if (Logger.isDebugEnabled()) {
            	StringBuilder sb = new StringBuilder();
            	sb.append("response: ");
            	sb.append(res.getStatusLine().getStatusCode());
            	sb.append(" ");
            	sb.append(res.getStatusLine().getReasonPhrase());
            	Logger.debug(sb.toString());
            }

            return new RestResponse(res);

        } finally {
        	client.getConnectionManager().shutdown();
        }
	}

    /**
     * Scheme別にHttpClientを生成する
     * @return
     */
    private HttpClient getHttpClient() {
        if(context == null) {
            return new DefaultHttpClient();
        }

        if("http".equals(context.getScheme())){
            return new DefaultHttpClient();
        } else {

            KeyStore truestStore;
            SSLSocketFactory socketFactory = null;

            try{
                truestStore = KeyStore.getInstance(KeyStore.getDefaultType());
                truestStore.load(null, null);
                socketFactory = new NoCheckSSLSocketFactory(truestStore);
            } catch (CertificateException e) {
                LogC.w(e);
            } catch (UnrecoverableKeyException e) {
                LogC.w(e);
            } catch (NoSuchAlgorithmException e) {
                LogC.w(e);
            } catch (KeyStoreException e) {
                LogC.w(e);
            } catch (KeyManagementException e) {
                LogC.w(e);
            } catch (IOException e) {
                LogC.w(e);
            }
            if(socketFactory == null) {
                return new DefaultHttpClient();
            }
            socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(context.getScheme(),socketFactory,context.getPort()));
            HttpParams httpParams = new BasicHttpParams();
            ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(httpParams,schemeRegistry);

            return new DefaultHttpClient(connectionManager, httpParams);
        }
    }

    //HTTPSで証明書のチェックをしないためのクラス
    class NoCheckSSLSocketFactory extends SSLSocketFactory {
        private SSLContext mSslContext = SSLContext.getInstance("TLS");

        public NoCheckSSLSocketFactory(KeyStore keyStore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(keyStore);

            TrustManager trustManager = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws
                        CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            };

            mSslContext.init(null, new TrustManager[] { trustManager }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port,boolean autoClose)
                throws IOException, UnknownHostException {
            return mSslContext.getSocketFactory().createSocket(socket, host, port,autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return mSslContext.getSocketFactory().createSocket();
        }
    }

}
