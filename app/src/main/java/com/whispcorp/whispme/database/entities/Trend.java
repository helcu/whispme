package com.whispcorp.whispme.database.entities;

import com.google.gson.JsonArray;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.CustomMethod;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trend {

    private String userName;
    private String title;
    private String place;
    private String hashtags;
    private Integer likes;
    private Date    time;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getTimeAsString(){

        SimpleDateFormat simpleDate =  new SimpleDateFormat(Constants.DateFormat.TRENDFORMAT);
        return simpleDate.format(this.time);

    }

    public static List<Trend> getListFromJson(JSONArray array){
            List<Trend> lst = new ArrayList<>();
            Trend trendObj;
            for(int i =0; i<array.length();i++){
                trendObj = new Trend();
                try {
                    trendObj.setUserName(array.getJSONObject(i).getJSONObject("owner").getString("username"));
                    trendObj.setTitle(array.getJSONObject(i).getString("title"));
                    trendObj.setPlace(array.getJSONObject(i).getString("place"));
                    trendObj.setLikes(array.getJSONObject(i).getJSONObject("meta").getInt("likes"));
                    trendObj.setTime(CustomMethod.StringToDate(array.getJSONObject(i).getString("update")));
                    lst.add(trendObj);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return lst;
    }
}
