package model;

public class Series extends MediaContent {

    private Episodes[] episodes1;

    public Series(int id, String title,Episodes[] episodes1) {
        super(id, title);
        this.episodes1 = episodes1;
    }
    public Series(int id, String title,int duration) {
        super(id, title);
        this.episodes1 = new Episodes[0];
    }

    public Series(int id, String title,String type,int duration) {
        super(id, title);
        this.episodes1 = new Episodes[0];
    }

    @Override
    public int getDuration(){
        int minutes = 0;
        for(Episodes e: episodes1){
            minutes = e.getMinute();
        }
        return minutes;
    }

    @Override
    public String getType() {
        return "SERIES";
    }

    public void getEpisodesName(){
        for(Episodes e: episodes1){
            System.out.println(e.toString());
        }
    }


}
