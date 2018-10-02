
package com.mpdam.info.tdsapp.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Projet {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("objet")
    @Expose
    private String objet;
    @SerializedName("lieu")
    @Expose
    private String lieu;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("etat_id")
    @Expose
    private Integer etatId;
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("realisateur_id")
    @Expose
    private Integer realisateurId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("service")
    @Expose
    private Service service;
    @SerializedName("region")
    @Expose
    private Region region;
    @SerializedName("etat")
    @Expose
    private Etat etat;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("fournisseur")
    @Expose
    private Object fournisseur;
    @SerializedName("propositions")
    @Expose
    private List<Planning> propositions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEtatId() {
        return etatId;
    }

    public void setEtatId(Integer etatId) {
        this.etatId = etatId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Object fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<Planning> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Planning> propositions) {
        this.propositions = propositions;
    }

}