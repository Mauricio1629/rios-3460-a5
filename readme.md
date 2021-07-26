#Inventory Program Readme

##Guide On How To Use The Program

Adding an item:

    - To add an item to the list fill in the top three textFields and click the "Add Item" button.

        - Value textField must be only numbers
        - Serial number must be only 10 numbers or letters
        - Name must be within 2 - 256 characters

    - Failure to follow the constraints will give an error message within the textfield

Deleting an Item:

    - Click on the item you would like to delete and then click the "Delete Item" button.
    
Editing an Item: 
    
    - Click on the item you want to edit and it will appear in the bottom textfields.
      Once done editing click the "Edit Selected Item" button

        - The same constraints apply to the bottom display


Searching for an Item:

    - Searching by Serial Number:

        - Write in the search textField the characters you want to find from all serial numbers
          and click the "Search by Serial Number" button.

          - Example serial number: H5J3L2L25H   
            If we write in the search L2 then this number will appear with all others that also contain L2

    - Searching by names:

        - Same idea has the previous search, type in the search textField but click "Search by Name"
            - If we type "Ch" all names with ch will appear no matter if its upper or lower case

Return to viewing the whole list:
    
    - Just click the "View All Inventory" button to bring back the whole list

Saving:

    - To save, type the path to where you want the file to save in the bottom textField, click on the circle for the
    format in which you want to save and click the "Save File" button.
    
        - The path should follow this order:
            - Example from my computer(Apple): /Users/mauriciorios/Desktop/Test
        
        - Write the path and the name for the file but there is not need to include .txt or .html

    - You should receive a positive or negative message in the textField stating if save was successful or not

Loading: 

    - To load type the path to where the file is located in the bottom textField, click on the circle for the
    format that the file has and click the "Load File" button.
    
        - The path should follow this order:
            - Example from my computer(Apple): /Users/mauriciorios/Desktop/Test.txt (or .html)

    - You should receive a positive or negative message in the textField stating if loading was successful or not

Sorting:

    - To sort simply click one, two and three times times the cellBox for the titles on the list. (Value, Serial Number, Name)

        - Value: If you click once It will organize everything from least to most to most. Click again for most
        to least and a final time to have it the original way.

        - Serial Numbers: If you click once It will organize everything from 0 to 9 and then A to Z. Click again for
        the opposite and a third click to have the original view

        - Name: Click once to sort from A-Z, a second click for Z-A and the third for the original view.

    - There is a triangle that will point to where the list is sorting towards
        