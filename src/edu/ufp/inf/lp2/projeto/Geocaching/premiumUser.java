package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.SeparateChainingHashST;


public class premiumUser extends User {


    public premiumUser(int id_number, String name, Point point, SeparateChainingHashST<Integer, Cache> caches_userST) {
        super(id_number, name, point, caches_userST);
    }

    public premiumUser(int id_number, String name, double x, double y, SeparateChainingHashST<Integer, Cache> caches_userST) {
        super(id_number, name, x, y, caches_userST);
    }

    public premiumUser(int id_number, String name, Point point) {
        super(id_number, name, point);
    }

    public premiumUser(int id_number, String name, double x, double y) {
        super(id_number, name, x, y);
    }
}