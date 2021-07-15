package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class User {

  public int id_number;
  public String name;
  public String type;
  public Point point;

  public SeparateChainingHashST<Integer, Cache> caches_userST = new SeparateChainingHashST<>();

  // List of caches visited by User - key is the Date (time it was visited)
  public RedBlackBST<Date, Cache> visit_historyST = new RedBlackBST<>();
  private ArrayList<Item> itemsUser = new ArrayList<>();



  public User(int id_number, String name, Point point, SeparateChainingHashST<Integer, Cache> caches_userST) {
    this.id_number = id_number;
    this.name = name;
    this.point = point;
    this.caches_userST = caches_userST;
  }

  public User(int id_number, String name, double x, double y, SeparateChainingHashST<Integer, Cache> caches_userST) {
    this.id_number = id_number;
    this.name = name;
    this.point = new Point(x, y);
    this.caches_userST = caches_userST;
  }

  public User(int id_number, String name, Point point) {
    this.id_number = id_number;
    this.name = name;
    this.point = point;
  }

  public User(int id_number, String name, double x, double y) {
    this.id_number = id_number;
    this.name = name;
    this.point = new Point(x, y);
  }

  public User(int id_number, String name, String type) {
    this.id_number = id_number;
    this.name = name;
    this.type = type;
  }


  /**
   * Adds a new cache to the users's ArrayList (Caches created by)
   * @param c the cache to add
   */
  public void addCache(Cache c) {
    if (this.caches_userST.contains(c.getCache_id_int())) {
      System.out.println("That user already has this cache");
      return;
    }
    this.caches_userST.put(c.getCache_id_int(), c);
  }

  /**
   * Prints all Caches the user created
   */
  public void printAllCaches() {
    for (Integer key : this.caches_userST.keys()) {
      Cache c = this.caches_userST.get(key);
      System.out.println(c);
    }
  }


  /**
   * This method makes this User visit a Cache, and leave the Log in there.
   * The user can add or remove items to the cache
   * @param c cache
   * @param inseridos item to add to cache
   * @param retirados item to remove from cache
   * @param date date of the log
   * @param u1 user
   * @param message message to add to log
   */
  public void visitCache(Cache c, ArrayList<Item> inseridos, ArrayList<Item> retirados, Date date, User u1, String message) {

    if(c.getType_t() == Cache.Type.premium && u1.getType().equals("basic")){
      System.out.println(u1.getName() +" Sendo um user " +u1.getType() + " nao tem acesso a Cache" + c.getCache_id());
    } else{
      if(inseridos != null){
        for(Item i : inseridos){
          c.addItem(i);
          //u1.removeItem(i);
        }
      }
      if(retirados != null) {
        for(Item i : retirados){
          c.removeItem(i);
          u1.addItem(i);
        }
      }

      c.addLog(new Log(date, u1, message));
      this.visit_historyST.put(date, c);
    }
  }


  /**
   * Prints all the User Visited Caches acessing to the visit_history RedBlackBST
   */
  public void printAllCachesVisited() {
    for (Date auxDate : visit_historyST.keys()) {
      Cache caux = visit_historyST.get(auxDate);
      System.out.println(caux);
    }

  }

  public ArrayList<Cache> getAllCachesVisited() {
    ArrayList<Cache> cacheArrayList = new ArrayList<>();
    for (Date auxDate : visit_historyST.keys()) {
      Cache caux = visit_historyST.get(auxDate);
      cacheArrayList.add(caux);
    }
    return cacheArrayList;
  }


  public void insertCacheVisitHistory(Cache c, Date date) {
    this.visit_historyST.put(date, c);

  }


  /**
   * adds an Item to the User
   * @param i
   */
  public void addItem(Item i) {
    for (Item item : this.itemsUser) {
      if(item.equals(i)) {
        System.out.println("User already has this item");
        return;
      }
    }
    this.itemsUser.add(i);
  }

  /**
   * Removes Item from user by Item
   * @param i - item
   * @return item
   */
  public Item removeItem(Item i) {
    for (Item item : this.itemsUser) {
      if(item.equals(i)) {
        this.itemsUser.remove(item);
        return item;
      }
    }
    return null;
  }

  /**
   * Get Users that visited more caches
   *
   * @param usersST redblack airport
   */
  public void getUsersWithMostVisits(SeparateChainingHashST<Integer, User> usersST, Date datainicial, Date datafinal) {
    int max = 0;
    String username = null;

    for (Integer id : usersST.keys()) {
      User u = usersST.get(id);
      if(u.visit_historyST.size() > max ){
        max = u.visit_historyST.size();
        username = u.getName();
      }
    }
    System.out.println("O User " +username+ " visitou " +max+ " Caches");
  }

  /**
   * Save visit history to file
   * @param path path to file
   * @param type 0(.TXT) or 1(.BIN)
   */
  public void saveVisitHistoryToFile(SeparateChainingHashST<Integer, User> usersST, String path, int type) {
    if(type == 0 ) {
      Out o = new Out(path);
      o.println("User_ID;DATE;Cache_ID");

      for (Date id : visit_historyST.emordem()) {
        Cache c = visit_historyST.get(id);
        for(Integer userID : usersST.keys()){
          User u = usersST.get(userID);
          o.println(
                  u.getId_number() + ";" +
                          id.getDay() + ";" +
                          id.getMonth() + ";" +
                          id.getYear() + ";" +
                          id.getHour() + ";" +
                          id.getMinute() + ";" +
                          id.getSecond() + ";" +
                          id.getMilisecond() + ";" +
                          c.getCache_id());
        }
      }

    } else {
      BinaryOut bo = new BinaryOut(path);
      for (Date id : visit_historyST.emordem()) {
        Cache c = visit_historyST.get(id);
        for(Integer userID : usersST.keys()){
          User u = usersST.get(userID);
          bo.write(
                  u.getId_number() + ";" +
                          id.getDay() + ";" +
                          id.getMonth() + ";" +
                          id.getYear() + ";" +
                          id.getHour() + ";" +
                          id.getMinute() + ";" +
                          id.getSecond() + ";" +
                          id.getMilisecond() + ";" +
                          c.getCache_id()+ "\n");
        }
      }
    }
    if(type != 0){
      System.out.println("Visit_history saved .bin");
    } else{
      System.out.println("visit_history saved .txt");
    }
  }

  /**
   * Reads info about visits_history from file and add it to the SeparateChaining of visits_history
   *
   * @param cachesST the SeparateChaining of caches
   * @param usersST the SeparateChaining of users
   * @param path the path to the file with the caches info
   */
  public void readVisitsHistoryFromFile(SeparateChainingHashST<Integer, User> usersST, SeparateChainingHashST<Integer, Cache> cachesST, String path) {
    In in = new In(path);
    in.readLine();

    while (!in.isEmpty()){
      String line = in.readLine();
      String[] fields = line.split(";");

        User user = usersST.get(Integer.parseInt(fields[0]));
        user.visit_historyST.put(new Date(Integer.parseInt(fields[1]),Integer.parseInt(fields[2]),Integer.parseInt(fields[3]),Integer.parseInt(fields[4]),Integer.parseInt(fields[5]),Integer.parseInt(fields[6]),Integer.parseInt(fields[7])), cachesST.get(Integer.parseInt(fields[8])));
    }
  }



  /**
   * Gets and SETS
   */
  public int getId_number() {
    return id_number;
  }

  public void setId_number(int id_number) {
    this.id_number = id_number;
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

  public SeparateChainingHashST<Integer, Cache> getCaches_userST() {
    return caches_userST;
  }

  public void setCaches_userST(SeparateChainingHashST<Integer, Cache> caches_userST) {
    this.caches_userST = caches_userST;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public RedBlackBST<Date, Cache> getVisit_historyST() {
    return visit_historyST;
  }

  public ArrayList<Item> getItemsUser() {
    return itemsUser;
  }

  public void setItemsUser(ArrayList<Item> itemsUser) {
    this.itemsUser = itemsUser;
  }


  @Override
  public String toString() {
    return "User{" +
            "id_number=" + id_number +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", point=" + point +
            ", caches_userST=" + caches_userST +
            ", visit_historyST=" + visit_historyST +
            ", itemsUser=" + itemsUser +
            '}';
  }


}