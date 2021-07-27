package ru.job4j.serialization;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Car {
    private boolean isCargo;
    private double price;
    private String model;
    private Contact contact;
    private String[] ownersArr;
    static final String LS = System.lineSeparator();

    public boolean isCargo() {
        return isCargo;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getOwnersArr() {
        return ownersArr;
    }

    public Car(boolean isCargo, double price, String model, Contact contact, String[] ownersArr) {
        this.isCargo = isCargo;
        this.price = price;
        this.model = model;
        this.contact = contact;
        this.ownersArr = ownersArr;
    }

    private static class Contact {
        private String name;
        private String address;

        public Contact(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    /*    вывод объекта в формате XML:
     * 1-я строка необязательный заголовок с версией XML и указанием на используемую кодировку
     * <Car>        - начало объекта Car
     * <isCargo>false</isCargo>     - поле boolean isCargo
     * <price>10000.0</price>       - поле double price
     * <model>Ford 9L</model>       - поле String model
     * <contact>Contact{name='John K. Smith', address='400100, City, Town, Street, house, flat'}</contact>
     *                              - поле типа Contact в виде приведения к String
     * <ownersArrs>                 - начало массива ownersArr типа String
     * <ownersArr>Alice</ownersArr> - 1-е значение массива ownersArr
     * <ownersArr>Bill</ownersArr>  - 2-е значение массива ownersArr
     * </ownersArrs>                - конец массива ownersArr
     * </Car>                       - конец объекта Car
     */
    public static String toXMLview (Car car) {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>").append(LS);
        result.append("<Car>").append(LS);
        result.append("<isCargo>").append(car.isCargo()).append("</isCargo>").append(LS);
        result.append("<price>").append(car.getPrice()).append("</price>").append(LS);
        result.append("<model>").append(car.getModel()).append("</model>").append(LS);
        result.append("<contact>").append(car.getContact().toString()).append("</contact>").append(LS);
        result.append("<ownersArrs>").append(LS);
        for (String str : car.getOwnersArr()) {
            result.append("<ownersArr>").append(str).append("</ownersArr>").append(LS);
        }
        result.append("</ownersArrs>").append(LS);
        result.append("</Car>");
        return result.toString();
    }

    @Override
    public String toString() {
        return "Car{" +
                "isCargo=" + isCargo +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", contact=" + contact +
                ", ownersArr=" + Arrays.toString(ownersArr) +
                '}';
    }

    public static void main(String[] args) throws IOException {
        Car car = new Car(false, 10000.00, "Ford 9L",
                new Contact("John K. Smith", "400100, City, Town, Street, house, flat"),
                new String[] {"Alice", "Bill"});
        System.out.println(Car.toXMLview(car));
        try (PrintWriter pw = new PrintWriter(new FileWriter("car.xml", Charset.forName("UTF-8"), true))) {
            pw.write(Car.toXMLview(car));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
