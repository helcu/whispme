package com.whispcorp.whispme.network.modelService.trendService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrendResponse {

    @SerializedName("cordenate")
    @Expose
    private CoordenateResponse cordenate;
    @SerializedName("meta")
    @Expose
    private MetaResponse meta;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
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

    public CoordenateResponse getCordenate() {
        return cordenate;
    }

    public void setCordenate(CoordenateResponse cordenate) {
        this.cordenate = cordenate;
    }

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
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
