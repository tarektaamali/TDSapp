package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/4/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("services")
    @Expose
    private List<Service> services = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

}