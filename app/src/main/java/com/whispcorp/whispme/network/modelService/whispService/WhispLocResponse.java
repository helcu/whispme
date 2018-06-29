package com.whispcorp.whispme.network.modelService.whispService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.whispcorp.whispme.network.modelService.MetaResponse;
import com.whispcorp.whispme.network.modelService.OwnerResponse;

import java.util.List;

public class WhispLocResponse {

    @SerializedName("meta")
    @Expose
    private MetaResponse meta;

    @SerializedName("owner")
    @Expose
    private OwnerResponse owner;

    @SerializedName("update")
    @Expose
    private String update;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("typewhisp")
    @Expose
    private String typewhisp;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("place")
    @Expose
    private String place;

    @SerializedName("loc")
    @Expose
    private List<Double> loc = null;

    @SerializedName("__v")
    @Expose
    private Integer v;


    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }

    public OwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(OwnerResponse owner) {
        this.owner = owner;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypewhisp() {
        return typewhisp;
    }

    public void setTypewhisp(String typewhisp) {
        this.typewhisp = typewhisp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Double> getLoc() {
        return loc;
    }

    public void setLoc(List<Double> loc) {
        this.loc = loc;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
