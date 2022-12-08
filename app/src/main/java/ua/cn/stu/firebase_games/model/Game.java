package ua.cn.stu.firebase_games.model;

import java.io.Serializable;

public class Game implements Serializable {

    private String id;
    private String name;
    private String genre;
    private String info;
    private String hours;
    private String url;

    public Game() {
    }

    public Game(String id, String name,
                String genre, String info,
                String hours, String url) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.info = info;
        this.hours = hours;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
