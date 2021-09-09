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

        Dock dock = new Dock(ships);

        Thread upload1 = new Thread(() -> dock.uploadShip(ships.get(0)));
        Thread upload2 = new Thread(() -> dock.uploadShip(ships.get(1)));
        Thread upload3 = new Thread(() -> dock.uploadShip(ships.get(2)));

        upload1.start();
        upload2.start();
        upload3.start();
    }
}
