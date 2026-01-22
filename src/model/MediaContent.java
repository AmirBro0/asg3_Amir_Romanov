package model;

public abstract class MediaContent {
    protected int id;
    protected String title;

    public MediaContent(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public abstract int getDuration();
    public abstract String getType();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String toString() {
        return "Media{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", type='" + getType() + '\'' +
                ", duration=" + getDuration() +
                '}';
    }
}