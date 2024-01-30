Title: Accessing the FreshFood database from command line or terminal and performing CRUD operations using java.

Steps to run the project:
Open the current working directory in Intellij or Eclipse IDE
Run Main.java file

Steps to run the project:
Open the current working directory in Intellij or Eclipse IDE
Add the ojdbc8-21.1.0.0.jar to the project dependencies
Run Main.java file
Otherwise
Open command prompt   in the current working directory
Execute the following commands
>  javac Main.java
>  java -class-path <path-of-ojdbc8-21.1.0.0.jar>; Main
For example, refer to the below command
>   java -classpath C:/Users/sameer/IdeaProjects/db1/src/ojdbc8-21.1.0.0.jar; Main

If jdk is not configured in your system then
1. Open the current working directory in Intellij or Eclipse IDE.
2. Run any of the .java files.

Table of Contents:
Main.java, menu.java, searchMenu.java, updateMenu.java, analyseMenu.java, DatabaseConn.java

Description:
This is a menu-driven program that runs on command line or terminal. 
It starts with giving users a choice of searching for employee or customer details, update order details, delivery details and payment methods, and get analyzed reports on Order details, monthly sales, delivery details and product details. 
This switch case is written inside the main.java file and is the starting point for the program. It takes inputs “1” for search, “2” for update, “3” for reports and “exit” for exiting the program

File: menu.java
The program will further lead to a menu.java file for the first three choices that contains three main functions: search, update and analyze for performing the required operations and a function that works on getting product reports. 
Search case takes inputs “emp” for searching an employee, “cust” for searching a customer and “exit” for exiting the application. Users can search for the employee ID, name and all employee details, that take “id” , “name” and “all” for getting respective outputs.
For getting customer details, it takes inputs “id”, “name” and “all” similar to customer to get respective outputs.

Update method has three functionalities namely- updating order details which takes “ord” input for it to execute, payment methods which takes “pay” as input, and delivery details which takes “del” as input. 
Each of these methods further takes input from the user to search for the record, updates it and prints either specific records or the entire table as per their functionalities.

The analyze method has five functionalities namely. 
The first method analyzes order data based on type of order and mode of payment of each customer by name. 
It does not require any input for the user end and simply outputs the analyzed order data. 
The second method takes “month” and “order type” (Delivery or Pick-up) as input and returns a report on the number of sales done in a month that are of the input order type and returns a daywise number. 
The next functionality is divided into 3 methods and the combination performs the task of fetching a report of the number of items delivered is each street and it takes “street name” as the input and searches for the keywords in the address. 
The last functionality takes “day” as the input and fetches the maximum sales done in the day in a span of three months.
For every wrong input, a message saying “Invalid input” pops up on the screen and takes the user back to the input session.

DatabaseConn.java : 
This file is the main connecting link between database and frontend terminal. 
The jdbc connection along with username and password verification is established inside the constructor which is called automatically when the program starts executing. 
The entire business logic is written inside the DatabaseConn.java files that contain methods to perform required actions. 
The connection is closed using the closeConnection method that can be called at any time and in any part of the code. 
