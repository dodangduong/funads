package funs.adds.android.sdk.component.callback;

public interface AdsFunListener {
    void reQuestAdsSuccess(int code);
    void isHideAds(boolean isHide);
    void isShowAds(boolean isShow);
    void isCloseAds(boolean isClose);
    void isDownLoad(boolean isDownload);
}
