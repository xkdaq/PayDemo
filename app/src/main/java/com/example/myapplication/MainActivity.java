package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.PayTask;
import com.example.myapplication.bean.WXEntity;
import com.example.myapplication.net.IView;
import com.example.myapplication.net.PayPresenter;
import com.example.myapplication.wxapi.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private PayPresenter payPresenter;
    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        payPresenter = new PayPresenter(this);

        iwxapi = WXAPIFactory.createWXAPI(this, "wxeacd5c31a7cfcb2c", true);
        iwxapi.registerApp("wxeacd5c31a7cfcb2c");
    }


    /**
     * 支付宝支付
     */
    public void alipay(View view) {
        //u_id  后台用户id
        //money 充值金额
        //type  actionType接口返回类型
        payPresenter.zfbPay("71", "0.1", "alipay");
    }

    /**
     * 微信支付
     */
    public void wxpay(View view) {
        //u_id  后台用户id
        //money 充值金额
        //type  actionType接口返回类型
        payPresenter.wxPay("71", "0.1", "wxpay");
    }

    @Override
    public void onSuccess(String code, String msg, Object data, String actionType) {
        if ("alipay".equals(actionType)) {
            String orderInfo = (String) data;
            Log.e("xuke", "info = " + orderInfo);

            Runnable payRunnable = () -> {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg1 = new Message();
                msg1.what = 100;
                msg1.obj = result;
                mHandler.sendMessage(msg1);
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();
        } else if ("wxpay".equals(actionType)) {
            WXEntity wxEntity = (WXEntity) data;
            Log.e("xuke", "wx=" + wxEntity.toString());
            PayReq request = new PayReq();
            request.appId = wxEntity.getAppid();
            request.partnerId = wxEntity.getPartnerid();
            request.prepayId = wxEntity.getPrepayid();
            request.packageValue = wxEntity.getPackageX();
            request.nonceStr = wxEntity.getNoncestr();
            request.timeStamp = wxEntity.getTimestamp();
            request.sign = wxEntity.getPaySign();
            iwxapi.sendReq(request);
        }
    }

    @Override
    public void onFailed(String code, String msg) {

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100: {
                    PayResult result = new PayResult((Map<String, String>) msg.obj);
                    Log.e("xuke", "result=" + result);
                    if (result.getResultStatus().equals("9000")) {
                        //支付成功
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


}
