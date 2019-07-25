package com.example.myapplication.net;


import com.example.myapplication.bean.WXEntity;
import com.example.myapplication.bean.ZFBEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


/**
 * Created by kekex on 2018/4/28.
 * api
 */
public interface ApiStore {

    /**
     * 微信支付
     */
    @FormUrlEncoded
    @POST("renwu/wxpay")
    Observable<WXEntity> weixinPay(
            @Field("u_id") String u_id,
            @Field("money") String money
    );

    /**
     * 支付宝支付
     */
    @FormUrlEncoded
    @POST("renwu/alipayapp")
    Observable<ZFBEntity> zhifubaoPay(
            @Field("u_id") String u_id,
            @Field("money") String money
    );

}
