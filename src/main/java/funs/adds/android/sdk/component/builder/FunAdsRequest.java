package funs.adds.android.sdk.component.builder;

import funs.adds.android.sdk.component.sate.FunAdsView;

public class FunAdsRequest {
    private FunAdsView funAdsView;

    public FunAdsRequest(FunAdsView funAdsView) {
        this.funAdsView = funAdsView;
    }

    public FunAdsRequest loadAdsRequest(){
        funAdsView.loadFunAds();
        return null;
    }
}
