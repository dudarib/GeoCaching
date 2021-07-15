package edu.ufp.inf.lp2.projeto.Geocaching;


import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.projeto.Geocaching.javafx.MainGeocache;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static final String FILE_DELIMETER = ";";

    private static final SeparateChainingHashST<Integer, User> usersST = new SeparateChainingHashST<>();
    private static final SeparateChainingHashST<Integer, Cache> cachesST = new SeparateChainingHashST<>();
    private static final SeparateChainingHashST<String, TravelBug> travelbugsST = new SeparateChainingHashST<>();



    public static void main(String[] args) throws InterruptedException {

        /** Read all Data from txt */
        readCachesFromFile(cachesST, ".//data//caches.txt");
        readUsersFromFile(usersST, ".//data//users.txt");
        readTravelbugsFromFile(travelbugsST, usersST, cachesST, ".//data//travelbugs.txt");

        //User u1 = usersST.get(4);
        //Cache c1 = cachesST.get(14);
        //u1.readVisitsHistoryFromFile(usersST, cachesST, ".//data//visits_history.txt");
        //c1.readCacheLogsFromFile(cachesST, usersST, ".//data//cache_logs.txt");


        /** prints all STs */
        //printAllUsers(usersST);
        //printAllCaches(cachesST);
        //printAllTravelbugs(travelbugsST);

        //client1();

        /**client to Run the input File**/
        //client2(cachesST, usersST, travelbugsST);


        /** save all Data from STs */
        //saveCachesToFile(cachesST, ".//data//caches.txt", 0);
        //saveUsersToFile(usersST, ".//data//users.txt", 0);
        //saveTravelbugsToFile(travelbugsST, ".//data//travelbugs.txt", 0);

        //c1.printLogs();
        //printAllCachesVisitedBy(u1);

        //u1.saveVisitHistoryToFile(usersST, ".//data//visits_history.txt", 0);
        //c1.saveCacheLogsToFile(cachesST, ".//data//cache_logs.txt", 0);



        //////////////////////////////////////////TESTE Grafos//////////////////////////////////////////////

        Rede r = null;


        /**
         * Load Graph from Txt
         */
        try {

            In in = new In(".//data//connections.txt");

            while (!in.isEmpty()) {

                int V = Integer.parseInt(in.readLine());
                r = new Rede(V);
                int E = Integer.parseInt(in.readLine());
                String line = in.readLine();
                while (line != null) {

                    String[] fields = line.split(",");
                    int v = Integer.parseInt(fields[0]);
                    int w = Integer.parseInt(fields[1]);
                    double distance = Double.parseDouble(fields[2]);
                    double time = Double.parseDouble(fields[3]);

                    r.addEdge(new Connection(v, w, distance, time));

                    line = in.readLine();
                    //System.out.println(v+" connections added");
                }
                //System.out.println(r.toString());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        assert r != null;
        /** ADDiciona uma nova conexao entre dois pontos **/
        //r.addEdge(new Connection(2,8,200, 2500));
        //r.addEdge(new Connection(18, 3, 230.0, 2800.0));
        //System.out.println(r.toString());

        /**
         * caminhos mais curtos
         */
        /**caminho mais curto em (metros)**/
        //r.shortestPathFromTo(r, 1, 15, cachesST);

        /**caminho mais curto de um ponto para todos os outros**/
        //r.shortestPathFromToAll(r,15, cachesST);

        /**caminho mais rapido (min)**/
        //r.fastestPath(r, 1, 15, cachesST);  //TODO encontrar o valor tempo no DJIKSTRA (como aceder ao valor tempo)

        //r.isConnected(r);

        //r.getCachesInZone("norte", cachesST);
        //r.getCachesInZone("centro", cachesST);
        //r.getCachesInZone("sul", cachesST);


        /**Subgraph de premiums**/
        Rede redePremium = r.createPremiumSubgraph(r, cachesST);
        //System.out.println(r.createPremiumSubgraph(r, cachesST).toString());
        //r.shortestPathFromToAll(redePremium, 10, cachesST);

        /**Subgraph por zona**/
        Rede redeZona = r.createPremiumSubgraph(r, cachesST);
        //System.out.println(r.createZonaSubgraph(r, "centro", cachesST).toString());
        //redeZona.shortestPathFromToAll(redeZona, 10, cachesST);


        /**Problema do caixeiro-viajante**/
        r.caixeiro_viajante(r,9, 80, cachesST);

        //r.saveNetwork(r, ".//data//connections.txt", true);

    }

    ////////////////////////////////////////// Functions //////////////////////////////////////////////

    /**
     * Reads info about caches from file and add it to the SeparateChaining of caches
     *
     * @param cachesST the SeparateChaining of caches
     * @param path the path to the file with the caches info
     */
    private static void readCachesFromFile(SeparateChainingHashST<Integer, Cache> cachesST, String path) {
        In in = new In(path);
        in.readLine();

        while (!in.isEmpty()){
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);

            Point p = new Point(Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
            Cache c = new Cache(Integer.parseInt(fields[0]), Cache.Type.valueOf(fields[1]), p, fields[4]);  //TODO Colocar regiao no construtor

            for(int i = 0; i<Integer.parseInt(fields[5]);i++){
                Item iaux = new Item(fields[6+i]);
                c.addItem(iaux);
            }

            cachesST.put(c.getCache_id_int(), c);
        }
    }

    /**
     * Reads info about users from file and add it to the SeparateChaining of users
     *
     * @param usersST the SeparateChaining of users
     * @param path    the path to the file with the users info
     */
    private static void readUsersFromFile(SeparateChainingHashST<Integer, User> usersST, String path) {
        In in = new In(path);
        in.readLine();

        while (!in.isEmpty()){
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);

            User u  = new User(Integer.parseInt(fields[0]),fields[1], fields[2]);
            usersST.put(u.getId_number(), u);
        }
    }

    /**
     * Reads info about Travelbugs from file and add it to the SeparateChaining of Travelbugs
     *
     * @param travelbugsST the SeparateChaining of travelbugs
     * @param path    the path to the file with the travelbugs info
     */
    private static void readTravelbugsFromFile(SeparateChainingHashST<String, TravelBug> travelbugsST, SeparateChainingHashST<Integer, User> usersST, SeparateChainingHashST<Integer, Cache> cachesST, String path) {
        In in = new In(path);
        in.readLine();

        while (!in.isEmpty()){
            String line = in.readLine();
            String[] fields = line.split(FILE_DELIMETER);

            User u = usersST.get(Integer.parseInt(fields[1]));
            Cache c1 = cachesST.get(Integer.parseInt(fields[2]));
            Cache c2 = cachesST.get(Integer.parseInt(fields[3]));

            TravelBug tb = new TravelBug("Travelbug", fields[0], u, c1, c2);
            travelbugsST.put(tb.getTravelbug_id(), tb);
        }
    }

    /**
     * Save Caches to file
     * @param path path to file
     * @param type 0(.TXT) or 1(.BIN)
     */
    public static void saveCachesToFile(SeparateChainingHashST<Integer, Cache> cachesST, String path, int type) {
        if(type == 0 ) {
            Out o = new Out(path);

            o.println("cache_id;cache_type;lat;long;zone;n_items;items[]");
            for (Integer id : cachesST.keys()) {
                Cache aux = cachesST.get(id);
                if(aux.items.size() == 0){
                    o.println(
                            aux.getCache_id() + ";" +
                                    aux.getType() + ";" +
                                    aux.get_Point().getX() + ";" +
                                    aux.get_Point().getY() + ";" +
                                    aux.getZone() + ";" +
                                    aux.items.size());
                } else {
                    o.print(
                            aux.getCache_id() + ";" +
                                    aux.getType() + ";" +
                                    aux.get_Point().getX() + ";" +
                                    aux.get_Point().getY() + ";" +
                                    aux.getZone() + ";" +
                                    aux.items.size() + ";");
                    for (int i = 0; i < aux.items.size(); i++) {
                        o.print(aux.items.get(i).getName() +";");
                    }
                    o.println();
                }
            }
        } else {
            BinaryOut bo = new BinaryOut(path);
            for (Integer id : cachesST.keys()) {
                Cache aux = cachesST.get(id);
                if(aux.items.size() == 0){
                    bo.write(
                            aux.getCache_id() + ";" +
                                    aux.getType() + ";" +
                                    aux.get_Point().getX() + ";" +
                                    aux.get_Point().getY() + ";" +
                                    aux.getZone() + ";" +
                                    aux.items.size() + "\n");
                } else {
                    bo.write(
                            aux.getCache_id() + ";" +
                                    aux.getType() + ";" +
                                    aux.get_Point().getX() + ";" +
                                    aux.get_Point().getY() + ";" +
                                    aux.getZone() + ";" +
                                    aux.items.size() + ";");
                    for (int i = 0; i < aux.items.size(); i++) {
                        bo.write(aux.items.get(i).getName() +";");
                    }
                    bo.write("\n");
                }
            }
        }
        if(type != 0){
            System.out.println("Caches saved .bin");
        } else{
            System.out.println("Caches saved .txt");
        }
    }


    /**
     * Save Users to file
     * @param path path to file
     * @param type 0(.TXT) or 1(.BIN)
     */
    public static void saveUsersToFile(SeparateChainingHashST<Integer, User> usersST, String path, int type) {
        if(type == 0 ) {
            Out o = new Out(path);

            o.println("id_num;Name; Type");
            for (Integer id : usersST.keys()) {
                User aux = usersST.get(id);
                o.println(
                        aux.getId_number() + ";" +
                                aux.getName() + ";" +
                                aux.getType());
            }
        } else {
            BinaryOut bo = new BinaryOut(path);
            for (Integer id : usersST.keys()) {
                User aux = usersST.get(id);
                bo.write(
                        aux.getId_number() + ";" +
                                aux.getName() + ";" +
                                aux.getType() + "\n");
            }
        }
        if(type != 0){
            System.out.println("users saved .bin");
        } else{
            System.out.println("users saved .txt");
        }
    }

    /**
     * Save Travelbugs to file
     * @param path path to file
     * @param type 0(.TXT) or 1(.BIN)
     */
    public static void saveTravelbugsToFile(SeparateChainingHashST<String, TravelBug> travelbugsST, String path, int type) {
        if(type == 0 ) {
            Out o = new Out(path);

            o.println("travelBug_id;user;cache1;cache2");
            for (String id : travelbugsST.keys()) {
                TravelBug aux = travelbugsST.get(id);
                o.println(
                        aux.getTravelbug_id() + ";" +
                                aux.getUser().getId_number() + ";" +
                                aux.getCache1().getCache_id() + ";" +
                                aux.getCache2().getCache_id());
            }
        } else {
            BinaryOut bo = new BinaryOut(path);
            for (String id : travelbugsST.keys()) {
                TravelBug aux = travelbugsST.get(id);
                bo.write(
                        aux.getTravelbug_id() + ";" +
                                aux.getUser().getId_number() + ";" +
                                aux.getCache1().getCache_id() + ";" +
                                aux.getCache2().getCache_id() + "\n");
            }
        }
        if(type != 0){
            System.out.println("Travelbug saved .bin");
        } else{
            System.out.println("Travelbug saved .txt");
        }
    }


    /**
     * Prints all the users existing in the SeparateChaining
     *
     * @param usersST the SeparateChaining of users
     */
    private static void printAllUsers(SeparateChainingHashST<Integer, User> usersST) {
        System.out.println("All Users:");
        for (Integer id : usersST.keys()) {
            System.out.println(usersST.get(id));
        }
    }
    /**
     * Prints all the Caches existing in the SeparateChaining
     *
     * @param cachesST the SeparateChaining of caches
     */
    public static void printAllCaches(SeparateChainingHashST<Integer, Cache> cachesST) {
        System.out.println("All Caches:");
        for (Integer cacheId : cachesST.keys()){
            System.out.println(cachesST.get(cacheId));
        }
    }

    /**
     * Prints all the Travelbugs existing in the SeparateChaining
     *
     * @param travelbugsST the SeparateChaining of travelbugs
     */
    private static void printAllTravelbugs(SeparateChainingHashST<String, TravelBug> travelbugsST) {
        System.out.println("All Travelbugs:");
        for (String travelbugId : travelbugsST.keys()){
            System.out.println(travelbugsST.get(travelbugId));
        }
    }

    /**
     * prints caches visited by user
     * @param user user
     */
    private static void printAllCachesVisitedBy(User user) {
        System.out.println("caches visited by " + user.getName());
        user.printAllCachesVisited();
        System.out.println();

    }

    /**
     * remove a User from SeparateChainingST
     * @param u user to delete
     */
    public static void removeUser(SeparateChainingHashST<Integer, User> usersST, User u) {

        for (Integer id : usersST.keys()) {
            User uaux = usersST.get(id);
            if(uaux.getId_number() == u.getId_number())  {
                usersST.delete(id);
                return;
            }
        }
        System.out.println("user doesnt exist !");
    }

    /**
     * remove a Cache from SeparateChainingST
     * @param c cache to delete
     */
    public static void removeGeocache(SeparateChainingHashST<Integer, Cache> cachesST, Cache c) {

        for (Integer id : cachesST.keys()) {
            Cache caux = cachesST.get(id);
            if(caux.getCache_id().equals(c.getCache_id()))  {
                cachesST.delete(id);
                System.out.println("cache " +c.getCache_id() + " removida!");
                return;
            }
        }
        System.out.println("cache doesnt exist !");
    }


    /**
     * remove a Travelbug from SeparateChainingST
     * @param travelbugsST SeparateChainingST travelbugs
     * @param tb travelbug to remove
     */
    public static void removeTravelbug(SeparateChainingHashST<String, TravelBug> travelbugsST, TravelBug tb) {
        for (String id : travelbugsST.keys()) {
            TravelBug taux = travelbugsST.get(id);
            if(taux.getTravelbug_id().equals(tb.getTravelbug_id()))  {
                travelbugsST.delete(id);
                System.out.println("Travelbug " +tb.getTravelbug_id() + " removida!");
                return;
            }
        }
        System.out.println("Travelbug doesnt exist !");
    }

    /**
     * Add a Caches to SeparateChainingST
     * @param c cache to add
     */
    public static void addCache(SeparateChainingHashST<Integer, Cache> cachesST, Cache c) {
        for (Integer id : cachesST.keys()) {
            Cache caux = cachesST.get(id);
            if(caux.getCache_id().equals(c.getCache_id()))  {
                System.out.println("Cache already exists!");
                return;
            }
        }
        cachesST.put(c.getCache_id_int(), c);
    }

    /**
     * Add a User to SeparateChainingST
     * @param u user to add
     */
    public static void addUser(SeparateChainingHashST<Integer, User> usersST, User u) {
        for (Integer id : usersST.keys()) {
            User uaux =  usersST.get(id);
            if(uaux.getId_number() == u.getId_number())  {
                System.out.println("User already exists!");
                return;
            }
        }
        usersST.put(u.getId_number(), u);
    }


    /**
     * Add a Travelbug to SeparateChainingST
     * @param travelbugsST ST to add the travelbug
     * @param tb travelbug to add
     */
    public static void addTravelbug(SeparateChainingHashST<String, TravelBug> travelbugsST, TravelBug tb ) {
        for (String id : travelbugsST.keys()) {
            TravelBug taux = travelbugsST.get(id);
            if(taux.getTravelbug_id().equals(tb.getTravelbug_id()))  {
                System.out.println("Travelbug already exists!");
                return;
            }
        }
        travelbugsST.put(tb.getTravelbug_id(), tb);
    }


    /**
     * Edits info about cache by id
     *
     * @param cachesST SeparateChainingST
     * @param cache_id cache key to be edited
     */

    public static void editCache(SeparateChainingHashST<Integer, Cache> cachesST, int cache_id) {
        if (cachesST.contains(cache_id)) {
            System.out.println("1 - type");
            System.out.println("2 - Zone");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {
                case "1":
                    String new_type = scanIn.nextLine();
                    cachesST.get(cache_id).setType(Cache.Type.valueOf(new_type));
                    break;
                case "2":
                    String new_zone = scanIn.nextLine();
                    cachesST.get(cache_id).setZone(new_zone);
                    break;

            }
            System.out.println("\nCache edited!\n");
        } else {
            System.out.println("\nThis cache doesn't exist" + "\n");
        }
    }

    /**
     * Edits info about User by id
     *
     * @param usersST SeparateChainingST
     * @param user_id user key to be edited
     */

    public static void editUser(SeparateChainingHashST<Integer, User> usersST, int user_id) {
        if (usersST.contains(user_id)) {
            System.out.println("1 - Edit Name");
            System.out.println("2 - Edit Type");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {
                case "1":
                    String new_name = scanIn.nextLine();
                    usersST.get(user_id).setName(new_name);
                    break;
                case "2":
                    String new_type = scanIn.nextLine();
                    usersST.get(user_id).setType(new_type);
                    break;

            }
            System.out.println("\nUser edited!\n");
        } else {
            System.out.println("\nThis User doesn't exist" + "\n");
        }
    }

    /**
     * Edits info about Trabelbug by id
     *
     * @param travelbugsST SeparateChainingST
     * @param travelbug_id travelbug key to be edited
     */

    public static void editTravelbug(SeparateChainingHashST<String, TravelBug> travelbugsST, String travelbug_id) {
        if (travelbugsST.contains(travelbug_id)) {
            System.out.println("1 - edit start cache");
            System.out.println("2 - edit finish cache");

            Scanner scanIn = new Scanner(System.in);
            String choice = scanIn.nextLine();

            switch (choice) {
                case "1":
                    String new_start_cache = scanIn.nextLine();
                    travelbugsST.get(travelbug_id).setCache1(cachesST.get(Integer.parseInt(new_start_cache)));
                    break;
                case "2":
                    String new_finish_cache = scanIn.nextLine();
                    travelbugsST.get(travelbug_id).setCache2(cachesST.get(Integer.parseInt(new_finish_cache)));
                    break;
            }

            System.out.println("\nTravelbug edited!\n");
        } else {
            System.out.println("\nThis Travelbug doesn't exist" + "\n");
        }
    }


    /**
     * Test info about ALL the SeparateChainingSTs
     */
    private static void client1() {

        ////////////////////////////////////////// Caches //////////////////////////////////////////////

        Cache c1 = new Cache(19, Cache.Type.valueOf("premium"), new Point(38.9369329,-9.3290594), "sul");
        c1.addItem(new Item("pedra"));

        //addCache(cachesST, c1);
        //removeGeocache(cachesST, c1);
        //editCache(cachesST, 19);

        //printAllCaches(cachesST);

        //saveCachesToFile(cachesST, ".//data//caches.txt", 0);
        //saveCachesToFile(cachesST, ".//data//binary//caches.bin", 1);

        ///////////////////////////////////// Users /////////////////////////////////////////////////

        User u1 = new User(7, "Eduardo", "admin");

        //addUser(usersST, u1);
        //removeUser(usersST, u1);
        //editUser(usersST, 7);

        //printAllUsers(usersST);

        //saveUsersToFile(usersST, ".//data//users.txt", 0);
        //saveUsersToFile(usersST, ".//data//binary//users.bin", 1);


        ////////////////////////////////////// TravelBugs //////////////////////////////////////////////////

        TravelBug tb1 = new TravelBug("Travelbug", "travelbug4", usersST.get(6), cachesST.get(15), cachesST.get(16));

        //addTravelbug(travelbugsST, tb1);
        //removeTravelbug(travelbugsST, tb1);
        //editTravelbug(travelbugsST, "travelbug4");

        //printAllTravelbugs(travelbugsST);

        //saveTravelbugsToFile(travelbugsST, ".//data//travelbugs.txt", 0);
        //saveTravelbugsToFile(travelbugsST, ".//data//binary//travelbugs.bin", 1);


    }


    /**
     * Interaçao de um User visitar uma cache
     * @param user user
     * @param cache cache
     * @param message message
     * @param inseridos item inserido
     * @param retirados item removido
     * @param date data do log
     */
    public static void interacao(User user, Cache cache, String message, ArrayList<Item> inseridos, ArrayList<Item> retirados, Date date){

        user.visitCache(cache, inseridos, retirados, date, user, message);

        if(inseridos != null) {
            for (Item i : inseridos) {
                if (i instanceof TravelBug) {
                    ((TravelBug) i).addLog(cache, user, "inserido", date);
                    //((TravelBug) i).printLogs();
                }
            }
        }
        if(retirados != null) {
            for (Item ir : retirados) {
                if (ir instanceof TravelBug) {
                    ((TravelBug) ir).addLog(cache, user, "retirado", date);
                    //((TravelBug) ir).printLogs();
                }
            }
        }
    }


    private static void client2(SeparateChainingHashST<Integer, Cache> cachesST, SeparateChainingHashST<Integer, User> usersST, SeparateChainingHashST<String, TravelBug> travelbugsST) throws InterruptedException {
        System.out.println("Client2 Test:");

        User u1 = usersST.get(1);   //Manuel
        User u2 = usersST.get(2);   //pedro
        User u3 = usersST.get(3);   //fernando
        User u4 = usersST.get(4);   //joana
        User u5 = usersST.get(5);   //maria
        User u6 = usersST.get(6);   //filomena

        //cachesST.get(2).addItem(travelbugsST.get("travelbug2"));
        u1.addItem(travelbugsST.get("travelbug2"));
        u3.addItem(travelbugsST.get("travelbug1"));
        u5.addItem(travelbugsST.get("travelbug3"));

        ArrayList<Item> retiradoss = new ArrayList<>();
        retiradoss.add(cachesST.get(8).getThisItems().get(1));

        //- o Manuel visita as geocaches 1, 2, 6, 8, 13, 16, 17 ao passar na geocache2 tirou o travelbug2. Ao passar na geocache 17 deixou o travelbug2.
        interacao(u1, cachesST.get(1), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(2), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(6), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(8), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(13), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(16), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u1, cachesST.get(17), "Really fun to find this cache!", u1.getItemsUser(), null, new Date());

       // - o Pedro visita as geocaches 18, 13, 8 ao passar na geocache 8 tirou a capa
        interacao(u2, cachesST.get(18), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u2, cachesST.get(13), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u2, cachesST.get(8), "Really fun to find this cache!", null, retiradoss, new Date());

        //- o Fernando visita as geocaches 12, 11, 10, 8, 9, 5, 6, 4, 3, 2, 1, 7, 15, 17, 18, 13. Ao passar na geocache1 tirou o travelbug1. Ao passar na geocache 15 deixou ficar o travelbug1.
        interacao(u3, cachesST.get(12), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(11), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(10), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(8), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(9), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(5), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(6), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(4), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(3), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(2), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(1), "hello world", null, null, new Date(2,4,2020));
        Thread.sleep(100);
        interacao(u3, cachesST.get(7), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(15), "Really fun to find this cache!", u3.getItemsUser(), null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(17), "Really fun to find this cache!", null, null, new Date(9,5,2020));
        Thread.sleep(100);
        interacao(u3, cachesST.get(18), "hey !", null, null, new Date());
        Thread.sleep(100);
        interacao(u3, cachesST.get(13), "Really fun to find this cache!", null, null, new Date());

       //- a Joana visita as geocaches 14, 15, 18, 17, 13
        interacao(u4, cachesST.get(14), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u4, cachesST.get(15), "im happy!", null, null, new Date());
        Thread.sleep(100);
        interacao(u4, cachesST.get(18), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u4, cachesST.get(17), "Hard to find but worth it!", null, null, new Date());
        Thread.sleep(100);
        interacao(u4, cachesST.get(13), "Really fun to find this cache!", null, null, new Date());

        // - a Maria visita as geocaches 3, 8, 9, 10, 16, 11, 12. Ao passar na geocache 3 tirou o travelbug3. Ao passar na geocache12 deixou ficar o travelbug3.
        interacao(u5, cachesST.get(3), "Eduardo Ribeiro", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(8), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(9), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(10), "hello!", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(16), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(11), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u5, cachesST.get(12), "Really fun to find this cache!", u5.getItemsUser(), null, new Date());

        //- a Filomena visita as geocaches 5, 6, 7, 3, 2, 1, 8, 13
        interacao(u6, cachesST.get(5), " fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(6), "Fun to find!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(7), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(3), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(2), "Really fun to find this cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(1), "Nice Cache!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(8), "nicee!", null, null, new Date());
        Thread.sleep(100);
        interacao(u6, cachesST.get(13), "Really fun to find fewwef cache!", null, null, new Date());

        System.out.println("\n");

        //printAllCaches(cachesST);
        //printAllUsers(usersST);

        //cachesST.get(2).printLogs();

        //u1.getUsersWithMostVisits(usersST, new Date(31,1,2020), new Date(5,1,2021));
        printAllCachesVisitedBy(u1);
//        printAllCachesVisitedBy(u2);
        //printAllCachesVisitedBy(u3);
//        printAllCachesVisitedBy(u4);
//        printAllCachesVisitedBy(u5);
//        printAllCachesVisitedBy(u6);
        //System.out.println(getVisitedCachesByUser(u1.getId_number()));


        //print Caches logs
       // cachesST.get(1).printLogs();
      //  cachesST.get(2).printLogs();
       // cachesST.get(17).printLogs();


    }

    public static ArrayList<Cache> getVisitedCachesByUser(int id) {
        return usersST.get(id).getAllCachesVisited();
    }

}




/** TESTE1
 *
 ///n.loadInput(".//data//input.txt", 0);
 n.loadUsers(".//data//users.txt");
 n.loadCaches(".//data//caches.txt", 0);
 //n.loadCaches(".//data//binary//caches.bin", 1); //not working

 n.printUsers();
 //n.printCaches();
 Item i1 = new Item(0, "pulseira", "normal");

 Date d1 = new Date(29, 4, 2021);
 Point p1 = new Point(10, 20);
 User u2 = new User(11111111, "Fernando Pessoa", 1.0, 4.0);
 Cache c1 = new Cache(5, "Cais de Gaia (Sandeman)", p1, 3, u2);

 Log l1 = new Log(d1, u2, "It was difficult to find the cache, but Worthy!");

 ///preciso de tornar estatico? como User user_aux = new User(id, name, user_type);
 this.usersST.put(id, user_aux);usar funçoes em Cache
 //Cache.addItem(i1);

 //TODO Fazer esta funçao com o visitar que atualiza a cache com o log e/ou o item, Tambem fazer a enumeraçao de USERS
 //c1.visitCache(l1, u3);
 //c1.logsST().put(d1, l1);

 c1.addLog(l1);
 n.addCache(c1);

 //c1.printLogs();
 ///n.printCaches();
 //n.saveCaches(".//data//caches.txt", 0);
 //n.saveCaches(".//data//binary//caches.bin", 1);

 //n.loadCaches(".//data//caches.txt", 0);

 **/


/**Como meter as caches na CachesST
//n.printCaches();
//c1.addItem(i1);


Como saber se já visitei a cache??
como usar o travelbug
**/


        /*
        System.out.println("Client Test:");

        Cache c1 = new Cache(1, Cache.Type.premium, new Point(1.0, 2.0), "Norte");
        Cache c2 = new Cache(2, Cache.Type.basic, new Point(1.0, 2.0), "Norte");
        //Cache c1 = cachesST.get(1);
       // Cache c2 = cachesST.get(2);
//        Cache c3 = cachesST.get(3);
//        Cache c4 = cachesST.get(4);

        User u1 = usersST.get(1);
        User u2 = usersST.get(3);
        User u5 = usersST.get(5);

//        u1.insertCacheVisitHistory(c1, new Date(5, 5, 2021, 10,30, 30, 2));
//        u1.insertCacheVisitHistory(c2, new Date(5, 5, 2021, 16,30, 3, 4));

/*
        u1.visitCache(cachesST.get(1));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(2));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(6));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(8));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(13));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(16));
        Thread.sleep(1000);
        u1.visitCache(cachesST.get(17));

        System.out.println("caches visited by " + u1.getName());
        u1.printAllCachesVisited();
        System.out.println();




        u1.addItem(new Item("bola"));
        u1.addItem(new Item("caneta"));
        u1.addItem(travelbugsST.get("travelbug1"));

        //printAllUsers(usersST);
        //printAllCaches(cachesST);


        //interacao(u1, cachesST.get(4), "Really fun to find this cache!", u1.getItemsUser(), cachesST.get(4).getItems(), new Date());
        interacao(u1, cachesST.get(4), "Really fun to find this cache!", u1.getItemsUser(), null, new Date());
        interacao(u1, cachesST.get(4), "Really fun to find this cache!", null, cachesST.get(4).getThisItems(), new Date());

        System.out.println("caches visited by " + u1.getName());
        u1.printAllCachesVisited();


         */
