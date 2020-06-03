package funs.adds.android.sdk.component.factory;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import funs.adds.android.sdk.component.callback.AdsFunListener;
import funs.adds.android.sdk.component.callback.DataBroadCastReceiver;
import funs.adds.android.sdk.component.callback.UpdateDataCallBack;
import funs.adds.android.sdk.component.factory.bannerfactory.BannerAds;
import funs.adds.android.sdk.component.factory.popupfactory.PopUpAdsDialog;
import funs.adds.android.sdk.component.factory.popupfactory.PopUpAdsDialogVideo;
import funs.adds.android.sdk.component.model.networkmodel.AdsFunModel;
import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.network.APIServices;
import funs.adds.android.sdk.component.network.Host;
import funs.adds.android.sdk.component.network.NetworkProvider;
import funs.adds.android.sdk.component.observer.AdsServices;
import funs.adds.android.sdk.component.sate.FunAdsView;
import funs.adds.android.sdk.component.util.AdsUtils;
import funs.adds.android.sdk.component.util.PopUpType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RequestAdsFun {

    private volatile FunAdsView funAdsView;

    private  AdsFunFactory adsFunFactory;

    private String iventoriId;

    private AdsServices services;

    private AdsFunListener listener;
    
    private static Boolean isLoad = false;

    private Context context;

    public RequestAdsFun(String iventoriId, Context context) {
        super();
        this.iventoriId = iventoriId;
        this.context = context;
        this.listener = (AdsFunListener) context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIventoriId() {
        return iventoriId;
    }

    public void setIventoriId(String iventoriId) {
        this.iventoriId = iventoriId;
    }


    public synchronized void showAds(Data data) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adsFunFactory = new AdsFunFactory(context);
                String templateName = data.getTemplate().getName();
                String type = data.getType();
                if (templateName == null) {
                    templateName = "";
                }
                services = new AdsServices(data);
                if (templateName.equals(AdsUtils.INLINE)) {
                    funAdsView = adsFunFactory.createPopUp(PopUpType.BANNER);
                } else {
                    if (type.equals(AdsUtils.PICTURE)) {
                        funAdsView = adsFunFactory.createPopUp(PopUpType.POPUP);
                    } else {
                        funAdsView = adsFunFactory.createPopUp(PopUpType.VIDEO);
                    }
                }
                if (funAdsView!= null) {
                    funAdsView.showFunAds();
                    services.attach(funAdsView);
                    services.notifyAllObserver();
                }
            }
        },0);

    }

    public synchronized void requestAds(){

        Log.d("ads_fun","requestAds");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("ads_fun","CREATE THREAD");
                APIServices apiServices = NetworkProvider.getClient(Host.DEV_URL).create(APIServices.class);
                apiServices.fetchAds(iventoriId).enqueue(new Callback<AdsFunModel>() {
                    @Override
                    public void onResponse(Call<AdsFunModel> call, Response<AdsFunModel> response) {
                        if (response.isSuccessful() && response.body().getCode() == 200) {
                            System.out.println("ads_fun" + response.code());
                            Log.d("ads_fun","200: " + response.code());
                            showAds(response.body().getData());
                        } else {
                            Log.d("ads_fun",": " + response.code());
                            System.out.println("ads_fun" + response.code());
                        }
                        listener.reQuestAdsSuccess(response.body().getCode());
                    }

                    @Override
                    public void onFailure(Call<AdsFunModel> call, Throwable t) {
                        Log.d("ads_fun","onFailure : " + t.getMessage());
                        System.out.println("ads_fun" + t.getMessage());
                    }
                });
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class RequestAdsBuilder {

        private String iventoriId;

        private Context context;

        public RequestAdsBuilder() {
        }

        public RequestAdsBuilder of(Context context) {
            this.context = context;
            return this;
        }

        public RequestAdsBuilder context(Context context) {
            this.context = context;
            return this;
        }

        public RequestAdsBuilder adsId(String iventoriId) {
            this.iventoriId = iventoriId;
            return this;
        }

        public RequestAdsFun buil() {
            RequestAdsFun requestAdsFun = new RequestAdsFun(iventoriId,context);
            return requestAdsFun;
        }
    }
}
