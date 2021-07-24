/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Mauricio Rios
 */
package ucf.assignments;

public class Item {
    //initialize float value
    public String value;
    //initialize string serialNumber
    public String serialNumber;
    //initialize string name
    public String name;

    //declare constructor with value, serialNumber, and name
    public Item(String value, String serialNumber, String name) {
        this.value = value;
        this.serialNumber = serialNumber;
        this.name = name;
    }

    // create all getters and setters


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // create toString for object
    @Override
    public String toString() {
        return "Item{" +
                "value=" + value +
                ", serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}