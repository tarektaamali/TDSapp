package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/4/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("regions")
    @Expose
    private List<Region> regions = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

}