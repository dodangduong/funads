package funs.adds.android.sdk.component.factory.popupfactory;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.observer.FunObserver;

public class BasePopUp extends DialogFragment implements FunObserver {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void updateAds(Data data) {

    }
}
