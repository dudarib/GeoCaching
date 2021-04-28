package edu.ufp.inf.lp2.projeto.Geocaching;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args)  {

        Network n = new Network();


        n.loadUsers(".//data//users.txt");
        n.loadCaches(".//data//caches.txt", 0);

        //n.printUsers();
        //n.printCaches();
        Item i1 = new Item(0, "pulseira", "normal");

        Point p1 = new Point(10, 20);
        User u2 = new User(11111111, "Ferando Pessoa", 1.0, 4.0);
        Cache c1 = new Cache(5, "Cais de Gaia (Sandeman) ", p1, 3, u2);

        c1.addItem(i1);




        /*
        User u1 = new User(12314412, "Eduardo Ribeiro", 1.0, 4.0);
        Date d1 = new Date(31,1,2020);
        Date d2 = new Date(20,2,2020);
        Point p1 = new Point(10, 20);

        Cache c1 = new Cache(1, "Cache do Monte do Coteiro", p1, 2, u1);

        System.out.println(c1.toString());

         */
    }
}
