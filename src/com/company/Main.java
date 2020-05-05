package com.company;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        double lat = 43.205868;
        double lon = 23.551992;

        BarList barList = new BarList();

        barList.printClosestBars(lat, lon);

        barList.printMap(lat, lon);

        System.out.println(returnTime());

        System.out.println(barList.bars[2].getFormattedWorkingTime());
    }

    public static double returnTime(){
        Calendar calendar = Calendar.getInstance();
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);

        return calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE))/60;
    }
}
