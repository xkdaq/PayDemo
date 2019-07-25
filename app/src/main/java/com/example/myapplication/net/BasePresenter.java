package com.example.myapplication.net;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kekex on 2019/2/28.
 * basepresenter
 */

public class BasePresenter<T> {

    public T iView;
    protected ApiStore iHttpUrl = HttpClient.getRetrofit("https://xiaobai.jikewangluo.cn").create(ApiStore.class);
    private CompositeSubscription compositeSubscription;

    public BasePresenter(T view) {
        attachView(view);
    }

    /**
     * 绑定接口
     */
    public void attachView(T view) {
        this.iView = view;
    }

    /**
     * 进行网络请求
     */
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber));
    }


}
