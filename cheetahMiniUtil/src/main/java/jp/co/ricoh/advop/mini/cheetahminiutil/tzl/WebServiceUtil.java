/*
 * Copyright (C) 2013-2017 RICOH Co.,LTD
 * All rights reserved
 */

package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import jp.co.ricoh.advop.mini.cheetahminiutil.util.LogC;

import static jp.co.ricoh.advop.mini.cheetahminiutil.ssdk.wrapper.common.RequestHeader.KEY_HOST;

public class WebServiceUtil {
    public static final String KEY_X_APPLICATION_ID = "X-Application-Id";
    public static final String KEY_X_SUBSCRIPTION_ID = "X-Subscription-Id";

    private static final String LOGTAG = "connect";

    private static final String HOST_NAME = "gw.machine.address:54080";
    private static int RETRY_MAX = 20;

    private static final String KEY_PAGE_NO = "pageNo";
    private static final String KEY_GET_METHOD = "getMethod";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length";

    public static String printJobInformation = "/rws/service/printer/jobs/";

    public static HttpResponse connect(String target, List<NameValuePair> querys) throws URISyntaxException, ClientProtocolException, IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        URI uri = new URI("http://" + HOST_NAME + target);

        int status = HttpStatus.SC_INTERNAL_SERVER_ERROR;

        if (querys == null) {
            querys = new ArrayList<NameValuePair>();
        }
        HttpResponse response = null;
        for (int cnt = 0; cnt < RETRY_MAX; cnt++) {

            HttpUriRequest request;
            if (querys != null) {
                request = new HttpGet(new URI(uri.toString() + "?" + URLEncodedUtils.format(querys, "utf-8")));
            } else {
                request = new HttpGet(new URI(uri.toString()));
            }
            request.addHeader(new BasicHeader(KEY_HOST, HOST_NAME));

            response = client.execute(request);
            status = response.getStatusLine().getStatusCode();
            Log.i(LOGTAG, "connect : " + status);
            if (status == HttpStatus.SC_OK) {
                break;
            } else {
                if (status != HttpStatus.SC_SERVICE_UNAVAILABLE) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
        return response;
    }

    public static String getPrintJobInformation(String jobID) throws URISyntaxException, ClientProtocolException, IOException {
        List<NameValuePair> querys = new ArrayList<NameValuePair>();
        String target = printJobInformation + jobID;
        HttpResponse response = connect(target, querys);
        int status = response.getStatusLine().getStatusCode();

        String ret = EntityUtils.toString(response.getEntity());
        LogC.i("createPrintJob jobId: " + jobID);
        if (status == HttpStatus.SC_OK) {
            LogC.i("createPrintJob : " + ret);
            return ret;
        } else {
            LogC.i("createPrintJob error : " + ret);
            if (status != HttpStatus.SC_SERVICE_UNAVAILABLE) {
            }
            return ret;
        }
    }

    public static String getFile(String jobID, String pageNo) throws URISyntaxException, ClientProtocolException, IOException {

        HttpResponse jobstatus = WebServiceUtil.connect("/rws/service/scanner/jobs/" + jobID, null);
        LogC.i("jobstatus:" + EntityUtils.toString(jobstatus.getEntity()));

        String result = null;
        DefaultHttpClient client = new DefaultHttpClient();
        String target = "/rws/service/scanner/jobs/" + jobID + "/file";
        URI uri = new URI("http://" + HOST_NAME + target);

        int status = HttpStatus.SC_INTERNAL_SERVER_ERROR;

        for (int cnt = 0; cnt < RETRY_MAX; cnt++) {
            List<NameValuePair> querys = new ArrayList<NameValuePair>();
            querys.add(new BasicNameValuePair(KEY_PAGE_NO, pageNo));
            querys.add(new BasicNameValuePair(KEY_GET_METHOD, "filePath"));
            HttpUriRequest request = new HttpGet(new URI(uri.toString() + "?" + URLEncodedUtils.format(querys, "utf-8")));
            request.addHeader(new BasicHeader(KEY_HOST, HOST_NAME));

            HttpResponse response = client.execute(request);
            status = response.getStatusLine().getStatusCode();
            Log.i(LOGTAG, "getFile status : " + status);
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, EntityUtils.getContentCharSet(entity));
                Log.i(LOGTAG, "getFile response: " + result);
                return result;
            } else {
                Log.i(LOGTAG, "getFile error : " + EntityUtils.toString(response.getEntity()));
                if (status != HttpStatus.SC_SERVICE_UNAVAILABLE) {
                    break;
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }

        return result;
    }

    public static FileObject getAllFile(String jobID) throws URISyntaxException, ClientProtocolException, IOException {

        FileObject fileObject = new FileObject();
        HttpResponse jobstatus = WebServiceUtil.connect("/rws/service/scanner/jobs/" + jobID, null);

        if (jobstatus == null || jobstatus.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return fileObject;
        }
        String s = EntityUtils.toString(jobstatus.getEntity());
        LogC.i("jobstatus:" + s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            int count = jsonObject.getJSONObject("scanningInfo").getInt("scannedCount");
            fileObject = new FileObject();
            FileObject childObject;
            for (int i = 1; i < count + 1; i++) {
                String result = WebServiceUtil.getFile(jobID, "" + i);
                if (TextUtils.isEmpty(result)) {
                    LogC.e("get file " + jobID + "failed when pageNo =" + i);
                    continue;
                }
                childObject = new FileObject(new JSONObject(result).getInt("rotate"), new JSONObject(result).getString("filePath"));
                fileObject.childs.add(childObject);
            }
            if (fileObject.getChilds().size() < 1)
                return fileObject;
            String parent = new File(fileObject.getChilds().get(0).getPath()).getParent();
            fileObject.path = parent;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fileObject;
    }
}
