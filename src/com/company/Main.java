package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        List<Ship> ships = new ArrayList<>();
        ships.add(new Ship("first", 10));
        ships.add(new Ship("second", 10));
        ships.add(new Ship("third", 10));
        ships.add(new Ship("first23", 10));
        ships.add(new Ship("second24", 10));
        ships.add(new Ship("third34", 10));

        Dock dock1 = new Dock("A");
        Dock dock2 = new Dock("B");

        List<Dock> docks = new ArrayList<>();
        docks.add(dock1);
        docks.add(dock2);

        while (!ships.isEmpty()) {
            var ship = ships.get(0);
            for (Dock d: docks ) {
                if(!d.isLocked()) {
                    Thread thr =  new Thread(()->d.uploadShip(ship));
                    thr.start();
                    ships.remove(ship);
                    break;
                }
            }
        }

    }
}
