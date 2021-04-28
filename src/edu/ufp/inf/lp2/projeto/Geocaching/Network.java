package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;

public class Network {

    private RedBlackBST<Integer, Cache> cachesST = new RedBlackBST<>();

    private RedBlackBST<Integer, User> usersST = new RedBlackBST<>();


    public static int countCacheID;



    /**
     * Load Stations from a file (0, for .txt, 1 for .bin)
     *
     * @param path
     * @param type
     */
    public void loadCaches(String path, int type) {
        if(type == 0) {
            In in = new In(path);
            while (!in.isEmpty()) {
                String[] split = in.readLine().split(";");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                double x = Double.parseDouble(split[2]);
                double y = Double.parseDouble(split[3]);
                int difficulty = Integer.parseInt(split[4]);
                int id_number = Integer.parseInt(split[5]);
                Point p = new Point(x, y);

                Cache aux = new Cache(id, name, p, difficulty, usersST.get(id_number));
                this.cachesST.put(id, aux);
                Network.countCacheID = id;
            }
        }
        else {
            BinaryIn bin = new BinaryIn(path);
            while (!bin.isEmpty()) {
                String[] split = bin.readString().split(";");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                double x = Double.parseDouble(split[2]);
                double y = Double.parseDouble(split[3]);
                int difficulty = Integer.parseInt(split[4]);
                int id_number = Integer.parseInt(split[5]);
                Point p = new Point(x, y);

                Cache aux = new Cache(id, name, p, difficulty, usersST.get(id_number));
                this.cachesST.put(id, aux);
                Network.countCacheID = id;
            }

        }
        Network.countCacheID++;
    }

    /**
     * Print Stations on Console
     */
    public void printCaches() {
        for (Integer auxInteger : cachesST.keys()) {
            Cache paux = cachesST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }

    //USERS

    /**
     * Load Passengers from a file
     *
     * @param path
     */
    public void loadUsers(String path) {
        In in = new In(path);
        while (!in.isEmpty()) {
            String[] split = in.readLine().split(";");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            double x = Double.parseDouble(split[2]);
            double y = Double.parseDouble(split[3]);

            Point p = new Point(x,y);
            User aux = new User(id, name, p);
            usersST.put(id, aux);

        }
    }

    /**
     * Print Passangers in Console
     */
    public void printUsers() {
        for (Integer auxInteger : usersST.keys()) {
            User paux = usersST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }




    /**Gets e Sets**/
    public RedBlackBST<Integer, Cache> getCachesST() {
        return cachesST;
    }

    public void setCachesST(RedBlackBST<Integer, Cache> cachesST) {
        this.cachesST = cachesST;
    }

    public RedBlackBST<Integer, User> getUsersST() {
        return usersST;
    }

    public void setUsersST(RedBlackBST<Integer, User> usersST) {
        this.usersST = usersST;
    }
}
