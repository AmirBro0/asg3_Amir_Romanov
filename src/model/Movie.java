package model;

public class Movie extends MediaContent {
    private int minutes;

    public Movie(int id, String title, int minutes) {
        super(id, title);
        this.minutes = minutes;
    }
    public Movie(int id, String title,String type ,int minutes ) {
        super(id, title);
        this.minutes = minutes;
    }

    @Override
    public int getDuration() {
        return minutes;
    }

    @Override
    public String getType() {
        return "MOVIE";
    }
}
