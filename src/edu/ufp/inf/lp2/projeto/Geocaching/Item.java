package edu.ufp.inf.lp2.projeto.Geocaching;


public class Item {

  public int item_id;
  public String name;
  public String type;

  public Item(int item_id, String name, String type) {
    this.item_id = item_id;
    this.name = name;
    this.type = type;
  }

  public int getItem_id() {
    return item_id;
  }

  public void setItem_id(int item_id) {
    this.item_id = item_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}