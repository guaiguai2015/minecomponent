package com.guaiguai.wrl.mylibrary.okhttp;

import com.guaiguai.wrl.mylibrary.okhttp.Https.HttpsUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by WRL on 2017/5/6.
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    //为client配置一些参数
    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        okHttpClientBuilder.followRedirects(true); //重定向  默认是为true

        //对于https的支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;   //表示对所有的客户端都支持
            }
        });
        okHttpClientBuilder.sslSocketFactory(HttpsUtil.initSSLSocketFactory(),HttpsUtil.initTrustManager());

        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 发送指定的http/https请求
     * @param request
     * @param callback
     * @return
     */
    public static Call sendRequest (Request request, Callback callback) {

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;


    }

}
