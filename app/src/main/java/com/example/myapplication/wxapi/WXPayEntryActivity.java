package com.example.myapplication.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by kekex on 2019/3/12.
 * 微信支付的回调
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.e("xuke", "onPayFinish, errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
            } else if (resp.errCode == -1) {
                Toast.makeText(this, "支付异常,请重新支付或联系客服", Toast.LENGTH_SHORT).show();
            } else if (resp.errCode == -2) {
                Toast.makeText(this, "已取消支付", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
