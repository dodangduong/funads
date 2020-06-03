package funs.adds.android.sdk.component.typeads;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import funs.adds.android.sdk.component.sate.FunAdsView;

public class FullSreenAds extends FunAdsView {
    public FullSreenAds(Context context) {
        super(context);
    }

    public FullSreenAds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullSreenAds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FullSreenAds(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void loadFunAds() {
        Log.d("adsView","loadFunAds" + "fullScreenAds");
    }

    @Override
    public void showFunAds() {
        Log.d("adsView","showFunAds" + "fullScreenAds");
    }

    @Override
    public void hideFunAds() {
        Log.d("adsView","hideFunAds" + "fullScreenAds");
    }
}
