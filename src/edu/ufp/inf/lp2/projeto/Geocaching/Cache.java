package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;
import java.util.List;

public class Cache {

  public int cache_id;
  public String name;
  public Point point;
  public int difficulty;
  /* apenas 1 user ?? */
  public User user;

  //public RedBlackBST<Item> items;
  private ArrayList<Item> items = new ArrayList<>();
  //public RedBlackBST<Log> logs;
  private RedBlackBST<Date, Log> logsST = new RedBlackBST<>();

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
    for (Item item : this.getItems()) {
      if (item.getItem_id() == i.getItem_id() ) {
        System.out.println("Item already inserted in the Cache");
        return;
      }
    }
    this.getItems().add(i);
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
   * @param id
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

  /**
   * Add Log to the cache
   * @param log
   * @param message
   */
  public void addLog(Log log, String message) {

    log.setMessage(message);
    this.logsST.put(log.getData(), log);
  }



/**Gets & Sets**/
  public int getCache_id() {
    return cache_id;
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

  public Point getPoint() {
    return point;
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

  public ArrayList<Item> getItems() {
    return items;
  }

  public void setItems(ArrayList<Item> items) {
    this.items = items;
  }

  public RedBlackBST<Date, Log> logsST() {
    return logsST;
  }

  @Override
  public String toString() {
    return "Cache{" +
            "cache_id=" + cache_id +
            ", name='" + name + '\'' +
            ", point=" + point +
            ", difficulty=" + difficulty +
            ", user=" + user +
            ", items=" + items +
            ", logsST=" + logsST +
            '}';
  }


}