package com.example.myapplication.net;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by kekex on 2019/2/28.
 * BaseSubscriber
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        try {
            String msg = "";
            if (e != null) {
                e.printStackTrace();
                msg = e.getMessage();
                Log.e("xuke", "onError msg: " + msg);
                if (msg.contains("timeout")
                        || msg.contains("java.net.ConnectException")
                        || msg.contains("java.net.SocketTimeoutException")
                        || msg.contains("failed")
                        || msg.contains("Failed to connect to")
                        || msg.contains("stream was reset")
                        || msg.contains("Unable to resolve host")
                        || msg.contains("SSL handshake time out")
                        || msg.contains("time out")) {
                    msg = "网络连接失败";
                    onFailed("1000", msg);
                    return;
                }

                if (msg.contains("java.lang.IllegalStateException")) {
                    Log.e("xuke", "java.lang.IllegalStateException");
                    return;
                }
            }
            onFailed("1000", msg);
        } catch (Exception ex) {
            onFailed("1001", "exception");
            ex.printStackTrace();
        }
    }

    @Override
    public void onNext(T t) {

    }

    public abstract void onFailed(String code, String msg);
}
