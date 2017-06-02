package com.example.andrey.magnittest.managers;

import android.content.Context;
import android.database.Cursor;

import com.example.andrey.magnittest.entities.Item;
import com.example.andrey.magnittest.storage.DataBaseHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private List<Item> allItems;
    private List<Item> newItems;
    private Context context;
    private DataBaseHelper db;

    public List<Item> getAllItems() {
        return allItems;
    }

    public List<Item> getNewItems() {
        return newItems;
    }

    public ItemManager(Context context){
        this.context = context;
        db = new DataBaseHelper(context);
        allItems = new ArrayList<>();
        newItems = new ArrayList<>();
        if(allItems.size()==0){
            getFromDB();
        }
    }

    public void updateItems(){
        allItems.clear();
        getFromDB();
    }

    private double formatDouble(double d){
        System.out.println(d);
        String format = new DecimalFormat("#.#").format(d).replace(",", ".");
        System.out.println(format);
        return Double.valueOf(format);
    }

    private void addFakeItems() {
        for (int i = 0; i < 100; i++) {
            Item item = new Item(i, formatDouble(Math.random() * 1));
            allItems.add(item);
            addTodb(item);
        }
    }

    private void updateItem(Item item){
        for (Item i : allItems) {
            if (i.getNumber()==(item.getNumber())) {
                i.setRate(item.getRate());
                db.updateRow(i);
            }
        }
    }

    private void addItem(Item item){
        addTodb(item);
        allItems.add(item);
    }

    public void addOrUpdate(Item item){
        boolean added = false;
        for (Item i:allItems) {
            if(i.getNumber()==item.getNumber()){
                added = true;
            }
        }
        if(added){
            updateItem(item);
        }else{
            addItem(item);
        }
    }


    private void updateNewItem(Item item){
        for (Item i : newItems) {
            if (i.getNumber()==(item.getNumber())) {
                i.setRate(item.getRate());
            }
        }
    }

    private void addNewItem(Item item){
        newItems.add(item);
    }

    public void addOrUpdateNewItem(Item item){
        boolean added = false;
        for (Item i:newItems) {
            if(i.getNumber()==item.getNumber()){
                added = true;
            }
        }
        if(added){
            updateNewItem(item);
        }else{
            addNewItem(item);
        }
    }

    public void removeNew(){
        newItems.clear();
    }


    private void addTodb(Item item) {
        db.insertRow(item);
    }

    private void getFromDB(){
        Cursor res = db.getAllItems();
        if(res.getCount()==0){
            return;
        }
        while(res.moveToNext()){
            Item item = new Item(Integer.parseInt(res.getString(0)), Double.parseDouble(res.getString(1)));
            allItems.add(item);
        }
    if(allItems.size()==0){
        addFakeItems();
    }
    }
}

