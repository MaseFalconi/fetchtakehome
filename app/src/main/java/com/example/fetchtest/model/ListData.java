package com.example.fetchtest.model;

import java.util.Comparator;

/*
*   Data Model that will be created from the JSON objects from the test server
*
* */

public class ListData {

    private int id;
    private int listId;
    private String name;

    public ListData(){}

    public ListData(int jsonID, int jsonListID, String jsonName) {

        setId(jsonID);
        setListId(jsonListID);
        setName(jsonName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Comparator to group/sort the list id for the Data Items
    public static Comparator<ListData> listIDComparator = new Comparator<ListData>() {

        public int compare(ListData data1, ListData data2) {

            int originalID = data1.getListId();
            int comparingID = data2.getListId();

            //For ascending order
            return originalID-comparingID;
        }};

    //Comparator to sort the name for the Data Items in alphabetical/ascending order
    public static Comparator<ListData> dataNameComparator = new Comparator<ListData>() {

        public int compare(ListData data1, ListData data2) {
            String dataName1 = data1.getName();
            String dataName2 = data2.getName();

            //ascending order
            return pullNum(dataName1) - pullNum(dataName2);
        }

        /*If you do not convert the numerical part of the data name the list will not sort
        correctly, as this function would be comparing Strings*/
        int pullNum(String s) {
            String num = s.replaceAll("\\D", "");
            // return 0 if no digits found
            return num.isEmpty() ? 0 : Integer.parseInt(num);
        }
    };

    public String toString(){
        return "ID: " + getId() + " ListID: " + getListId() + " Name: " + getName();
    }
}
