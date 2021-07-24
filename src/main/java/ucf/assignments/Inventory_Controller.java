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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inventory_Controller implements Initializable {

    // initialize global ArrayList<Item> InventoryList to store all items
    public ArrayList<Item> InventoryList = new ArrayList<>();

    @Override
    // initialize observableList to be set in the tableview
    public void initialize(URL location, ResourceBundle resources) {
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryTableView.setItems(observableList);
    }

    // Create our observableList
    ObservableList<Item> observableList = FXCollections.observableArrayList();

    @FXML
    public TableView<Item> inventoryTableView;
    @FXML
    public TableColumn<Item, String> serialNumberColumn;
    @FXML
    public TableColumn<Item, String> nameColumn;
    @FXML
    public TableColumn<Item, String> valueColumn;
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
        // get the input for serial number and change to uppercase
        String serialNumber = addSerialNumberDisplay.getText().toUpperCase();
        // get string for name
        String name = addItemNameDisplay.getText();
        // get string for value
        String inputValue = addItemValueDisplay.getText();

        // counter to keep track that inputs are valid
        int conditionCounter = 0;
        // flag variable so when we pass to methods we know if we are using add displays or edit displays
        int location = 1;
        /* pass the name string to method to check that it passes the requirements the
          method returns 1 if it passes and 0 if it doesn't */

        conditionCounter += checkName(name, location);
        // pass the serialNumber string to method to check it and method returns 1 if it passes and 0 if it doesn't
        conditionCounter += checkSerialNumber(serialNumber, location);
        // pass value to check to see that string is only numbers
        conditionCounter += checkValue(inputValue, location);

        // format the string to have $X.XX
        String value = formatString(inputValue);

        // if all the conditions are satisfied it should add up to 3 and if it does it can now add the item to the list
        if(conditionCounter == 3) {
            // send the inputs to the method that will return the created item
            Item newItem = createItem(value, serialNumber, name);
            // add the returned item to our list
            InventoryList.add(newItem);
            // update the observable list
            addToObservableList(InventoryList);
            // clear the input displays for the 3 strings
            clearDisplays(location);
        }
    }

    @FXML
    public void editItemClicked(ActionEvent actionEvent) {
        // Get selected index in tableview
        int index = inventoryTableView.getSelectionModel().getSelectedIndex();
        // Now we get the serialNumber for the selected index to find our item in the list
        String number = observableList.get(index).serialNumber;

        // get name from editNameDisplay
        String name = editNameDisplay.getText();
        // get serialNumber from editSNDisplay and change to upper case
        String serialNumber = editSerialNumberDisplay.getText().toUpperCase();
        // get value for editDisplay
        String inputValue = editValueDisplay.getText();

        // counter to keep track that inputs are valid
        int conditionCounter = 0;
        // flag variable so when we pass to methods we know if we are using add displays or edit displays
        int location = 2;
        // send the name input to make sure its valid and it returns 1 if it does and 0 if not

        conditionCounter += checkName(name, location);
        // pass the serialNumber string to method to check it and method returns 1 if it passes and 0 if it doesn't
        conditionCounter += checkSerialNumber(serialNumber, location);
        // pass value to check to see that string is only numbers
        conditionCounter += checkValue(inputValue, location);

        // format the string to have $X.XX
        String value = formatString(inputValue);

        // if all the conditions are satisfied it should add up to 3 and if it does it can now add the item to the list
        if(conditionCounter == 3) {
            // send inputs to method that will return edited item
            Item editedItem = editItem(number, value, serialNumber, name);
            // clear displays with location id
            clearDisplays(location);
            // remove the unedited item
            observableList.remove(index);
            // add the edited item to the tableview
            observableList.add(index, editedItem);
        }
    }

    @FXML
    public void searchSerialNumberClicked(ActionEvent actionEvent) {
        // Get input in search display and change to upper case
        String search = searchDisplay.getText().toUpperCase();
        // send to method which returns a list of items that match the search string
        ArrayList<Item> searchedList = searchSerialNumber(search);
        // returned list is added to observable list
        addToObservableList(searchedList);
        // Clear the search display
        searchDisplay.clear();
        // clear edit displays
        clearDisplays(2);
    }

    @FXML
    public void searchNameClicked(ActionEvent actionEvent) {
        // Get input in search display and change to upper case
        String search = searchDisplay.getText().toUpperCase();
        // send to method which returns a list of items that match the search string
        ArrayList<Item> searchedList = searchName(search);
        // the returned list is added to observable list
        addToObservableList(searchedList);
        // Clear the search display
        searchDisplay.clear();
        // clear edit displays
        clearDisplays(2);
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
        // get selected index from the tableview
        int index = inventoryTableView.getSelectionModel().getSelectedIndex();
        // get the serial number of the selected index
        String serialNumber = inventoryTableView.getItems().get(index).serialNumber;
        // send the serialNumber of the selected item to be deleted in the method
        deleteItem(serialNumber);
        // get the item that was deleted from the Inventory list
        Item item = observableList.get(index);
        // delete the item from the observable list
        observableList.remove(item);
        // clear the displays
        clearDisplays(2);
    }

    @FXML
    public void itemClicked(MouseEvent mouseEvent) {
        // get index of clicked item
        int itemIndex = inventoryTableView.getSelectionModel().getSelectedIndex();
        // pull the selected item out the list
        Item item = observableList.get(itemIndex);
        // set its values to the edit displays
        editValueDisplay.setText(item.value.substring(1));
        editSerialNumberDisplay.setText(item.serialNumber);
        editNameDisplay.setText(item.name);
    }

    @FXML
    public void viewAllInventoryClicked(ActionEvent actionEvent) {
        // Show our whole list (This is for when we are searching and we are done and want our whole list)
        addToObservableList(InventoryList);
    }

    public Item createItem(String value, String serialNumber, String name) {
        // create new item with given values and return  it
        return new Item(value,serialNumber, name);
    }

    public Item editItem(String number, String value, String serialNumber, String name) {
        // create an empty item
        Item item = new Item(null,null,null);
        // for loop through our list
        for(int i = 0; i< InventoryList.size(); i++){
            // if the serialNumber of the item we want to edit is equal to one in our list we set the new input values
            if(InventoryList.get(i).serialNumber.equalsIgnoreCase(number)){
                // set new value to the empty item
                item.setValue(value);
                // set new serialNumber to the empty item
                item.setSerialNumber(serialNumber);
                // set new name to the empty item
                item.setName(name);
                // remove our old item from the list
                InventoryList.remove(i);
                // add our new item at the same index
                InventoryList.add(i,item);
                // break out the loop
                break;
            }
        }
        // return the new/edited item
        return item;
    }

    public ArrayList<Item> searchSerialNumber(String search) {
        // create a new list to be shown in the observable list
        ArrayList<Item> searchList = new ArrayList<>();
        // for loop through our Inventory list
        for (Item item : InventoryList) {
            // if the search string matches to any serialNumbers
            if (item.serialNumber.toUpperCase().contains(search)) {
                // we add to our new list
                searchList.add(item);
            }
        }
        // return the list with all matched serialNumber to be displayed in the observable list
        return searchList;
    }

    public ArrayList<Item> searchName(String search) {
        // create a new list to be shown in the observable list
        ArrayList<Item> searchList = new ArrayList<>();
        // for loop through our Inventory List
        for (Item item : InventoryList) {
            // if the search string matches to any names
            if (item.name.toUpperCase().contains(search)) {
                // we add to our new list
                searchList.add(item);
            }
        }
        // return the list with all matched names to be displayed in the observable list
        return searchList;
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

    public void deleteItem(String serialNumber) {
        // for loop through our list
        for(int i=0; i< InventoryList.size(); i++) {
            // if the serialnumber matches to one in the list
            if(serialNumber.equalsIgnoreCase(InventoryList.get(i).serialNumber)){
                // remove it
                InventoryList.remove(i);
                // break out the loop
                break;
            }
        }
    }

    public void addToObservableList(ArrayList<Item> List) {
        // clear the tableView for a new list
        observableList.clear();
        // add the given ArrayList to the observable list
        observableList.addAll(List);
        // set the new observable list to the table view
        inventoryTableView.setItems(observableList);
    }

    public int checkName(String name, int location) {
        // create flag that will change 1 if the name passed the constraints
        int flag = 0;
        // if the name is between 2 and 256 (inclusive)
        if(name.length() >= 2 && name.length() <= 256) {
            // raise flag by 1
            flag++;
            // set the text back to black if it was changed
            addItemNameDisplay.setStyle("-fx-text-inner-color: black");
            // set the text back to black if it was changed
            editNameDisplay.setStyle("-fx-text-inner-color: black");
        // if the name is shorter than 2 characters
        } else if(name.length() < 2) {
            // if the location for the name is upper input displays
            if(location == 1) {
                // set error message to the addItemNameDisplay
                addItemNameDisplay.setText("ERROR-TOO SHORT");
                // Turn that message red to show it is an error
                addItemNameDisplay.setStyle("-fx-text-inner-color: red");
            // else means we show error on the bottom displays
            } else {
                // set error message to editNameDisplay
                editNameDisplay.setText("ERROR-TOO SHORT");
                // Set text for error red to show its an error
                editNameDisplay.setStyle("-fx-text-inner-color: red");
            }
        // else the string was over 256
        } else {
            //
            if (location == 1) {
                // set error message to the addItemNameDisplay
                addItemNameDisplay.setText("ERROR-TOO LONG");
                // set the message to red to show it is an error
                addItemNameDisplay.setStyle("-fx-text-inner-color: red");
            // else we want the bottom displays
            } else {
                // set error message to editNameDisplay
                editNameDisplay.setText("ERROR-TOO LONG");
                // set text for error red to show its an error
                editNameDisplay.setStyle("-fx-text-inner-color: red");
            }
        }
        // return the flag value
        return flag;
    }

    public int checkSerialNumber(String serialNumber, int location) {
        // counter to know that serialNumber passes requirements
        int counter = 0;
        // flag to know if the serialNumber already exists within the list
        int flag = 0;
        // We check to see that serial number has the right amount of characters first
        if(serialNumber.length() != 10) {
            // if statement for location to know where to place error messages
            if(location == 1) {
                // set error message for the input display
                addSerialNumberDisplay.setText("ERROR-INPUT 10 CHARACTERS");
                // change the message to red to show its an error
                addSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
            }else {
                // set error message for the input display
                editSerialNumberDisplay.setText("ERROR-INPUT 10 CHARACTERS");
                // change the message to red to show its an error
                editSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
            }
            // return the counter to stop the method from continuing
            return counter;
        }
        // regex for alphabet and numbers
        String regex = "^[a-zA-Z0-9]+$";
        // create pattern and compile the regex
        Pattern pattern = Pattern.compile(regex);
        // create matcher for the pattern
        Matcher matcher = pattern.matcher(serialNumber);
        // if the serialNumber matches the regex we now check to see if it exists within the list
        if(matcher.matches()) {
            // if statement to know if we are adding a new number
            if (location == 1) {
                // for loop for the inventoryList
                for (Item item : InventoryList) {
                    // if the serialNumber equals any within the list we give error message
                    if (serialNumber.equalsIgnoreCase(item.serialNumber)) {
                        // raise the flag because the given serialNumber exists
                        flag++;
                        // message for the input display
                        addSerialNumberDisplay.setText("ERROR-SERIAL EXISTS");
                        // change the message to red to show its an error
                        addSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
                    }
                }
             // else statement means that we are checking a edited item
            } else {
                // get the index of selected item
                int index = inventoryTableView.getSelectionModel().getSelectedIndex();
                Item item = observableList.get(index);
                // copy our InventoryList to a temporary one to search
                ArrayList<Item> EditList = new ArrayList<>(InventoryList);
                // remove the index item because if we add the same it will think its repeated
                EditList.remove(item);
                // for loop for our temporary list
                for (Item value : EditList) {
                    //if the serialNumber equals any within the list we give error message
                    if (serialNumber.equalsIgnoreCase(value.serialNumber)) {
                        // raise the flag because the given serialNumber exists
                        flag++;
                        // message for the input display
                        editSerialNumberDisplay.setText("ERROR-SERIAL EXISTS");
                        // change the message to red to show its an error
                        editSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
                    }
                }
            }
            // if the serial number does not exist the flag remained 0
            if(flag ==0) {
                // counter +1 to return and know that it has passed all tests
                counter++;
                // turn our text to black in case it is red
                addSerialNumberDisplay.setStyle("-fx-text-inner-color: black");
                editSerialNumberDisplay.setStyle("-fx-text-inner-color: black");
            }
            // if the serial number exists our flag is more than 0 so we give error message
        } else {
            // if location 1 then we give error to the top displays
            if (location == 1) {
                // Give top display error message
                addSerialNumberDisplay.setText("ERROR-USE ALPHANUMERIC");
                // Give top text red color
                addSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
                // else for bottom displays
            } else {
                // Give top display error message
                editSerialNumberDisplay.setText("ERROR-USE ALPHANUMERIC");
                // Give top text red color
                editSerialNumberDisplay.setStyle("-fx-text-inner-color: red");
            }
        }
        // return the counter which has 0 for not passing tests and 1 if passed
        return counter;
    }

    public int checkValue(String value, int location) {
        // flag to know string passes tests
        int flag = 0;
        // if statement matching string to regex to see if it contains only positive numbers and decimals
        if(value.matches("^[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$")) {
            // raise the flag to know that it passed
            flag++;
            // if location 1 we change the text in top display to black
            if(location==1) {
                addItemValueDisplay.setStyle("-fx-text-inner-color: black");
            } else {
                // otherwise change the bottom display to black
                editValueDisplay.setStyle("-fx-text-inner-color: black");
            }
            // else the string did not match
        } else {
            // if location 1 then we set error message to top display
            if(location==1) {
                // error message if the string does not match regex
                addItemValueDisplay.setText("ERROR-ONLY POSITIVE NUMBERS");
                // give it red color to show error
                addItemValueDisplay.setStyle("-fx-text-inner-color: red");
                // else we show error in bottom display
            } else {
                // error message for string
                editValueDisplay.setText("ERROR-ONLY POSITIVE NUMBERS");
                // give string red color
                editValueDisplay.setStyle("-fx-text-inner-color: red");
            }
        }
        // return flag, 1 if it passed and 0 if it didn't
        return flag;
    }

    public String formatString(String stringValue){
        // currency format for the tableview
        DecimalFormat currency = new DecimalFormat("$0.00");
        // change the given string and convert to double
        Double value  = Double.valueOf(stringValue);
        // return the value as a string and with the currency format
        return currency.format(value);
    }

    public void clearDisplays(int location) {
        // if location 1 then we clear top displays
        if(location == 1){
            addItemValueDisplay.clear();
            addSerialNumberDisplay.clear();
            addItemNameDisplay.clear();
        // else we want to clear bottom displays
        } else {
            editValueDisplay.clear();
            editNameDisplay.clear();
            editSerialNumberDisplay.clear();
        }
    }
}