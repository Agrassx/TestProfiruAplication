package ru.profi.test.myapplication.model.classes;

public class Friend {
    private String id;
    private String first_name;
    private String last_name;
    private String photo_100;
    private String photo_max_orig;
    private boolean isPhotoLoaded = false;

    public String getFirst_name() {
        return first_name;
    }

    public String getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public String getPhoto_max_orig() {
        return photo_max_orig;
    }

    public boolean isPhotoLoaded() {
        return isPhotoLoaded;
    }

    public void setPhotoLoaded(boolean photoLoaded) {
        isPhotoLoaded = photoLoaded;
    }
}
