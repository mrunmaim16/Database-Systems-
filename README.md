**Title: Java-based CRUD Operations on FreshFood Database via Command Line/Terminal**

**Steps to Run the Project:**

1. Open the current working directory in IntelliJ or Eclipse IDE.
2. Run Main.java file.
3. If jdk is not configured, execute the following commands in the command prompt:

```bash
javac Main.java
java -classpath <path-of-ojdbc8-21.1.0.0.jar>; Main
```

For example:

```bash
java -classpath C:/Users/mrunmai/IdeaProjects/db1/src/ojdbc8-21.1.0.0.jar; Main
```

**Table of Contents:**
- Main.java
- menu.java
- searchMenu.java
- updateMenu.java
- analyseMenu.java
- DatabaseConn.java

**Description:**

This command-line program offers menu-driven CRUD operations. Users can search for employee or customer details, update order information, and obtain reports on order details, monthly sales, delivery details, and product details.

**File: menu.java**

- **Search Functionality:** Allows users to search for employee or customer details based on ID, name, or view all records.

- **Update Functionality:** Supports updating order details, payment methods, and delivery details based on user input.

- **Analyze Functionality:** Offers various analytical reports, including order analysis, monthly sales, street-wise deliveries, and daily maximum sales.

**DatabaseConn.java:**

This file serves as the connection link between the database and the terminal. It handles JDBC connection, user authentication, and contains business logic for CRUD operations. The `closeConnection` method can be called anytime to close the connection.

Note: Ensure the ojdbc8-21.1.0.0.jar is added to project dependencies before running the project.
