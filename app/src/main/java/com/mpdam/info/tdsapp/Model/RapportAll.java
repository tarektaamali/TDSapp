package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 4/23/2018.
 */



        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class RapportAll {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("rapports")
    @Expose
    private List<Rapport> rapports = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Rapport> getRapports() {
        return rapports;
    }

    public void setRapports(List<Rapport> rapports) {
        this.rapports = rapports;
    }

}