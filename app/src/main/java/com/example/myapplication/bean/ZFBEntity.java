package com.example.myapplication.bean;

import java.io.Serializable;

/**
 * Created by kekex on 2018/10/9.
 */

public class ZFBEntity implements Serializable {

    /**
     * code : 100
     * form : alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2019022163288236&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E7%99%BD%E7%94%A8%E6%88%B7%E5%85%85%E5%80%BC%22%2C%22out_trade_no%22%3A%2215531502075919%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E7%99%BD%E7%94%A8%E6%88%B7%E5%85%85%E5%80%BC%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.1%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fxiaobai.jikewangluo.cn%2Frenwu%2Falipayquery&sign=V5rPm3jxAqmqhBkkthEirgQC8uoCRzEnxUsrQWWiO0IHZ3D1tZAbMd7zZlLMmM2W4NXAwcuOSvX2uguLiHRHk3ja7OTeL0%2BG47q6C%2F86NwKN7SNTk9UgAkYB3k536FPnjDQu0%2BWTbwnLN%2FwzjDaqMBYGQcTo9LIns8sVKM7dzhuEFR%2BsbwZacGbL4wyNxrYMbx3arRMoUupb%2BhDw7B%2Fi9DAXlPTVPfhdfGdxUV0xshCa7tr4sh3qxsgQW190%2FOR53%2FxCXj3fL2R3hQX160b0OJawpDS01bKOtIzy1IBrtI%2FOyfQsg7Z%2FG0AzjHjdqmc%2BzDWg7Fe3j10Z1AaO3DfW8Q%3D%3D&sign_type=RSA2&timestamp=2019-03-21+14%3A36%3A47&version=1.0
     * rinfo : 生成成功
     */

    private String code;
    private String form;
    private String rinfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getRinfo() {
        return rinfo;
    }

    public void setRinfo(String rinfo) {
        this.rinfo = rinfo;
    }
}
