package com.company;

public class Ship {
    private int cargo;
    private String name;

    public Ship(String name, int cargo) {
        this.name = name;
        this.cargo = cargo;
    }

    public int uploadCargo(int count) {
        cargo -= count;
        if(cargo < 0) {
            count = 0;
            return count + cargo;
        }
        return count;
    }

    public int getCargoCount() {
        return cargo;
    }

    public String getName() {
        return name;
    }

}
