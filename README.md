# Pay My Buddy
Pay My Buddy is a web application that will help the users of this aplication to transfer money in a secured and easy way. The users in the app are called "buddies".

**The application was built based on these technologies** :
- Java and Spring Boot Framework (back-end)
- Thymeleaf and Boostrap v5.2(front-end)
- MySQL & workbench (database management system)
- Java JDK 11

<br>

## UML Diagram

![UML diag de classe](https://github.com/HamzaBenalia/Projet06/assets/99022185/fd60eb9c-8d86-4432-8691-02c65c3fc06d)

**Relations** ℹ️
- One user can only have one bank account.
- One bank account can only have one user
- one user can have one to many relations
- One user can have one to many transactions
  


## Physical Data Model
![Modélisation physique des données](https://github.com/HamzaBenalia/Projet06/assets/99022185/8de85434-691e-46d6-87ae-ef13eb300827)


## Creating the database
Please read and follow step by step :
1. Create a new database called `paymybuddy`. Type the following : `CREATE DATABASE paymybuddy;`
2. Import the `schema.sql` file (in the main/resources folder) to create the tables
3. Import the `datas.sql` in your database and try the application directly.


## Externalized MySQL configuration
For security purposes, the database credentials are externalized from the project. Therefore, you have to create a `jdbc.properties` file on your computer.
<br> When the file is created you must edit `application.properties` property `spring.config.import` and set your own path.
<br> The application should start correctly if everything is set up properly.

## Application.properties
You can modify the application by editing the `application.properties` file in the main/resources folder.
1. For example : spring.datasource.url=jdbc:Your JDBC://localhost:Your Port/8080/Your DATABASE name 

## Starting the application
If the database was properly setup, the application should start. <br>
The url is [http://localhost:8080](http://localhost:8080)

## Demo of the application
**Import the `datas.sql` file (in the main/resources folder) to import demonstration datas**
There are 5 users, with pre-filled fields. The main account is the number 1, from there you will be able to see transactions, bank account and connections
The four otheres have the same qualities. However, use the first one (HamzaBenalia) and try to test the functionalities.
1. (1,'Hamza','Benalia','hamzabenalia93@gmail.com', password: 1234'), connected to All buddies
2. (2,'eric ','eric','eric@gmail.com',password : 1234'), connected to no buddies.
3. 3,'sara','hms','sara@gmail.com',password: 1234), connected to no buddies.
4. (4,'toto','toto','toto@gmail.com',19.60, password :toto ) connecetd to buddy 1 (Hamza, benalia).
5. (5,'tata','tata','tata@gmail.com',password : tata ) connected to no buddy.

## Recommendation of Use
As show before, please use the user (Hamza, benalia) to test All the functionalities of the application.
He has all the connections. he does the transactions, he receives a transaction etc...

Thank you ℹ️
