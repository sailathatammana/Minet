# Minet

Minet is a terminal based warehouse application which can be used by cashiers, managers and admin to sell and return the
products from customers and also check the profits and statistics of their business.

## 1. Setup

### 1.1 Requirements:

- Java 11
- GIT (optional)
- IDE able to build projects if you plan to run from an IDE.

### 1.2 Things to remember before running the application

1. The following are the login details for different user roles for using the application

- Admin Login details: ravilatha Password: 12345678
- Manager Login details: latha Password: 12345678
- Cashier1 Login details: ravi Password: 12345678
- Cashier2 Login details: sai Password: 12345678

2. Make sure to place the assets folder in same path of jar file placed before running the jar file in the terminal.

3. Inventory details can be added in inventory.txt file in the assets folder. If you would like to add more items in
   inventory, please make sure all the fields are written. The fields in the inventory are

- Id, Product name, Description, Selling price, Cost Price, Quantity.
- For Example: 1,Pen,It is used to write,10.0,8.0,28

### 1.3 Using an IDE and GIT

1. In your IDE, navigate to the directory where you would like to store the application.

2. Clone the project repo and pull a copy of the repo to your local machine using the following command.

   `$ https://github.com/sailathatammana/Minet`

3. Navigate to project folder, src, com, company and Main.java.

4. Run the Main.java file using the IDE's run command.

### 1.4 Using a terminal and GIT

1. Navigate to the directory where you would like to store the application.

2. Clone the project repo and pull a copy of the repo to your local machine using the following command.

   `$ https://github.com/sailathatammana/Minet`

3. Open terminal, build the application and generate a jar file

4. Run using "`java -jar Minet.jar`"

## 2. Project organization

### 2.1 Requirement gathering

A spreadsheet with the information related to the organization of the project. It includes user-stories, tasks and time
estimation.

[Google Spreadsheets link](https://docs.google.com/spreadsheets/d/19Jp_jAzemHeTWeVOYk1qFjNQl6qWjSmLYeXWBT_RGTQ/edit#gid=1628705767)

### 2.2 Use case diagram

A low detail diagram to visualize how the application will work.

[Use case diagram](https://bit.ly/3S9uEGe)

### 2.3 Class diagrams

The class diagram allows to visualize the overall hierarchy of the project.

[Overview of the project](https://bit.ly/3r0kbRg)

[Cashier view of class diagram](https://bit.ly/3UpBnNL)

[Manager view if class diagram](https://bit.ly/3fcaUCW)

[Admin view of class diagram](https://bit.ly/3LBpUX9)




