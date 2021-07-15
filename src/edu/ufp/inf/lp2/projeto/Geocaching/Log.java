package edu.ufp.inf.lp2.projeto.Geocaching;

public class Log {

  public Date data;
  public User user;
  public String message;


  public Log(Date data, User user, String message) {
    this.data = data;
    this.user = user;
    this.message = message;
  }


  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Log{" +
            "data=" + data +
            ", user_id:" + user.getId_number() +
            ", user name:" + user.getName() +
            ", message='" + message + '\'' +
            '}';
  }
}