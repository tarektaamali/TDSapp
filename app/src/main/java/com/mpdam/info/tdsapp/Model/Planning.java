package com.mpdam.info.tdsapp.Model;

/**
 * Created by Info on 7/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Planning {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("devis")
    @Expose
    private String devis;
    @SerializedName("echenance")
    @Expose
    private String echenance;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("projet_id")
    @Expose
    private Integer projetId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("status")
    @Expose
    private int status;


    @SerializedName("projet")
    @Expose
    private Projet projet;
    @SerializedName("user")
    @Expose
    private User user;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDevis() {
        return devis;
    }

    public void setDevis(String devis) {
        this.devis = devis;
    }

    public String getEchenance() {
        return echenance;
    }

    public void setEchenance(String echenance) {
        this.echenance = echenance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjetId() {
        return projetId;
    }

    public void setProjetId(Integer projetId) {
        this.projetId = projetId;
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

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}