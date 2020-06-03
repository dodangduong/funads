package funs.adds.android.sdk.component.model.networkmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AspectRatio {

    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Double height;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

}
