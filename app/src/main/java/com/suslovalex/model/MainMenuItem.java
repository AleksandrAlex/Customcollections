package com.suslovalex.model;

/**
 * MainMenuItem is variable of MainMenu.
 */

public class MainMenuItem {
    private String mTitle;
    private String mDescription;
    private int mImageId;

    /**
     *
     * @param title is a name of menu.
     * @param description describe menu.
     * @param imageId is ID for menu image view
     */

    public MainMenuItem(String title, String description, int imageId) {
        mTitle = title;
        mDescription = description;
        mImageId = imageId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }
}
