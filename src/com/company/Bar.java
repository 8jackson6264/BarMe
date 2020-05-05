package com.company;

public class Bar {

    private String name;
    private double latitude;
    private double  longitude;
    private float openingTime;
    private float closingTime;

    public Bar(String name, double latitude, double longitude, float openingTime, float closingTime) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getName() {
        return name;
    }

    public float getOpeningTime() {
        return openingTime;
    }

    public float getClosingTime() {
        return closingTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public double distanceInMetersToBar(double lat2,double lon2) {
        double earthRadiusKm = 6371;

        double dLat = degreesToRadians(lat2-this.latitude);
        double dLon = degreesToRadians(lon2-this.longitude);

        double latInRadians = degreesToRadians(latitude);

        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(latInRadians) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return Math.round(earthRadiusKm * c*1000);
    }

    public String getFormattedWorkingTime(){
        int openingHours = (int) Math.floor(this.openingTime);
        int openingMinutes = (int) ((this.openingTime%1)*6);

        int closingHours = (int) Math.floor(this.closingTime);
        int closingMinutes = (int) ((this.closingTime%1)*6);


        return openingHours + ":" + openingMinutes + "0-" + closingHours + ":" + closingMinutes + "0";
    }
}
