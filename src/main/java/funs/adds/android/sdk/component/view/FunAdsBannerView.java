package funs.adds.android.sdk.component.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import funs.adds.android.sdk.R;

public class FunAdsBannerView extends RelativeLayout {

    private Button mButtonDownload;

    private TextView mTvTile,mTvContent;

    private ImageView mImageIcon;

    private Context context;

    public FunAdsBannerView(final Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public FunAdsBannerView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public FunAdsBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public FunAdsBannerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.funads_banner_view,this,true);
        mButtonDownload = view.findViewById(R.id.btn_download);
        mImageIcon = view.findViewById(R.id.image_small_content);
        mTvContent = view.findViewById(R.id.tv_content);
        mTvTile = view.findViewById(R.id.tv_title);
        mButtonDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=vn.funtap.ngaokiem3d"));
                intent.setPackage("com.android.vending");
                context.startActivity(intent);
                Toast.makeText(context, "DownLoad", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
