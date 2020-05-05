package com.company;

import java.util.ArrayList;
import java.util.List;

public class Map {

    public void printBarsOnMap(double userLat, double userLon, Bar[] allBars) {

        String[][] map = new String[12][22];
        Bar[] barsOnMap = filterBarsOutsideMap(allBars, map, userLat, userLon);

        map = fillMapWithInitialValues(map);
        map = addBarsOnMap(userLat, userLon, map, barsOnMap);
        printMap(map);
        System.out.print(createMapLegend(userLat, userLon, barsOnMap));
    }

    private Bar[] filterBarsOutsideMap(Bar[] allBars, String[][] map, double userLat, double userLon) {
        List<Bar> nearbyBars = new ArrayList<>();

        for (Bar bar : allBars) {
            double distanceOnLatitude = bar.distanceInMetersToBar(userLat, bar.getLongitude());
            double distanceOnLongitude = bar.distanceInMetersToBar(bar.getLatitude(), userLon);
            if (distanceOnLatitude > (map[0].length - 2) * 25 || distanceOnLongitude > (map.length - 2) * 25) {
                continue;
            } else {
                nearbyBars.add(bar);
            }
        }
        return (Bar[]) nearbyBars.toArray();
    }

    private String[][] fillMapWithInitialValues(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            map[i][0] = "|";
            map[i][map[i].length - 1] = "|";
        }
        for (int i = 0; i < map[1].length; i++) {
            map[0][i] = "----";
            map[map.length - 1][i] = "----";
        }
        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                map[i][j] = "    ";
            }
        }
        map[5][10] = " X  ";
        return map;
    }

    private void printMap(String[][] map) {
        System.out.println("             1000m");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
                if (i == 5 && j == 21) System.out.print("  500m");
            }
            System.out.println();
        }
    }

    private String[][] addBarsOnMap(double lat, double lon, String[][] map, Bar[] bars){

        int count = 1;

        for (Bar bar : bars) {
            int indexLat;
            int indexLon;
            double distanceOnLatitude = bar.distanceInMetersToBar(lat, bar.getLongitude());
            double distanceOnLongitude = bar.distanceInMetersToBar(bar.getLatitude(), lon);

            if (bar.getLatitude() > lat && bar.getLongitude() > lon) {
                indexLat = map[0].length / 2 - 1 + (int) distanceOnLatitude / 50;
                indexLon = map.length / 2 - 1 - (int) distanceOnLongitude / 50;
                map[indexLon][indexLat] = " " + count + "  ";
                count++;
            } else if (bar.getLatitude() < lat && bar.getLongitude() > lon) {
                indexLat = map[0].length / 2 - 1 - (int) distanceOnLatitude / 50;
                indexLon = map.length / 2 - 1 - (int) distanceOnLongitude / 50;
                map[indexLon][indexLat] = " " + count + "  ";
                count++;
            } else if (bar.getLatitude() > lat && bar.getLongitude() < lon) {
                indexLat = map[0].length / 2 - 1 + (int) distanceOnLatitude / 50;
                indexLon = map.length / 2 - 1 + (int) distanceOnLongitude / 50;
                map[indexLon][indexLat] = " " + count + "  ";
                count++;
            } else if (bar.getLatitude() < lat && bar.getLongitude() < lon) {
                indexLat = map[0].length / 2 - 1 - (int) distanceOnLatitude / 50;
                indexLon = map.length / 2 - 1 + (int) distanceOnLongitude / 50;
                map[indexLon][indexLat] = " " + count + "  ";
                count++;
            }
        }

        return map;
    }

    private String createMapLegend(double lat, double lon, Bar[] bars){

        StringBuilder sbMapLegend = new StringBuilder();
        int count = 1;

        for (Bar bar : bars) {
            if (bar.getLatitude() > lat && bar.getLongitude() > lon) {
                sbMapLegend.append(count).append(". ").append(bar.getName()).append(" - ").append(bar.distanceInMetersToBar(lat, lon)).append(" \n");
                count++;
            } else if (bar.getLatitude() < lat && bar.getLongitude() > lon) {
                sbMapLegend.append(count).append(". ").append(bar.getName()).append(" - ").append(bar.distanceInMetersToBar(lat, lon)).append(" \n");
                count++;
            } else if (bar.getLatitude() > lat && bar.getLongitude() < lon) {
                sbMapLegend.append(count).append(". ").append(bar.getName()).append(" - ").append(bar.distanceInMetersToBar(lat, lon)).append(" \n");
                count++;
            } else if (bar.getLatitude() < lat && bar.getLongitude() < lon) {
                sbMapLegend.append(count).append(". ").append(bar.getName()).append(" - ").append(bar.distanceInMetersToBar(lat, lon)).append(" \n");
                count++;
            }
        }

        return sbMapLegend.toString();
    }

}
