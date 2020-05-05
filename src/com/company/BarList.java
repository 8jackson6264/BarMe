package com.company;

public class BarList {

    Bar[] bars = new Bar[10];

    public BarList() {
        this.bars[0] = new Bar("RockClub", 43.207145, 23.553612, 9, 4);
        this.bars[1] = new Bar("Bijou", 43.205362, 23.555616, 18, 2);
        this.bars[2] = new Bar("Selfie", 43.205166, 23.556046, 7, 23);
        this.bars[3] = new Bar("The white Bar", 43.201007, 23.549560, 9, 17);
        this.bars[4] = new Bar("VIP", 43.204608, 23.548487, (float) 7.5, 4);
        this.bars[5] = new Bar("Flamingo", 43.202841, 23.550862, (float) 7.5, (float) 22.5);
        this.bars[6] = new Bar("Valmon", 43.200473, 23.559481, 9, 7);
        this.bars[7] = new Bar("Kadife", 43.202870, 23.549136, 7, 1);
        this.bars[8] = new Bar("The messenger", 43.197830, 23.551997, 6, 23);
        this.bars[9] = new Bar("Trakiiska princesa", 43.204775, 23.552568, 7, 0);
    }

    public void printClosestBars(double lat, double lon) {
        Bar temp;

        for (int i = 0; i < this.bars.length - 1; i++) {
            for (int j = 0; j < this.bars.length - i - 1; j++) {
                double firstDistance = (float) this.bars[j].distanceInMetersToBar(lat, lon);
                double secondDistance = (float) this.bars[j + 1].distanceInMetersToBar(lat, lon);
                if (firstDistance > secondDistance) {
                    temp = this.bars[j];
                    this.bars[j] = this.bars[j + 1];
                    this.bars[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < this.bars.length; i++) {
            System.out.println((i + 1) + ". " + this.bars[i].getName() + " (" +
                    this.bars[i].getFormattedWorkingTime() + ")" + "-" +
                    (int) this.bars[i].distanceInMetersToBar(lat, lon) + "m.");
        }
    }

    public void printOpenBars(double time, double lat, double lon) {
        Bar temp;
        int count = 1;

        for (int i = 0; i < this.bars.length - 1; i++) {
            for (int j = 0; j < this.bars.length - i - 1; j++) {
                double newClosingTimeOne = this.bars[j].getClosingTime();
                double newClosingTimeTwo = this.bars[j + 1].getClosingTime();
                if (this.bars[j].getOpeningTime() > this.bars[j].getClosingTime())
                    newClosingTimeOne = this.bars[j].getClosingTime() + 24;
                if (this.bars[j + 1].getOpeningTime() > this.bars[j + 1].getClosingTime())
                    newClosingTimeTwo = this.bars[j + 1].getClosingTime() + 24;
                if (newClosingTimeOne > newClosingTimeTwo) {
                    temp = this.bars[j];
                    this.bars[j] = this.bars[j + 1];
                    this.bars[j + 1] = temp;
                }
            }
        }


        for (Bar bar : this.bars) {
            if (bar.getOpeningTime() > bar.getClosingTime()) {
                if (time > bar.getOpeningTime() || time < bar.getClosingTime()) {
                    System.out.println(count + ". " + bar.getName() + " (" +
                            bar.getFormattedWorkingTime() + ")" + "-" +
                            (int) bar.distanceInMetersToBar(lat, lon) + "m.");
                    count++;
                }
            }
        }

        System.out.println();


    }

    public void printMap(double lat, double lon){

        String[][] map = new String[12][22];
        map = fillMapFirst(map);

        StringBuilder stringBuilder = new StringBuilder();
        int count = 1;

//        for (int i = 0; i < this.bars.length; i++) {
//            double distanceOnLatitude = Math.floor(this.bars[i].distanceInMetersToBar(lat, this.bars[i].getLongitude())/50);
//            double distanceOnLongitude = Math.floor(this.bars[i].distanceInMetersToBar(this.bars[i].getLatitude(), lon)/50);
//            if (distanceOnLatitude > (map[0].length-2)*25 || distanceOnLongitude > (map.length-2)*25  ){
//                continue;
//            }
//
//            if (this.bars[i].getLatitude() > lat){
//                if (this.bars[i].getLongitude() > lon){
//                    map[(map.length-2) - (int) distanceOnLongitude/2][(map[0].length-2) + (int) distanceOnLongitude/2] = count +"   ";
//                    stringBuilder.append(count + ". " + this.bars[i].getName());
//                }
//            }
//            if (this.bars[i].getLatitude() < lat){
//                if (this.bars[i].getLongitude() > lon){
//                    map[(map.length-2) - (int) distanceOnLongitude/2][(map[0].length-2) - (int) distanceOnLongitude/2] = count +"   ";
//                    stringBuilder.append(count + ". " + this.bars[i].getName());
//                }
//            }
//            if (this.bars[i].getLatitude() < lat){
//                if (this.bars[i].getLongitude() < lon){
//                    map[(map.length-2) + (int) distanceOnLongitude/2][(map[0].length-2) - (int) distanceOnLongitude/2] = count +"   ";
//                    stringBuilder.append(count + ". " + this.bars[i].getName());
//                }
//            }
//            if (this.bars[i].getLatitude() > lat){
//                if (this.bars[i].getLongitude() < lon){
//                    map[(map.length-2) + (int) distanceOnLongitude/2][(map[0].length-2) + (int) distanceOnLongitude/2] = count +"   ";
//                    stringBuilder.append(count + ". " + this.bars[i].getName());
//                }
//            }
//
//        }

        System.out.println(stringBuilder.toString());

        printMap(map);



    }

    private String[][] fillMapFirst(String[][] map){
        for (int i = 0; i < map.length; i++) {
            map[i][0] = "|";
            map[i][map[i].length-1] = "|";
        }
        for (int i = 0; i <map[1].length ; i++) {
            map[0][i] = "----";
            map[map.length-1][i] = "----";
        }
        for (int i = 1; i <map.length-1 ; i++) {
            for (int j = 1; j < map[i].length-1; j++) {
                map[i][j] = "    ";
            }
        }
        map[5][10] = " X  ";
        return map;
    }

    private void printMap(String[][] map){
        System.out.println("             1000m");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
                if (i == 5 && j == 21) System.out.print("  500m");
            }
            System.out.println();
        }
    }

}

