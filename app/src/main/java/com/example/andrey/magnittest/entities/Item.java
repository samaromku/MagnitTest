package com.example.andrey.magnittest.entities;

public class Item {
    private int number;
    private double rate;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Item(int number, double rate) {
        this.number = number;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "number=" + number +
                ", rate=" + rate +
                '}';
    }
}
