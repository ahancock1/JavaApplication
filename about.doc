JavaApplication
===============
Database, file reader and API project


Introduction
This application is designed to read the contents of data files, populate the tables in the database, fetch records and print them to the console window. The project has been created in Eclipse IDE using JavaSE-1.7, JUnit 4.0 for testing and JDBC for connection to the database. The application has Javadoc comments and commentary throughout, in addition to a generated UML diagram to increase maintainability of code.


Method of Approach
To design the application successfully a few assumptions have to be made. An assumption that the primary key identifier for person(s) and order(s) is an alphanumeric value; the primary key for a person may be a username which is unique to the person. The database table ORDER has been renamed to ORDERs as ORDER is a reserved name in SQL; best practice is to avoid using reserved names. The data files must be placed in the root of the application for the FileHandler to read.


Design
The application is designed using the following list of classes and the application structure is represented in the UML diagram shown in figure 1:
Main.java
Person.java
Order.java
FileHandler.java
DatabaseHandler.java
DatabaseConnection.java
Figure 1. UML diagram of the project.


Main.java
This class contains final global variables FILENAMES, FILE_SUFFIX, DATABASE, USER, and PASSWORD. FILENAMES is a string array used for storing the names of files to be loaded. The FILE_SUFFIX contains the extension of the file, in this case its “.data”. Strings DATABASE, USER and PASSWORD are used for the connection to the database; these variables need to be changed to access the database with correct database name and root credentials.

The main method in this class is utilised for initialising a new FileHandler and DatabaseHandler, reading the data files, populating the database, fetching and printing records from the database to the console window.


Person.java
This class contains the required private variables that represent a person from the person data file; variables can be accessed through the suitable get methods. A person contains an ArrayList of type Order for all orders placed by this person. As a person may have zero to many relationship with orders, there are two constructors; one to initialise a person without any orders and one that takes an ArrayList of orders. Orders can be added to a person using the addOrder method which takes an order to be added to the ArrayList.


Order.java
This class contains the required private variables that represent an order from the order data file; variables can be accessed through the suitable get methods.


FileHandler.java 
This class contains a read method which reads a file specified by the filename and the file suffix. The read method checks the primary key of each record is not empty and that the number of fields per record meets the number of columns required. The contents of the file is read line by line, split, trimmed and stored in the split string array.  The split lines are then added to an ArrayList and returned. This provides improved re-usability and means a new FileHandler instance does not need to be initiated for each file. A write method would be implemented similarly.

The read method returns an ArrayList of string arrays as it optimises the code. Another approach would be to return an ArrayList of Person and use a switch statement based on column name found in the data file and add orders to person. This approach would take longer as it is effectively searching and sorting the data. As this is going to be done when fetching data from the database, it seems useless to implement such code in this class.


DatabaseHandler.java
This class extends the database connection class which establishes a connection to the database. The add method is responsible for handling types of items and inserts them into the appropriate table in the database using the item type. The getPersonByOrder method will fetch all people from the database that has placed an order.  An ArrayList is created that contains the people who have placed an order. This method searches all orders within the database, creates and adds the order to the corresponding person which is stored in the ArrayList. If an order has been placed by a person already existing within the ArrayList the order is added to the person in the ArrayList. 


DatabaseConnection.java
This class is responsible for establishing a connection to the database using a JDBC connection and executing SQL statements required to insert and fetch data from the database. Errors are handled using try and catch statements which will display appropriate error messages to the console window. Connections to the database are managed whenever a record is inserted or fetched.


Testing
Testing the application was done using JUnit 4.0 where each class methods have been tested individually for their predicted outputs.
