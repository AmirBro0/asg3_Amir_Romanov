package model;

import java.util.ArrayList;
import java.util.List;

public class Series extends MediaContent {

    private List<Episodes> episodes;

    public Series(int id, String title, List<Episodes> episodes) {
        super(id, title);
        this.episodes = episodes;
    }
    public Series(int id, String title,int duration) {
        super(id, title);

    }


    public Series(int id, String title) {
        super(id, title);
        this.episodes = new ArrayList<>();
    }

    public Series(int id, String title,String type,int duration) {
        super(id, title);
    }

    public List<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episodes> episodes) {
        this.episodes = episodes != null ? episodes : new ArrayList<>();
    }

    public void addEpisode(Episodes episode) {
        this.episodes.add(episode);
    }

    @Override
    public int getDuration(){
        int minutes = 0;
        for(Episodes e: episodes){
            minutes += e.getMinute();
        }
        return minutes;
    }

    @Override
    public String getType() {
        return "SERIES";
    }




}
