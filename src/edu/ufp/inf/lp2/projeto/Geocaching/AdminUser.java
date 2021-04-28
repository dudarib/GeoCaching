package edu.ufp.inf.lp2.projeto.Geocaching;

import java.util.List;

public class AdminUser extends premiumUser {
    public AdminUser(int id_number, String name, Point point, List<Cache> caches) {
        super(id_number, name, point, caches);
    }
}