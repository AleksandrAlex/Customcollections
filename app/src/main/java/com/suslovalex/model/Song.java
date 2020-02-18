package com.suslovalex.model;

public class Song {
    private int mId;
    private String mArtist;
    private String mGenre;
    private String mTitle;
    private String mPath;

    public Song(int id, String artist, String genre, String title, String path) {
        mId = mId;
        mArtist = artist;
        mGenre = genre;
        mTitle = title;
        mPath = path;
    }

    public Song(String artist, String genre, String title, String path) {
        mArtist = artist;
        mGenre = genre;
        mTitle = title;
        mPath = path;
    }

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

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }
}
