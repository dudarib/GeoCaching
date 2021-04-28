package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.RedBlackBST;


public class Log {

  /**preciso de Log_id??**/
  public int log_id;
  public Date data;

  private RedBlackBST<Date, User> users = new RedBlackBST<>();
  public String message;

  public Log(RedBlackBST<Date, User> users, String message) {
    this.users = users;
    this.message = message;
  }

  public RedBlackBST<Date, User> getUsers() {
    return users;
  }

  public void setUsers(RedBlackBST<Date, User> users) {
    this.users = users;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }
}