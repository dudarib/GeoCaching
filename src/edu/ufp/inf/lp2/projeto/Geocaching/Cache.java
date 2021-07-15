package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Cache implements Serializable {

  public int cache_id;
  public String name;
  public Point point;
  public int difficulty;
  public String zone;
  public User user;
  public Type type;

  public ArrayList<Item> items = new ArrayList<>();
  private final RedBlackBST<Date, Log> logsST = new RedBlackBST<>();

  public enum Type {basic, premium}


  public Cache(int cache_id, String name, Point point, int difficulty, User user) {
    this.cache_id = cache_id;
    this.name = name;
    this.point = point;
    this.difficulty = difficulty;
    this.user = user;
  }

  public Cache(int cache_id, String name, Point point, int difficulty, User user, ArrayList<Item> items) {
    this.cache_id = cache_id;
    this.name = name;
    this.point = point;
    this.difficulty = difficulty;
    this.user = user;
    this.items = items;
  }

  public Cache(int cache_id, Type type, Point point, String zone) {
    this.cache_id = cache_id;
    this.point = point;
    this.type = type;
    this.zone = zone;
  }

  //ITEMS
  /**
   * adds an Item to the Cache
   * @param i
   */
  public void addItem(Item i) {
    for (Item item : this.items) {
      if(item.equals(i)) {
        System.out.println("Item already inserted in the Cache");
        return;
      }
    }
    this.items.add(i);
  }

  /**
   * adds an Item to the Cache and checks Id
   * @param i
   */
  public void addItem_byId(Item i) {
    for (Item item : this.getThisItems()) {
      if (item.getItem_id() == i.getItem_id() ) {
        System.out.println("Item already inserted in the Cache");
        return;
      }
    }
    this.getThisItems().add(i);
  }


  public ArrayList<Item> getThisItems() {
    return items;
  }

  /**
   * Removes Item from Cache by Item
   * @param i - item
   * @return item
   */
  public Item removeItem(Item i) {
    for (Item item : this.items) {
      if(item.equals(i)) {
        this.items.remove(item);
        return item;
      }
    }
    return null;
  }

  /**
   * Removes Item from Cache by ID
   * @param id id of item
   * @return item
   */
  public Item removeItem(int id) {
    for (Item item : this.items) {
      if(item.getItem_id() == id) {
        this.items.remove(item);
        return item;
      }
    }
    return null;
  }

  //LOGS
  /**
   * Add Log to the cache
   * @param log
   */
  public void addLog(Log log) {
    for (Date date : logsST.keys()) {
      Log laux = logsST.get(date);
      if(laux.getData() == log.getData())  {
        System.out.println("This log already exists");
        return;
      }
    }
    logsST.put(log.getData(), log);
    System.out.println("log added!");
  }

  /**
   * Remove log from cache
   * @param log log to delete
   */
  public void remove(Log log) {
    for (Date date : logsST.keys()) {
      Log laux = logsST.get(date);
      if(laux.getData() == log.getData())  {
        logsST.delete(log.getData());
        System.out.println("Log deleted");
        return;
      }
    }
    System.out.println("No matching Log to delete");
  }

  /**
   * Method to print all the logs of the cache
   */
  public void printLogs() {
    for (Date auxDate : logsST.emordem()) {
      Log paux = logsST.get(auxDate);
      System.out.println(paux.toString());
    }
  }


  /**
   * Save cache logs to file
   * @param cachesST ST de caches
   * @param path path to file
   * @param type 0(.TXT) or 1(.BIN)
   */
  public void saveCacheLogsToFile(SeparateChainingHashST<Integer, Cache> cachesST, String path, int type) {
    if(type == 0 ) {
      Out o = new Out(path);
      o.println("Cache_id;DATE;User_id;message");

      for (Date id : logsST.keys()) {
        Log l = logsST.get(id);
        for(Integer cacheid : cachesST.keys()){
          Cache c = cachesST.get(cacheid);
          o.println(
                  c.getCache_id() + ";" +
                          l.getData().getDay() + ";" +
                          l.getData().getMonth() + ";" +
                          l.getData().getYear() + ";" +
                          l.getData().getHour() + ";" +
                          l.getData().getMinute() + ";" +
                          l.getData().getSecond() + ";" +
                          l.getData().getMilisecond()+ ";" +
                          l.getUser().getId_number()+ ";" +
                          l.getMessage());
        }
      }

    } else {
      BinaryOut bo = new BinaryOut(path);
      for (Date id : logsST.emordem()) {
        Log l = logsST.get(id);
        for(Integer cacheid : cachesST.keys()){
          Cache c = cachesST.get(cacheid);
          bo.write(
                  c.getCache_id() + ";" +
                          l.getData().getDay() + ";" +
                          l.getData().getMonth() + ";" +
                          l.getData().getYear() + ";" +
                          l.getData().getHour() + ";" +
                          l.getData().getMinute() + ";" +
                          l.getData().getSecond() + ";" +
                          l.getData().getMilisecond()+ ";" +
                          l.getUser().getId_number()+ ";" +
                          l.getMessage()+ "\n");

        }
      }
    }
    if(type != 0){
      System.out.println("Logs saved .bin");
    } else{
      System.out.println("Logs saved .txt");
    }
  }

  /**
   * Reads info about cache logs from file and add it to the RedBlack of logsST
   *
   * @param cachesST the SeparateChaining of caches
   * @param usersST the SeparateChaining of users
   * @param path the path to the file with the caches info
   */
  public void readCacheLogsFromFile(SeparateChainingHashST<Integer, Cache> cachesST, SeparateChainingHashST<Integer, User> usersST, String path) {
    In in = new In(path);
    in.readLine();

    while (!in.isEmpty()){
      String line = in.readLine();
      String[] fields = line.split(";");

      Cache c = cachesST.get(Integer.parseInt(fields[0]));
      User u = usersST.get(Integer.parseInt(fields[8]));

      Log l = new Log(new Date(Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),Integer.parseInt(fields[4]),Integer.parseInt(fields[5]),Integer.parseInt(fields[6]),Integer.parseInt(fields[7])), u, fields[9]);
      c.addLog(l);
    }
  }


  /**
   Travelbug b = new Travelbug()
   ArrayList<Item> items = new Arraylist<>();
   items.add(b);
   for(Item i : items){
   if(i instanceof Travelbug){

   }
   }
   */


  /**
   * Gets & Sets
   **/
  public String getCache_id() {
    return cache_id +"";
  }

  public void setCache_id(int cache_id) {
    this.cache_id = cache_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPoint() {
    return point.getX() + "," + point.getY();
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Type getType_t() {
    return this.type;
  }

  public Point get_Point() {
    return  this.point;
  }

  public String getItems() {
    String allItems = "";
    if (items.size() > 0) {
      allItems += items.get(0).getName();
      for(int i = 1; i < items.size() ; i++){
        allItems += "," + items.get(i).getName();
      }
    }
    return allItems;
  }

  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  public String getType() {
    return type.toString();
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    this.zone = zone;
  }

  public RedBlackBST<Date, Log> getLogsST() {
    return logsST;
  }

  public Integer getCache_id_int() {
    return this.cache_id;
  }


  @Override
  public String toString() {
    return "Cache{" +
            "cache_id=" + cache_id +
            ", type='" + type + '\'' +
            ", zone='" + zone + '\'' +
            ", point=" + point +
            ", items=" + items +
            ", logsST=" + logsST +
            '}';
  }


}