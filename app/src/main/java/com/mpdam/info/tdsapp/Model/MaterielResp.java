package com.mpdam.info.tdsapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterielResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("materiels")
    @Expose
    private List<Materiel> materiels = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }

}