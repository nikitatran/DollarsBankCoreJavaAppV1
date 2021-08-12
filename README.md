# DollarsBankCoreJavaAppV1

## Project Description

Banking console app made in Java, with colored console output. Uses layered architecture.

User can
* Create new account
* Login/signout
* Deposit/withdraw from checking/savings account
* Transfer funds between checking and savings account
* View 5 most recent transactions
* See their information

## Set up

### Create local MySQL database
Please use [this script](https://gist.github.com/nikitatran/e4f8ffaacfbb40bb961e0970febdd4d7) to create the database schema.

### Config file
In the `com.dollarsbank.connection` package, add a `config.properties` file that is structured as follows:
```
url=jdbc:mysql://localhost:3306/dollarsbank
username=root
password=root
```
Change the username and password to what yours is for your localhost connection, and the port number if necessary.

### MySQL Connector Jar
You may need to add the MySQL Connector .jar to the build path if you are unable to connect to the database.

### Colored Console Output (Eclipse)
This project was created in Eclipse IDE. In order to view the ANSI color-coded console output in Eclipse's console, you have to go to Help > Eclipse Marketplace, and install "ANSI Escape in Console".
