package funs.adds.android.sdk.component.factory.bannerfactory;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import funs.adds.android.sdk.R;
import funs.adds.android.sdk.component.callback.AdsFunListener;
import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.util.BannerEnum;

public class BannerAds extends Banner {
    private Context context;
    private View view;
    private ViewGroup viewGroup;
    private Button btn_close,btn_download;
    private AppCompatActivity activity;
    private TextView tv_content,tv_titile;
    private LocalBroadcastManager localBroadcastManagerWeakReference;
    private ImageView banner_image;
    private AdsFunListener listener;
    private BannerEnum bannerEnum;
    public BannerAds(Context context, BannerEnum bannerEnum) {
        super(context);
        this.context = context;
        this.bannerEnum = bannerEnum;
        initView();
    }

    public BannerAds(Context context) {
        super(context);
        this.context = context;
        this.listener = (AdsFunListener) context;
        initView();
    }
    public void addViewIntoRoot(AppCompatActivity activity) {
        RelativeLayout.LayoutParams lay = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        lay.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        viewGroup.addView(view,lay);
        listener.isShowAds(true);
    }

    public BannerAds(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public BannerAds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public BannerAds(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.banner,this,true);
        banner_image = view.findViewById(R.id.banner_imageView);
        btn_close = view.findViewById(R.id.btn_close);
        btn_download = view.findViewById(R.id.banner_button_download);
        tv_content = view.findViewById(R.id.banner_tv_content);
        tv_titile = view.findViewById(R.id.banner_tv_title);
        activity = (AppCompatActivity) context;
        viewGroup = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);
        btn_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideFunAds();
            }
        });

        btn_download.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=vn.funtap.ngaokiem3d"));
                intent.setPackage("com.android.vending");
                context.startActivity(intent);
                Toast.makeText(context, "DownLoad", Toast.LENGTH_SHORT).show();
                listener.isDownLoad(true);
            }
        });
        Log.d("adsView","initView");
    }

    @Override
    public void hideFunAds() {

        if (view.getParent() != null) {
            viewGroup.removeView(view);
            Log.d("adsView","hideFunAds" + "bannerAds");
            listener.isCloseAds(true);
        }
    }

    @Override
    public void showFunAds() {
        Log.d("adsView","showFunAds" + view.getRootView());
        if (view.getParent() == null) {
            addViewIntoRoot(activity);
            Log.d("adsView","showFunAds" + "bannerAds");
        }
    }

    @Override
    public void loadFunAds() {
        Log.d("adsView","loadFunAds" + "bannerAds");
    }

    public void drawAds(Data data) {
        Glide.with(getContext()).load(data.getLogoUrl()).into(banner_image);
        tv_content.setText(data.getShortDescribe());
        tv_titile.setText(data.getShortTitle());
        Log.d("adsView","drawAds" + "bannerAds");
    }
    @Override
    public void updateAds(Data data) {
        Log.d("adsView","updateAds_banner");
        drawAds(data);
    }
}
