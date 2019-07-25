package com.example.myapplication.net;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.TlsVersion;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kekex on 2019/2/28.
 * httpclient
 */

public class HttpClient {

    //读超时长，单位：毫秒
    private static final int READ_TIME_OUT = 30000;
    //连接时长，单位：毫秒
    private static final int CONNECT_TIME_OUT = 30000;

    /**
     * 配置retrofit
     */
    public static Retrofit getRetrofit(String baseUrl) {
        Interceptor controlInterceptor = chain -> {
            Request request = chain.request();
            RequestBody oldBody = request.body();
            Buffer buffer = new Buffer();
            assert oldBody != null;
            oldBody.writeTo(buffer);
            String data = buffer.readUtf8();
            Log.e("xuke", "request url: " + request.url() + "\nrequest data: " + data);
            return chain.proceed(request);
        };

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_1)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addNetworkInterceptor(controlInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("time", String.valueOf(System.currentTimeMillis()));
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();

        return retrofit;
    }

}
