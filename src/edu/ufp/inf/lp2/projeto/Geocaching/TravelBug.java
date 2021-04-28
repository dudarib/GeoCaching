package edu.ufp.inf.lp2.projeto.Geocaching;


public class TravelBug extends Item {

  public int travelbug_id;

  public String mission;

      public premiumUser user;

    public TravelBug(int item_id, String name, String type) {
        super(item_id, name, type);
    }

    public TravelBug(int item_id, String name, String type, int travelbug_id, String mission, premiumUser user) {
        super(item_id, name, type);
        this.travelbug_id = travelbug_id;
        this.mission = mission;
        this.user = user;
    }

    public int getTravelbug_id() {
        return travelbug_id;
    }

    public void setTravelbug_id(int travelbug_id) {
        this.travelbug_id = travelbug_id;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public premiumUser getUser() {
        return user;
    }

    public void setUser(premiumUser user) {
        this.user = user;
    }
}