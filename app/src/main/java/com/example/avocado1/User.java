package com.example.avocado1;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static User u;
    private String id;
    private String Email;
    private  String Password;
    private  String UserName;
    private List<String> Preferences = new ArrayList<>();
    private List<String> FavoriteMovies = new ArrayList<>();
    private List<String> FavoriteTVshows = new ArrayList<>();


    public User () {

    }

    public User(String id, String email, String password, String userName, List preferences, List favmovies, List favTVshows) {
        this.id = id;
        Email = email;
        Password = password;
        UserName = userName;
        Preferences = preferences;
        FavoriteMovies= favmovies;
        FavoriteTVshows= favTVshows;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public List<String> getPreferences() {
        return Preferences;
    }

    public void setPreferences(List<String> preferences) {
        Preferences = preferences;
    }

    public List<String> getFavoriteMovies() {
        return FavoriteMovies;
    }

    public void setFavoriteMovies(List<String> favoriteMovies) {
        FavoriteMovies = favoriteMovies;
    }

    public List<String> getFavoriteTVshows() {
        return FavoriteTVshows;
    }

    public void setFavoriteTVshows(List<String> favoriteTVshows) {
        FavoriteTVshows = favoriteTVshows;
    }
}



