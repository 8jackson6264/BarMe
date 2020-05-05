package com.company;

import java.util.Calendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int option;
        double lat;
        double lon;

        while (true){
            BarList barList = new BarList();

            System.out.println("Въведете вашата локация както следва: \"xx.xxxx, yy.yyyy\" - пример: 43.205847 23.553495");

            Scanner input = new Scanner(System.in);
            lat = input.nextDouble();
            lon = input.nextDouble();

            printOptions();

            option = input.nextInt();

            switch (option){
                case 1:
                    barList.printClosestBars(lat, lon);
                    break;
                case 2:
                    barList.printOpenBars(returnTime(), lat, lon);
                    break;
                case 3:
                    barList.printMap(lat, lon);
                    break;
                case 0:
                    System.exit(0);
            }

            System.out.println();
            System.out.println();

        }

    }

    public static double returnTime(){
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.HOUR_OF_DAY) + ((double)calendar.get(Calendar.MINUTE))/60;
    }

    public static void printOptions(){
        System.out.println("Изберете опция:\n" +
                "1. Най-близки барове.\n" +
                "2. Отворени барове\n" +
                "3. Карта на баровете\n" +
                "0 - Изход...");
    }

}
