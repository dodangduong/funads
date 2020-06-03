package funs.adds.android.sdk.component.model.networkmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data implements Parcelable {

    public Data(String shortTitle, String shortDescribe, String type) {
        this.shortTitle = shortTitle;
        this.shortDescribe = shortDescribe;
        this.type = type;
    }

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("template")
    @Expose
    private Template template;
    @SerializedName("longTitle")
    @Expose
    private String longTitle;
    @SerializedName("shortTitle")
    @Expose
    private String shortTitle;
    @SerializedName("longDescribe")
    @Expose
    private String longDescribe;
    @SerializedName("shortDescribe")
    @Expose
    private String shortDescribe;
    @SerializedName("actions")
    @Expose
    private List<Action> actions = null;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("bannerUrl")
    @Expose
    private String bannerUrl;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;
    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getLongDescribe() {
        return longDescribe;
    }

    public void setLongDescribe(String longDescribe) {
        this.longDescribe = longDescribe;
    }

    public String getShortDescribe() {
        return shortDescribe;
    }

    public void setShortDescribe(String shortDescribe) {
        this.shortDescribe = shortDescribe;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(template,flags);
        out.writeString(shortTitle);
        out.writeString(shortDescribe);
        out.writeString(imageUrl);
        out.writeString(logoUrl);
        out.writeString(videoUrl);
    }

    public static final Parcelable.Creator<Data> CREATOR
            = new Parcelable.Creator<Data>() {
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    private Data(Parcel in) {
        template = (Template) in.readParcelable(Template.class.getClassLoader());
        shortTitle = in.readString();
        shortDescribe = in.readString();
        imageUrl = in.readString();
        logoUrl = in.readString();
        videoUrl = in.readString();
    }
}
