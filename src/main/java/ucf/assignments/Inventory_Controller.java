/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Mauricio Rios
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Inventory_Controller implements Initializable {

    // initialize global ArrayList<Item> InventoryList to store all items
    public ArrayList<Item> InventoryList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryTableView.setItems(observableList);
    }

    ObservableList<Item> observableList = FXCollections.observableArrayList();

    @FXML
    public TableView<Item> inventoryTableView;
    @FXML
    public TableColumn<Item, String> serialNumberColumn;
    @FXML
    public TableColumn<Item, String> nameColumn;
    @FXML
    public TableColumn<Item, Float> valueColumn;
    @FXML
    public TextField addSerialNumberDisplay;
    @FXML
    public TextField addItemNameDisplay;
    @FXML
    public TextField addItemValueDisplay;
    @FXML
    public TextField editValueDisplay;
    @FXML
    public TextField editSerialNumberDisplay;
    @FXML
    public TextField editNameDisplay;
    @FXML
    public TextField searchDisplay;
    @FXML
    public TextField pathDisplay;
    @FXML
    public CheckBox TSVCheckbox;
    @FXML
    public CheckBox HTMLCheckbox;
    @FXML
    public CheckBox JSONCheckbox;

    @FXML
    public void addItemButtonClicked(ActionEvent actionEvent) {
        String serialNumber = addSerialNumberDisplay.getText();
        String name = addItemNameDisplay.getText();
        String stringValue = addItemValueDisplay.getText();
        Float value = Float.valueOf(stringValue);

        // Get info from 3 input displays
        // Make sure that serial number has only numbers and letters
        // Make sure the serial number is not already assigned
            // if does not satisfy condition turn to red or clear and set new text prompt
        // Make sure name is between 2 and 256 characters
            // if does not satisfy conditions turn text red or clear and set text prompt
        // Make sure price follows $399.99 format
        // if all is good send info to method to add to list
        Item newItem = addItem(value, serialNumber, name);
        InventoryList.add(newItem);
        addToObservableList();
        addItemValueDisplay.clear();
        addSerialNumberDisplay.clear();
        addItemNameDisplay.clear();
        // Method returns item
        // add item to list
        // Update observable list
        // Clear display
    }

    @FXML
    public void editItemClicked(ActionEvent actionEvent) {
        // Get selected index in tableview
        int index = inventoryTableView.getSelectionModel().getSelectedIndex();
        String name = editNameDisplay.getText();
        String serialNumber = editSerialNumberDisplay.getText();
        String fValue = editValueDisplay.getText();
        Float value = Float.valueOf(fValue);
        // Get inputs for 3 edit displays
            // Make sure they satisfy conditions:
            // Serial number repetition and format
            // Name has right amount of characters
            // Value format
        // Send index and 3 strings to method
        Item editedItem = editItem(index, value, serialNumber, name);
        InventoryList.remove(index);
        InventoryList.add(index, editedItem);
        editValueDisplay.clear();
        editNameDisplay.clear();
        editSerialNumberDisplay.clear();
        addToObservableList();
        // method returns editedItem
        // remove old item and add returned item
        // update observable list
    }

    @FXML
    public void searchSerialNumberClicked(ActionEvent actionEvent) {
        // Get input in search display
        // send to method
            // method searches and makes a new observable list with common search string
        searchSerialNumber();
        // returned list is added to observable list
        // Clear display
    }

    @FXML
    public void searchNameClicked(ActionEvent actionEvent) {
        // Get input in search display
        // send to method
            // method searches and makes a new observable list with common search string
        searchName();
        // returned list is added to observable list
        // Clear display
    }

    @FXML
    public void saveFileClicked(ActionEvent actionEvent) {
        // Get string in path display
        // Get the checkbox clicked and make sure only one is selected
            // or disable the others the moment one is clicked
        // If TSV checkbox clicked send to saveTSV method
        saveTSV();
        // If HTML checkbox clicked send to saveHTML
        saveHTML();
        // if JSON checkbox clicked send to saveJSON
        saveJSON();
        // Method returns string and we add here
        // Clear display
    }

    @FXML
    public void loadFileClicked(ActionEvent actionEvent) {
        // Get string in path display
        // Get the checkbox clicked and make sure only one is selected
            // or disable the others the moment one is clicked
        // If TSV checkbox clicked send to loadTSV method
        loadTSV();
        // If HTML checkbox clicked send to loadHTML method
        loadHTML();
        // if JSON checkbox clicked send to loadJSON method
        loadJSON();
        // Method returns string and we add here
        // Clear display
    }

    @FXML
    public void deleteItemClicked(ActionEvent actionEvent) {
        // get selected index
        int index = inventoryTableView.getSelectionModel().getSelectedIndex();
        // send index to method to delete
        deleteItem(index);
        // update the observable list
        addToObservableList();
        editValueDisplay.clear();
        editNameDisplay.clear();
        editSerialNumberDisplay.clear();
    }

    @FXML
    public void itemClicked(MouseEvent mouseEvent) {
        int itemIndex = inventoryTableView.getSelectionModel().getSelectedIndex();
        editValueDisplay.setText(String.valueOf(InventoryList.get(itemIndex).value));
        editSerialNumberDisplay.setText(InventoryList.get(itemIndex).serialNumber);
        editNameDisplay.setText(InventoryList.get(itemIndex).name);
    }

    @FXML
    public void viewAllInventoryClicked(ActionEvent actionEvent) {
        addToObservableList();
    }

    public Item addItem(Float value, String serialNumber, String name) {
        // create new item with given values and return  it
        return new Item(value,serialNumber, name);
    }

    public Item editItem(int index, Float value, String serialNumber, String name) {
        // get the selected item
        Item item = InventoryList.get(index);
        // update the value
        item.setValue(value);
        // update the serialNumber
        item.setSerialNumber(serialNumber);
        // update the name
        item.setName(name);
        // return editedItem
        return item;
    }

    public void searchSerialNumber() {
        // receive the search string
        // compare the string to all serial numbers within the list
        // if the string matches create a new list with the matching items
        // return list
    }

    public void searchName() {
        // receive the search string
        // compare the string to all names within the list
        // if the string matches create a new list with the matching items
        // return list
    }

    public void saveJSON() {
    }

    public void saveHTML() {
    }

    public void saveTSV() {
    }

    public void loadJSON() {
    }

    public void loadHTML() {
    }

    public void loadTSV() {
    }

    public void deleteItem(int index) {
        // with index use remove() to delete item
        InventoryList.remove(index);
    }

    public void addToObservableList() {
        // clear the tableView for a new list
        observableList.clear();
        // add our Arraylist to the observable list
        observableList.addAll(InventoryList);
    }
}