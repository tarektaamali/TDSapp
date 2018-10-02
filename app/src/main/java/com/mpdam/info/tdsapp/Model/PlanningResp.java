package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/1/2018.
 */


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanningResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("planning")
    @Expose
    private List<Planning> planning = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Planning> getPlanning() {
        return planning;
    }

    public void setPlanning(List<Planning> planning) {
        this.planning = planning;
    }

}