package model;

public class Episodes{
    private String episode_name;
    private int minute;

    public Episodes(String episode_name , int minute){
        this.episode_name = episode_name;
        this.minute += minute;
    }

    public int getMinute() {
        return minute;
    }

    public String toString(){
        return "Episode name: " + episode_name + "\nDuration: " + minute + "\n";
    }
}
