package funs.adds.android.sdk.component.sate;

import android.content.Context;

public class FunAdsContext {

    private Context mContext;

    private FunAdsView mAdsView;

    public FunAdsView getmAdsView() {
        return mAdsView;
    }

    public void setmAdsView(FunAdsView mAdsView) {
        this.mAdsView = mAdsView;
    }

    public FunAdsContext(Context mContext) {
        this.mContext = mContext;
    }

    public void showAds() {
        mAdsView.showFunAds();
    }

    public void hideFunAds() {
        mAdsView.hideFunAds();
    }

    public void loadFunAds() {
        mAdsView.loadFunAds();
    }
}
