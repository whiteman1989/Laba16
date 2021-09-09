package com.company;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    List<Ship> ships;
    int cargo = 0;
    private Lock lock = new ReentrantLock();

    public Dock(List<Ship> ships) {
        this.ships = ships;
    }

    public void uploadCargo() {
        while (!ships.isEmpty()) {
            lock.lock();
            System.out.println("Dock locked");
            try {
                var ship = ships.get(0);
                while (ship.getCargoCount() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cargo += ship.uploadCargo(1);
                    System.out.println("-> uploaded: " + cargo + " units of cargo from "  + ship.getName()+" ship ");
                }
                ships.remove(ship);
                System.out.println("--> Ship " + ship.getName() + "nul" + "uploaded");
            }
            finally {
                lock.unlock();
                System.out.println("Dock unlocked");
            }
        }
    }

    public void uploadShip(Ship ship) {
        lock.lock();
        System.out.println("Dock locked");
        try {
            while (ship.getCargoCount() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cargo += ship.uploadCargo(1);
                System.out.println("-> uploaded: " + cargo + " units of cargo from "  + ship.getName()+" ship ");
            }
            System.out.println("--> Ship " + ship.getName() + "nul" + "uploaded");
        }
        finally {
            lock.unlock();
            System.out.println("Dock unlocked");
        }
    }

}
