package com.whispcorp.whispme.database.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(tableName = Whisp.TB_WHISP,
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "owner",
                onDelete = CASCADE))
public class Whisp {

    public static final String TB_WHISP = "Whisp";

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String serverId;
    private Integer owner;
    private String type;
    private String content;
    private String title;
    private Double latitude;
    private Double longitude;
    private Integer likes;
    private Integer views;
    private Integer comments;

    public Whisp() {
    }

    public Whisp(Integer id, String serverId, Integer owner, String type, String content, String title, Double latitude, Double longitude, Integer likes, Integer views, Integer comments) {
        this.id = id;
        this.serverId = serverId;
        this.owner = owner;
        this.type = type;
        this.content = content;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.likes = likes;
        this.views = views;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /*

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Hero.class && obj == this) {
            return true;
        }

        Hero hero = (Hero) obj;
        return hero.serverId.equals(this.serverId) && hero.name.equals(this.name);
    }


    public static class ProviderBuilder {

        private Hero hero;
        private List<Hero> heroes;

        public ProviderBuilder(Hero hero) {
            this.hero = hero;
        }

        public ProviderBuilder(List<Hero> heroes) {
            this.heroes = heroes;
        }

        // JSONObject -> JavaObject
        public static ProviderBuilder from(JSONObject jsonSource) {
            try {
                return new ProviderBuilder(new Hero()
                        .setServerIdBuilder(jsonSource.getString("id"))
                        .setNameBuilder(jsonSource.getString("login")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        // JSONArray -> JavaList
        public static ProviderBuilder from(JSONArray jsonSources) {
            int length = jsonSources.length();
            List<Hero> sources = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    ProviderBuilder providerBuilder = ProviderBuilder.from(jsonSources.getJSONObject(i));
                    if (providerBuilder != null) {
                        sources.add(providerBuilder.build());
                    }
                } catch (JSONException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return new ProviderBuilder(sources);
        }


        public Hero build() {
            return hero;
        }

        public List<Hero> buildAll() {
            return heroes;
        }
    }

    */

}