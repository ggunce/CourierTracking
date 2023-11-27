package com.migros.couriertrackingservice.util;

public class GeolocationUtil {
    private static GeolocationUtil instance;
    private final static Integer EARTH_RADIUS = 6371; // Radius of the earth in km
    private GeolocationUtil() {
    }
    public static synchronized GeolocationUtil getInstance() {
        if (instance == null) {
            instance = new GeolocationUtil();
        }
        return instance;
    }

    public double calculateDistance(double lat1,double lon1,double lat2,double lon2) {
        var dLat = deg2rad(lat2 - lat1);  // deg2rad below
        var dLon = deg2rad(lon2 - lon1);
        var a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c * 1000;
    }

    private static double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }
}
