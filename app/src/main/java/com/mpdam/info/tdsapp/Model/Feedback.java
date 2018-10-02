package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/3/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedback {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("avis")
    @Expose
    private float avis;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("realisateur_id")
    @Expose
    private Integer realisateurId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public float getAvis() {
        return avis;
    }

    public void setAvis(float avis) {
        this.avis = avis;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRealisateurId() {
        return realisateurId;
    }

    public void setRealisateurId(Integer realisateurId) {
        this.realisateurId = realisateurId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}