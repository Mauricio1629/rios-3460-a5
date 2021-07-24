/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Mauricio Rios
 */
package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Inventory_ControllerTest {

    @Test
    void TestingCreatingAnItemAssertTrueForBooleanIfContentsAreEquals() {
        Inventory_Controller TC = new Inventory_Controller();

        // create expected item
        Item expected = new Item("15.99","A1S2D3F4G5","Shirt");
        // input same values for the output item
        Item output = TC.createItem("15.99","A1S2D3F4G5","Shirt");

        // if the value, serialNumber and name are equal for expected and output, give boolean true
        boolean equals = expected.value.equals(output.value) && expected.serialNumber.equals(output.serialNumber)
                && expected.name.equals(output.name);
        // assert true for equals for the boolean
        assertTrue(equals);
    }

    @Test
    void TestingCreatingAnItemAssertFalseForBooleanIfContentsAreNotEqual() {
        Inventory_Controller TC = new Inventory_Controller();

        // create expected item
        Item expected = new Item("16","1A2S3D4F5G","Shirt");
        // give the output different values
        Item output = TC.createItem("15.99","A1S2D3F4G5","Shirt");
        // if the value, serialNumber and name are not equal for expected and output, the boolean will be given false

        boolean equals = expected.value.equals(output.value) && expected.serialNumber.equals(output.serialNumber)
                && expected.name.equals(output.name);
        // assert false for the boolean
        assertFalse(equals);
    }

    @Test
    void TestingEditingAnItemAssertEqualsForBooleanForSameValues() {
        Inventory_Controller TC = new Inventory_Controller();

        // Create items to add to our list
        Item item1 = new Item("13.49","P1O2I3U4Y5","Meal");
        Item item2 = new Item("45.50","ZXC098AS12","Membership");
        Item item3 = new Item("32.76","BN12MN45BV","Alien");

        // Add the items to our global list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);

        // create expected item
        Item expected = new Item("25.99","21SA890CXZ","Discounted Membership");
        // give serialNumber of the Item we are changing and the new values for value, serialNumber and Name
        Item output = TC.editItem("ZXC098AS12","25.99","21SA890CXZ","Discounted Membership");

        // if the contents of expected and output are equal the boolean will be true
        boolean equals = expected.value.equals(output.value) && expected.serialNumber.equals(output.serialNumber)
                && expected.name.equals(output.name);

        // assert true for boolean
        assertTrue(equals);
    }

    @Test
    void TestingEditingAnItemAssertFalseForBooleanForDifferentValues() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our list
        Item item1 = new Item("13.49","P1O2I3U4Y5","Meal");
        Item item2 = new Item("45.50","ZXC098AS12","Membership");
        Item item3 = new Item("32.76","BN12MN45BV","Alien");

        // Add the items to our global list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);

        // create expected item which will be the original values
        Item expected = new Item("45.50","ZXC098AS12","Membership");

        // give serialNumber of the Item we are changing and the new values for value, serialNumber and Name
        Item output = TC.editItem("ZXC098AS12","25.99","21SA890CXZ","Discounted Membership");
        // if the contents of expected and output are not equal the boolean will be false

        boolean equals = expected.value.equals(output.value) && expected.serialNumber.equals(output.serialNumber)
                && expected.name.equals(output.name);
        // assert false for boolean
        assertFalse(equals);
    }

    @Test
    void TestingSearchingSerialNumbersForJ3() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our global list
        Item item1 = new Item("4.99","G5H4J3K2L1","Lays");
        Item item2 = new Item("2.34","MN09BV76C4","Juice");
        Item item3 = new Item("10","6G7F8DJ5Y4","Chicken");
        Item item4 = new Item("50","M4N3J3P16P","Ribeye");

        // Add the items to the list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);
        TC.InventoryList.add(item4);

        // create a expected Arraylist
        ArrayList<Item> expectedList = new ArrayList<>();

        // create the items that will show up when searching
        Item item01 = new Item("4.99","G5H4J3K2L1","Lays");
        Item item02 = new Item("50","M4N3J3P16P","Ribeye");

        // add the items to the expectedList
        expectedList.add(item01);
        expectedList.add(item02);

        // we will search for serialNumbers that have J3 in them. We give the method "J3" and it returns a list
        ArrayList<Item> outputList = TC.searchSerialNumber("J3");

        // create a counter
        int counter = 0;

        // for loop through our expected list and match items to output
        for(int i=0; i<expectedList.size(); i++) {
            // check to see if value, serialNumber and Name equal each other
            if(expectedList.get(i).value.equals(outputList.get(i).value) &&
                    expectedList.get(i).serialNumber.equals(outputList.get(i).serialNumber) &&
                    expectedList.get(i).name.equals(outputList.get(i).name)) {
                // raise counter if item at i equals for both expected and output
                counter++;
            }
        }
        // assert equals for the counter and the size of expected list
        assertEquals(counter, 2);
    }

    @Test
    void TestingSearchingSerialNumbersFor123() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our global list
        Item item1 = new Item("4.99","G5H4TR4GL1","Lays");
        Item item2 = new Item("2.34","MN09B123C4","Juice");
        Item item3 = new Item("10","L1238E5TY4","Chicken");
        Item item4 = new Item("50","M4N3J123K6P","Ribeye");

        // Add the items to the list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);
        TC.InventoryList.add(item4);

        // create a expected Arraylist
        ArrayList<Item> expectedList = new ArrayList<>();

        // create the items that will show up when searching
        Item item01 = new Item("2.34","MN09B123C4","Juice");
        Item item02 = new Item("10","L1238E5TY4","Chicken");
        Item item03 = new Item("50","M4N3J123K6P","Ribeye");

        // add the items to the expectedList
        expectedList.add(item01);
        expectedList.add(item02);
        expectedList.add(item03);

        // we will search for serialNumbers that have 123 in them. We give the method "123" and it returns a list
        ArrayList<Item> outputList = TC.searchSerialNumber("123");

        // create a counter
        int counter = 0;

        // for loop through our expected list and match items to output
        for(int i=0; i<expectedList.size(); i++) {
            // check to see if value, serialNumber and Name equal each other
            if(expectedList.get(i).value.equals(outputList.get(i).value) &&
                    expectedList.get(i).serialNumber.equals(outputList.get(i).serialNumber) &&
                    expectedList.get(i).name.equals(outputList.get(i).name)) {
                // raise counter if item at i equals for both expected and output
                counter++;
            }
        }
        // assert equals for the counter and the size of expected list
        assertEquals(counter, 3);
    }

    @Test
    void TestingSearchNameToSeeIfItContainsTheLetterC() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our global list
        Item item1 = new Item("19.99","G1O2I3D4Y5","Desk");
        Item item2 = new Item("100.23","WXC098AH1V","Case");
        Item item3 = new Item("68.43","PO12MN4576","Toy");
        Item item4 = new Item("4.34","KH12MN4FW4","Chips");

        // Add the items to the list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);
        TC.InventoryList.add(item4);

        // create a expected Arraylist
        ArrayList<Item> expectedList = new ArrayList<>();

        // create the items that will show up when searching
        Item item01 = new Item("100.23","WXC098AH1V","Case");
        Item item02 = new Item("4.34","KH12MN4FW4","Chips");

        // add the items to the expectedList
        expectedList.add(item01);
        expectedList.add(item02);

        // we will search for names that have C in them. We give the method "C" and it returns a list
        ArrayList<Item> outputList = TC.searchName("C");

        // create a counter
        int counter = 0;

        // for loop through our expected list and match items to output
        for(int i=0; i<expectedList.size(); i++) {
            // check to see if value, serialNumber and Name equal each other
            if(expectedList.get(i).value.equals(outputList.get(i).value) &&
                    expectedList.get(i).serialNumber.equals(outputList.get(i).serialNumber) &&
                    expectedList.get(i).name.equals(outputList.get(i).name)) {
                // raise counter if item at i equals for both expected and output
                counter++;
            }
        }
        // assert equals for the counter and the size of expected list
        assertEquals(counter, 2);
    }

    @Test
    void TestingSearchNameToSeeIfItContainsTheLetterS() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our global list
        Item item1 = new Item("19.99","G1O2I3D4Y5","Desk");
        Item item2 = new Item("100.23","WXC098AH1V","Case");
        Item item3 = new Item("68.43","PO12MN4576","Toy");
        Item item4 = new Item("4.34","KH12MN4FW4","Chips");

        // Add the items to the list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);
        TC.InventoryList.add(item4);

        // create a expected Arraylist
        ArrayList<Item> expectedList = new ArrayList<>();

        // create the items that will show up when searching
        Item item01 = new Item("19.99","G1O2I3D4Y5","Desk");
        Item item02 = new Item("100.23","WXC098AH1V","Case");
        Item item03 = new Item("4.34","KH12MN4FW4","Chips");

        // add the items to the expectedList
        expectedList.add(item01);
        expectedList.add(item02);
        expectedList.add(item03);

        // we will search for names that have S in them. We give the method "S" and it returns a list
        ArrayList<Item> outputList = TC.searchName("S");

        // create a counter
        int counter = 0;

        // for loop through our expected list and match items to output
        for(int i=0; i<expectedList.size(); i++) {
            // check to see if value, serialNumber and Name equal each other
            if(expectedList.get(i).value.equals(outputList.get(i).value) &&
                    expectedList.get(i).serialNumber.equals(outputList.get(i).serialNumber) &&
                    expectedList.get(i).name.equals(outputList.get(i).name)) {
                // raise counter if item at i equals for both expected and output
                counter++;
            }
        }
        // assert equals for the counter and the size of expected list
        assertEquals(counter, 3);
    }

    @Test
    void TestingDeletingAnItemFromTheList() {
        Inventory_Controller TC = new Inventory_Controller();
        // Create items to add to our global list
        Item item1 = new Item("20","N4B3M5N6K7","Gas");
        Item item2 = new Item("15","ASDFG12345","Picture");
        Item item3 = new Item("12.50","POI12JH45L","Calculator");
        Item item4 = new Item("52.22","JW999TPNE9","Album");

        // Add the items to the list
        TC.InventoryList.add(item1);
        TC.InventoryList.add(item2);
        TC.InventoryList.add(item3);
        TC.InventoryList.add(item4);

        // create a expected Arraylist
        ArrayList<Item> expectedList = new ArrayList<>();

        // create the items that will show up when searching
        Item item01 = new Item("20","N4B3M5N6K7","Gas");
        Item item02 = new Item("12.50","POI12JH45L","Calculator");
        Item item03 = new Item("52.22","JW999TPNE9","Album");

        // add the items to the expectedList
        expectedList.add(item01);
        expectedList.add(item02);
        expectedList.add(item03);

        // We want to delete Picture from the global list. We give the serial number for it to the method
        TC.deleteItem("ASDFG12345");

        // create a counter
        int counter = 0;

        // for loop through our expected list and match items to output
        for(int i=0; i<expectedList.size(); i++) {
            // check to see if value, serialNumber and Name equal each other
            if(expectedList.get(i).value.equals(TC.InventoryList.get(i).value) &&
                    expectedList.get(i).serialNumber.equals(TC.InventoryList.get(i).serialNumber) &&
                    expectedList.get(i).name.equals(TC.InventoryList.get(i).name)) {
                // raise counter if item at i equals for both expected and output
                counter++;
            }
        }
        // assert equals for the counter and the size of expected list
        assertEquals(counter, 3);
    }

    @Test
    void TestingFormatingAStringThatHasTooManyDecimals() {
        Inventory_Controller TC = new Inventory_Controller();
        // declare input string that will be formatted
        String inputString = "15.49345";
        // create expected string
        String expected = "$15.49";
        // give method the input string that will format it to $X.XX and return it as a string
        String output = TC.formatString(inputString);
        // assert equals for expected and output
        assertEquals(expected, output);
    }

    @Test
    void TestingFormatingAStringThatHasNoDecimals() {
        Inventory_Controller TC = new Inventory_Controller();
        // declare input string that will be formatted
        String inputString = "40";
        // create expected string
        String expected = "$40.00";
        // give method the input string that will format it to $X.XX and return it as a string
        String output = TC.formatString(inputString);
        // assert equals for expected and output
        assertEquals(expected, output);
    }
}