package funs.adds.android.sdk.component.factory;

import android.content.Context;

import funs.adds.android.sdk.component.factory.bannerfactory.BannerAds;
import funs.adds.android.sdk.component.factory.popupfactory.PopUpAds;
import funs.adds.android.sdk.component.sate.FunAdsView;
import funs.adds.android.sdk.component.util.PopUpEnum;
import funs.adds.android.sdk.component.util.PopUpType;

public class AdsFunFactory {

    private  Context context;


    public AdsFunFactory(Context context) {
        this.context = context;
    }

    public FunAdsView createPopUp(PopUpType popUpType) {
        switch (popUpType) {
            case VIDEO:
               return new PopUpAds(context, PopUpEnum.POPUP_WITH_VIDEO);
            case POPUP:
                return new PopUpAds(context, PopUpEnum.POPUP_WITHOUT_VIDEO);
            case BANNER:
                return new BannerAds(context);
            default:
                throw new IllegalArgumentException("This Popup type is unsupported");
        }
    }

}
