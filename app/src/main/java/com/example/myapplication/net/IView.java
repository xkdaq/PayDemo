package com.example.myapplication.net;


public interface IView {
    /**
     * 成功获取到数据
     */
    void onSuccess(String code, String msg, Object data, String actionType);

    /**
     * 失败
     */
    void onFailed(String code, String msg);
}
