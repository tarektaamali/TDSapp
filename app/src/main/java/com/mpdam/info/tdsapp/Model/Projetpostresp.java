package com.mpdam.info.tdsapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Projetpostresp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("projet")
    @Expose
    private Projet projet;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

}
