
package com.mpdam.info.tdsapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RapportResp {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("rapport")
    @Expose
    private Rapport rapport;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Rapport getRapport() {
        return rapport;
    }

    public void setRapport(Rapport rapport) {
        this.rapport = rapport;
    }

}
