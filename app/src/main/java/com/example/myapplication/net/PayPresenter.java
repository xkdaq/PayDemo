package com.example.myapplication.net;


import com.example.myapplication.bean.WXEntity;
import com.example.myapplication.bean.ZFBEntity;

/**
 * 支付
 */
public class PayPresenter extends BasePresenter<IView> {

    public PayPresenter(IView view) {
        super(view);
    }

    /**
     * 微信支付
     */
    public void wxPay(String u_id, String money, final String type) {

        addSubscription(iHttpUrl.weixinPay(u_id, money), new BaseSubscriber<WXEntity>() {
            @Override
            public void onNext(WXEntity entity) {
                if (entity != null) {
                    iView.onSuccess("100", "微信参数获取成功", entity, type);
                } else {
                    iView.onFailed("200", "微信参数获取失败");
                }
            }

            @Override
            public void onFailed(String code, String msg) {
                iView.onFailed(code, msg);
            }
        });
    }

    /**
     * 支付宝支付
     */
    public void zfbPay(String u_id, String money, final String type) {

        addSubscription(iHttpUrl.zhifubaoPay(u_id, money), new BaseSubscriber<ZFBEntity>() {
            @Override
            public void onNext(ZFBEntity entity) {
                if (entity != null) {
                    iView.onSuccess("100", "支付宝参数获取成功", entity.getForm(), type);
                } else {
                    iView.onFailed("200", "支付宝参数获取失败");
                }
            }

            @Override
            public void onFailed(String code, String msg) {
                iView.onFailed(code, msg);
            }
        });
    }

}
