package funs.adds.android.sdk.component.factory.popupfactory;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.offline.FilteringManifestParser;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistParserFactory;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import funs.adds.android.sdk.R;
import funs.adds.android.sdk.component.callback.AdsFunListener;
import funs.adds.android.sdk.component.model.networkmodel.Data;
import funs.adds.android.sdk.component.observer.FunObserver;
import funs.adds.android.sdk.component.util.AdsUtils;
import funs.adds.android.sdk.component.view.FunAdsBannerView;

public class PopUpAdsDialogVideo extends BasePopUp implements Player.EventListener {

    public PlayerView playerView;

    public SimpleExoPlayer player;

    private Button mButtonDownload;

    private TextView mTvTile,mTvContent;

    private ImageView mImageIcon;

    private AdsFunListener listener;

    private FunAdsBannerView funAdsBannerView;

    private Data data;

    private int topMargin, leftMargin, rightMargin;

    private RelativeLayout btn_close;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        Log.d("adsView","onCreateView");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(AdsUtils.KEY_DATA,data);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_portrial_video,container,true);
        funAdsBannerView = view.findViewById(R.id.funads_banner_view);
        playerView = view.findViewById(R.id.video_view);
        btn_close = view.findViewById(R.id.btn_close_popupvideo);
        playerView.setShutterBackgroundColor(Color.TRANSPARENT);
        playerView.setKeepContentOnPlayerReset(true);
        mButtonDownload = view.findViewById(R.id.btn_download);
        mImageIcon = view.findViewById(R.id.image_small_content);
        mTvContent = view.findViewById(R.id.tv_content);
        mTvTile = view.findViewById(R.id.tv_title);
        this.listener = (AdsFunListener) getActivity();
        if (savedInstanceState != null) {
            this.data = savedInstanceState.getParcelable(AdsUtils.KEY_DATA);
        }
        mButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=vn.funtap.ngaokiem3d"));
                intent.setPackage("com.android.vending");
                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), "DownLoad", Toast.LENGTH_SHORT).show();
                listener.isDownLoad(true);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Close_PopUp" + "Close");
                dismiss();
                player.release();
                listener.isCloseAds(true);
            }
        });
        initPlayer();
        initializePlayer();
        Log.d("adsView","onCreateView" + data.getLongTitle());
        return view;
    }

    @SuppressLint("NewApi")
    private void initPlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(getContext());
            playerView.setPlayer(player);
            listener.isShowAds(true);

        }
        Log.d("adsView","initPlayer");
    }

    private void initializePlayer() {
        mTvTile.setText(data.getShortTitle());
        mTvContent.setText(data.getShortDescribe());
        String urlVideo = data.getVideoUrl().replace(" ","%20");
        MediaSource mediaSource = buildMediaSource(urlVideo);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        Log.d("adsView","initializePlayer");
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    private MediaSource buildMediaSource(String url) {
        DefaultDataSourceFactory fac = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "ADS"));
        MediaSource videoSource = new ExtractorMediaSource.Factory(fac)
                .createMediaSource(Uri.parse(url));
        return videoSource;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        Log.d("Exodebug","onPlayerStateChanged" + playWhenReady + "/" + playbackState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void updateAds(Data data) {
        Log.d("adsView","updateAds_popup_video");
        this.data = data;
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
