package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/3/2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DevisResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("propositions")
    @Expose
    private List<Planning> propositions = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Planning> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Planning> propositions) {
        this.propositions = propositions;
    }

}
