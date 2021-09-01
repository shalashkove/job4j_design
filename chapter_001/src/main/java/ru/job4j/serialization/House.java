package ru.job4j.serialization;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class House {
    private boolean isFree;
    private double price;
    private String owner;
    private Contact contact;
    private String[] ownersList;

    public House(boolean isFree, double price, String owner, Contact contact, String[] ownersList) {
        this.isFree = isFree;
        this.price = price;
        this.owner = owner;
        this.contact = contact;
        this.ownersList = ownersList;
    }

    @Override
    public String toString() {
        return "House{"
                + "isFree=" + isFree
                + ", price=" + price
                + ", owner='" + owner + '\''
                + ", contact=" + contact
                + ", ownersList=" + Arrays.toString(ownersList)
                + '}';
    }

    /*    вывод объекта в формате JSON:
     *    {
     *        "isFree": boolean,
     *        "price": double,
     *        "owner": String,
     *        "contact": объект класса Contact,
     *        "ownersList": массив String
     *    }
     */
    public static void main(String[] args) {
        House house = new House(true, 200.00, "Jack",
                new Contact(123456, "111-11-11"), new String[] {"Olli", "Alisa", "Mike"});
        System.out.println(house);

        final Gson gson = new GsonBuilder().create();
        String gsonString = gson.toJson(house);
        System.out.println(gsonString);

        final House houseReturn = gson.fromJson(gsonString, House.class);
        System.out.println(houseReturn);
    }
}
