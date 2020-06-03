package funs.adds.android.sdk.component.factory.bannerfactory;

import android.content.Context;

import funs.adds.android.sdk.component.util.BannerEnum;

public class BannerFactory {

    private static Context context;

    public BannerFactory(Context context) {
        this.context = context;
    }

    public static final Banner getBanner(BannerEnum bannerEnum) {
        switch (bannerEnum) {
            case TOP:
                throw new IllegalArgumentException("This Banner type is unsupported");
            case BOTTOM:
                return new BannerAds(context);
            default:
                throw new IllegalArgumentException("This Banner type is unsupported");
        }
    }
}
