package model;

public class Episodes {

    private String episodeName;
    private int minute;



    public Episodes(String episodeName, int minute) {
        this.episodeName = episodeName;
        this.minute = minute;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "Episode{name='" + episodeName + "', minute=" + minute + "}";
    }
}
