package com.suslovalex.model;

import androidx.annotation.NonNull;

public class Song {
    private int mId;
    private String mArtist;
    private String mGenre;
    private String mTitle;
    private int mPath;

    public Song(int id, String artist, String genre, String title, int path) {
        mId = id;
        mArtist = artist;
        mGenre = genre;
        mTitle = title;
        mPath = path;
    }

//  public Song(String artist, String genre, String title, int path) {
//      mId++;
//      mArtist = artist;
//      mGenre = genre;
//      mTitle = title;
//      mPath = path;
//  }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getPath() {
        return mPath;
    }

    public void setmPath(int path) {
        mPath = path;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + getId()
                + "Artist: " + getArtist()
                + "Genre: " + getGenre()
                + "Title: " + getTitle()
                + "Path: " + getPath();
    }
}
