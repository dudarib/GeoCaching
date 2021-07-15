package edu.ufp.inf.lp2.projeto.Geocaching;

import edu.princeton.cs.algs4.*;

public class Network {

    private RedBlackBST<Integer, Cache> cachesST = new RedBlackBST<>();

    private RedBlackBST<Integer, User> usersST = new RedBlackBST<>();


    public static int countCacheID;


    /**
     * Load input from a file (0, for .txt, 1 for .bin)
     *
     * @param path
     * @param type
     */
    public void loadInput(String path, int type) {
        if(type == 0) {
            In in = new In(path);
            while (!in.isEmpty()) {
                String[] split = in.readLine().split(",");
                int n_users = Integer.parseInt(split[0]);
                while (n_users > 0) {
                    int id = Integer.parseInt(split[1]);
                    String name = split[2];
                    String user_type = split[3];

                    User user_aux = new User(id, name, user_type);
                    this.usersST.put(id, user_aux);

                    n_users --;
                }

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
     * Load Caches from a file (0, for .txt, 1 for .bin)
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
     * Save Caches to file
     * @param path
     * @param type
     */
    public void saveCaches(String path, int type) {
        if(type == 0 ) {
            Out o = new Out(path);

            for (Integer id : cachesST.keys()) {
                Cache aux = cachesST.get(id);
                o.println(
                        aux.getCache_id() + ";" +
                                aux.getName() + ";" +
                                aux.get_Point().getX() + ";" +
                                aux.get_Point().getY() + ";" +
                                aux.getDifficulty() + ";" +
                                aux.getUser().getId_number());
            }
        } else {
            BinaryOut bo = new BinaryOut(path);
            for (Integer id : cachesST.keys()) {
                Cache aux = cachesST.get(id);
                bo.write(
                        aux.getCache_id() + ";" +
                                aux.getName() + ";" +
                                aux.get_Point().getX() + ";" +
                                aux.get_Point().getY() + ";" +
                                aux.getDifficulty() + ";" +
                                aux.getUser().getId_number() + "\n");
            }

        }
        if(type != 0){
            System.out.println("Caches saved .bin");
        } else{
            System.out.println("Caches saved .txt");
        }

    }




    /**
     * Print Caches on Console
     */
    public void printCaches() {
        for (Integer auxInteger : cachesST.keys()) {
            Cache paux = cachesST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }

    //USERS

    /**
     * Load Users from a file
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
     * Print Users in Console
     */
    public void printUsers() {
        for (Integer auxInteger : usersST.keys()) {
            User paux = usersST.get(auxInteger);
            System.out.println(paux.toString());
        }
    }

    /**
     * Reads info about caches from file and add it to the SeparateChaining of caches
     *
     * @param cachesST the SeparateChaining of caches
     * @param path the path to the file with the caches info
     */
    private static void readCachesFromFile(SeparateChainingHashST<Integer, Cache> cachesST, String path) {
        In in = new In(path);
        int n_regioes = Integer.parseInt(in.readLine());
        for(int j = 0; j < n_regioes ; j++){
            String line = in.readLine();
            String regiao = line.split(",")[0];
            int n_caches = Integer.parseInt(line.split(",")[1]);
            for (int n = 0; n < n_caches ; n++){
                line = in.readLine();
                String[] fields = line.split(";");

                Point p = new Point(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
                Cache c = new Cache(Integer.parseInt(fields[0]), Cache.Type.valueOf(fields[1]), p, regiao);  //TODO Colocar regiao no construtor

                for(int i = 0; i<Integer.parseInt(fields[4]);i++){
                    Item iaux = new Item(fields[5+i]);
                    c.addItem(iaux);
                }

                cachesST.put(c.getCache_id_int(), c);
            }
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
