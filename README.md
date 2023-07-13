# Pay My Buddy App.

Pay my buddy is a web application that helps the users to transfer money between them as rapid and safe as possible. 
The users in "Pay My Buddy" App, are called "Buddies That is from where the name of the application comes from.

**The application was built using these technologies** :
- Java && Spring Boot framework (BackEnd)
- Thymeleaf and Boostrap v5.2 (FrondEnd)
- MySQL (DataBase)
       
## UML Diag of classes

![UML diag de classe](https://github.com/HamzaBenalia/Projet06/assets/99022185/8f3c6164-1a51-4056-8f68-1990ef79c6ad)


## The physical model of the Data

![Modélisation physique des données](https://github.com/HamzaBenalia/Projet06/assets/99022185/2f31128b-6035-4d6d-b76f-6734f922fc5a)

## Set up the DataBase "Pay My Buddy"

**To set up your dataBase please, follow these steps below  :**

Create a new database called paymybuddy with the following command : CREATE DATABASE paymybuddy;<br/>
-Import the schema.sql file (in the main/resources folder) to create the tables
-Import the data.sql file (Optional) if you want to test the fucntionalities of the dataBase.
-Ps : data.sql is a prefilled file with a ready data to be tested.

## Application.properties
You can custom the application by editing the `application.properties` file in the main/resources folder.
1. `server.port= [number]` : sets the local port of the web application : spring.datasource.url=jdbc:mysql://localhost:portNumber/paymybuddy

## Starting the application
In the root folder, open a command prompt (**cmd.exe**) and type the following command to start the application : `mvn spring-boot:run`
If the database was properly setup, the application should start. <br>
Otherwise, you will need to check your the ports, the password of the database if any etc...
The url of the application is set at : [http://localhost:8080](http://localhost:8080)



