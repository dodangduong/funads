package funs.adds.android.sdk.component.factory.popupfactory;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Observable;

import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.observer.AdsServices;
import funs.adds.android.sdk.component.sate.FunAdsView;
import funs.adds.android.sdk.component.util.PopUpEnum;

public class PopUpAds extends FunAdsView {

    private FragmentTransaction fragmentTransaction;
    private BasePopUp popUp;
    private DialogFragment dialogFragment;
    Fragment fragment;

    private Context context;

    private PopUpEnum popUpEnum;

    public PopUpAds(Context context,PopUpEnum popUpEnum) {
        super(context);
        this.context = context;
        this.popUpEnum = popUpEnum;
    }

    public PopUpAds(Context context) {
        super(context);
        this.context = context;
    }

    public PopUpAds(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public PopUpAds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public PopUpAds(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }

    @Override
    public void hideFunAds() {

        Log.d("adsView","hideFunAds" + "popUpAds");
        if ( dialogFragment != null && dialogFragment.isVisible()) {
            dialogFragment.dismiss();
        }

    }

    @Override
    public void showFunAds() {
       initView();
    }

    @Override
    public void loadFunAds() {
        Log.d("adsView","showFunAds" + "popUpAds");

    }

    private void initView() {
        AppCompatActivity activity = (AppCompatActivity) context;
        fragmentTransaction = activity.getFragmentManager().beginTransaction();
        fragment = activity.getFragmentManager().findFragmentByTag("dialog");
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);
        popUp = PopUpFactory.createPopUp(popUpEnum);
        popUp.show(fragmentTransaction,"dialog");
        popUp.setCancelable(false);
    }

    @Override
    public void updateAds(Data data) {
        super.updateAds(data);
        Log.d("adsView","updateAds_popup_ads");
        AdsServices adsServices = new AdsServices(data);
        adsServices.attach(popUp);
        adsServices.notifyAllObserver();
    }
}
