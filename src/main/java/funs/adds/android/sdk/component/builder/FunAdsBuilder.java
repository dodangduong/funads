package funs.adds.android.sdk.component.builder;

import funs.adds.android.sdk.component.sate.FunAdsView;

public class FunAdsBuilder implements AdsBuildder {

    private FunAdsView funAdsView;

    public FunAdsBuilder() {
    }

    @Override
    public AdsBuildder Builder(FunAdsView funAdsView) {
        this.funAdsView = funAdsView;
        return this;
    }

    @Override
    public FunAdsRequest build() {
        return new FunAdsRequest(funAdsView).loadAdsRequest();
    }
}
