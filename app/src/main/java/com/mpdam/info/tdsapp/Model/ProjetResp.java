
package com.mpdam.info.tdsapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjetResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("projets")
    @Expose
    private List<Projet> projets = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

}
