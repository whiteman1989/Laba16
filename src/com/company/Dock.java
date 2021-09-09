package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    private List<Ship> ships;
    private int cargo = 0;
    private String name;
    private ReentrantLock lock = new ReentrantLock();
    private boolean isLock =false;

    public Dock(List<Ship> ships, String name) {
        this.name = name;
        this.ships = ships;
    }

    public Dock(String name) {
        this.ships = new ArrayList<>();
        this.name = name;
    }

    public void uploadShip(Ship ship) {
        isLock = true;
        lock.lock();
        System.out.println("Dock \"" +name+"\" locked");
        try {
            while (ship.getCargoCount() > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cargo += ship.uploadCargo(1);
                System.out.println("-> uploaded: " + cargo + " units of cargo from "  + ship.getName()+" ship in dock "+name);
            }
            System.out.println("--> Ship " + ship.getName() + " uploaded in dock "+name);
        }
        finally {
            lock.unlock();
            System.out.println("Dock \"" +name+"\" unlocked");
            isLock = false;
        }
    }

    public boolean isLocked() {
        return isLock;
    }

    public String getName() {
        return name;
    }

}
