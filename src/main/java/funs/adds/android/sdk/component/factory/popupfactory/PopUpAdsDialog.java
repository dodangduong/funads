package funs.adds.android.sdk.component.factory.popupfactory;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import funs.adds.android.sdk.R;
import funs.adds.android.sdk.component.callback.AdsFunListener;
import funs.adds.android.sdk.component.callback.DataBroadCastReceiver;
import funs.adds.android.sdk.component.callback.UpdateDataCallBack;
import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.observer.FunObserver;
import funs.adds.android.sdk.component.util.AdsUtils;

public class PopUpAdsDialog extends BasePopUp {

    private ImageView mLagreImageView,mSmallImage;

    private Button mClose, mDownload;

    private AdsFunListener listener;

    private TextView mTitle, mContent;

    private Data data;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        Log.d("adsView","onCreate");
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_portrail_withbutton,container,true);
        if (savedInstanceState != null) {
            this.data = savedInstanceState.getParcelable(AdsUtils.KEY_DATA);
        }
        initView(view);
        Log.d("adsView","onCreateView");
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(AdsUtils.KEY_DATA,data);
    }

    private void initView(View view) {
        this.listener = (AdsFunListener) getActivity();
        mClose = view.findViewById(R.id.btn_close_popup);
        mContent = view.findViewById(R.id.tv_content);
        mTitle = view.findViewById(R.id.tv_title);
        mLagreImageView = view.findViewById(R.id.image_main_content);
        mSmallImage = view.findViewById(R.id.image_small_content);
        mDownload = view.findViewById(R.id.btn_download);

        mTitle.setText(data.getShortTitle());
        mContent.setText(data.getShortDescribe());
        Glide.with(getActivity()).load(data.getImageUrl()).into(mLagreImageView);
        Glide.with(getActivity()).load(data.getLogoUrl()).into(mSmallImage);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Close_PopUp" + "Close");
                dismiss();
                listener.isCloseAds(true);
            }
        });

        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=vn.funtap.ngaokiem3d"));
                intent.setPackage("com.android.vending");
                startActivity(intent);
                Toast.makeText(getDialog().getContext(), "DownLoad", Toast.LENGTH_SHORT).show();
                listener.isDownLoad(true);
            }
        });
        listener.isShowAds(true);
        Log.d("adsView","initView" + data.getLongTitle());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void updateAds(Data data) {
        Log.d("adsView","updateAds_popup");
        this.data = data;
    }
}
