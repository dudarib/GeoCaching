package edu.ufp.inf.lp2.projeto.Geocaching;

import java.util.ArrayList;
import java.util.List;

public class User {

  public int id_number;

  public String name;

  //public String type;

  //public RedBlackBST<Cache> caches;

  public Point point;

  public List<Cache> caches_userST = new ArrayList<>();


  public User(int id_number, String name, Point point, List<Cache> caches_userST) {
    this.id_number = id_number;
    this.name = name;
    this.point = point;
    this.caches_userST = caches_userST;
  }

  public User(int id_number, String name, double x, double y, List<Cache> caches_userST) {
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

  public List<Cache> getCaches_userST() {
    return caches_userST;
  }

  public void setCaches_userST(List<Cache> caches_userST) {
    this.caches_userST = caches_userST;
  }

  @Override
  public String toString() {
    return "User{" +
            "id_number=" + id_number +
            ", name='" + name + '\'' +
            ", point=" + point +
            ", caches_userST=" + caches_userST +
            '}';
  }
}