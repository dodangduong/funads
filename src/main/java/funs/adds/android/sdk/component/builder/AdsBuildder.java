package funs.adds.android.sdk.component.builder;

import funs.adds.android.sdk.component.sate.FunAdsView;

public interface AdsBuildder {
    AdsBuildder Builder(FunAdsView funAdsView);
    FunAdsRequest build();
}
