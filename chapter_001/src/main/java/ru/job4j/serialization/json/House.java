package ru.job4j.serialization.json;

import org.json.JSONObject;
import ru.job4j.serialization.Contact;

import java.util.Arrays;

public class House {
    private boolean isFree;
    private double price;
    private String owner;
    private Contact contact;
    private String[] ownersList;

    public boolean isFree() {
        return isFree;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getOwnersList() {
        return ownersList;
    }

    public House(boolean isFree, double price, String owner, Contact contact, String[] ownersList) {
        this.isFree = isFree;
        this.price = price;
        this.owner = owner;
        this.contact = contact;
        this.ownersList = ownersList;
    }

    @Override
    public String toString() {
        return "House{" +
                "isFree=" + isFree +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                ", contact=" + contact +
                ", ownersList=" + Arrays.toString(ownersList) +
                '}';
    }

    public static void main(String[] args) {
        House house = new House(true, 200.00, "Jack",
                new Contact(123456, "111-11-11"), new String[] {"Olli", "Alisa", "Mike"});
        System.out.println(house);
        System.out.println(new JSONObject(house));
    }
}