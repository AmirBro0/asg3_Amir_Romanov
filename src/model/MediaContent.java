package model;

public abstract class MediaContent {
    protected int id;
    protected String title;

    public MediaContent(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public abstract int getDuration();
    public abstract String getType();

    public String getTitle() {
        return title;
    }
}