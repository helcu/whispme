package com.whispcorp.whispme.network.modelService.whispService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.whispcorp.whispme.network.modelService.CordenateResponse;
import com.whispcorp.whispme.network.modelService.MetaResponse;
import com.whispcorp.whispme.network.modelService.OwnerResponse;

public class WhispResponse {

    @SerializedName("meta")
    @Expose
    private MetaResponse meta;

    @SerializedName("update")
    @Expose
    private String update;

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("cordenate")
    @Expose
    private CordenateResponse cordenate;

    @SerializedName("owner")
    @Expose
    private OwnerResponse owner;

    @SerializedName("typewhisp")
    @Expose
    private String typewhisp;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("__v")
    @Expose
    private Integer v;


    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
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

    public CordenateResponse getCordenate() {
        return cordenate;
    }

    public void setCordenate(CordenateResponse cordenate) {
        this.cordenate = cordenate;
    }

    public OwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(OwnerResponse owner) {
        this.owner = owner;
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

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
