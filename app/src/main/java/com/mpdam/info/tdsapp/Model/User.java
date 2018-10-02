package com.mpdam.info.tdsapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("prenom")
    @Expose
    private String prenom;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("societe")
    @Expose
    private String societe;
    @SerializedName("telephone")
    @Expose
    private String telephone;

    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("star")
    @Expose
    private float avis;
    @SerializedName("count")
    @Expose
    private int nbre_projet;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
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
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getAvis() {
        return avis;
    }

    public void setAvis(float avis) {
        this.avis = avis;
    }

    public int getNbre_projet() {
        return nbre_projet;
    }

    public void setNbre_projet(int nbre_projet) {
        this.nbre_projet = nbre_projet;
    }
}