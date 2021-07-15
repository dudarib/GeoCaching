package edu.ufp.inf.lp2.projeto.Geocaching;


import edu.princeton.cs.algs4.RedBlackBST;

public class TravelBug extends Item {

    public String travelbug_id;
    public User user;
    public Cache cache1;
    public Cache cache2;

    private final RedBlackBST<Date, LogTravelbug> Travelbugs_logsST = new RedBlackBST<>();


    public TravelBug(int item_id, String name, String type, String travelbug_id, User user, Cache cache1, Cache cache2) {
        super(item_id, name, type);
        this.travelbug_id = travelbug_id;
        this.user = user;
        this.cache1 = cache1;
        this.cache2 = cache2;
    }

    public TravelBug(String name, String travelbug_id, User user, Cache cache1, Cache cache2) {
        super(name);
        this.travelbug_id = travelbug_id;
        this.user = user;
        this.cache1 = cache1;
        this.cache2 = cache2;
    }


    /**
     * Adds a log to the Travelbug
     * @param cache cache it currently is
     * @param user user moving the travelbug
     * @param action Travelbug mission
     * @param date Now() Date
     */
    public void addLog(Cache cache,User user, String action, Date date){
        for (Date data : Travelbugs_logsST.keys()) {
            LogTravelbug laux = Travelbugs_logsST.get(data);
            if(laux.getData() == date)  {
                System.out.println("This log already exists");
                return;
            }
        }
        Travelbugs_logsST.put(date, new LogTravelbug(cache, user, action, date));
        System.out.println("log added to travelbug!");
    }

    /**
     * remove Log from travelBug
     * @param log log to delete
     */
    public void removeLog(Log log) {
        for (Date date : Travelbugs_logsST.keys()) {
            LogTravelbug laux = Travelbugs_logsST.get(date);
            if(laux.getData() == log.getData())  {
                Travelbugs_logsST.delete(log.getData());
                System.out.println("Log deleted");
                return;
            }
        }
        System.out.println("No matching Log to delete");
    }

    /**
     * Prints all Logs of the travelbug
     */
    public void printLogs() {
        for (Date auxDate : Travelbugs_logsST.keys()) {
            LogTravelbug paux = Travelbugs_logsST.get(auxDate);
            System.out.println(paux.toString());
        }
    }


    /**
     * GETS AND SETS
     */
    public String getTravelbug_id() {
        return travelbug_id;
    }

    public void setTravelbug_id(String travelbug_id) {
        this.travelbug_id = travelbug_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cache getCache1() {
        return cache1;
    }

    public void setCache1(Cache cache1) {
        this.cache1 = cache1;
    }

    public Cache getCache2() {
        return cache2;
    }

    public void setCache2(Cache cache2) {
        this.cache2 = cache2;
    }

    public RedBlackBST<Date, LogTravelbug> getTravelbugs_logsST() {
        return Travelbugs_logsST;
    }


    @Override
    public String toString() {
        return "TravelBug{" +
                " id='" + travelbug_id + '\'' +
                ", user=" + user.getName() +
                ", ID_cache1=  " + cache1.getCache_id() +
                ", ID_cache2= " + cache2.getCache_id() + " " +
                ", Travelbugs_logsST=" + Travelbugs_logsST +
                '}';
    }
}