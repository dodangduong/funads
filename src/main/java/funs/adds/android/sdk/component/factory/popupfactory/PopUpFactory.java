package funs.adds.android.sdk.component.factory.popupfactory;

import android.content.Context;

import funs.adds.android.sdk.component.util.PopUpEnum;

public class PopUpFactory {

    private static Context context;

    public PopUpFactory(Context context) {
        this.context = context;
    }

    public static final BasePopUp createPopUp(PopUpEnum popUpEnum) {
        switch (popUpEnum) {
            case POPUP_WITH_VIDEO:
                return new PopUpAdsDialogVideo();
            case POPUP_WITHOUT_VIDEO:
                return new PopUpAdsDialog();
            default:
                throw new IllegalArgumentException("This Popup type is unsupported");
        }
    }
}
