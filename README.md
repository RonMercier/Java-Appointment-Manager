# Java Appointment Manager Application

### Entity Relationship Diagram

![ScreenShot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/AppointmentManagerERD.png)

### Screenshots of application
#### Login screen 
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/Login.png)

#### Main screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/MainScreen.png)

#### Add customer screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/AddCustomer.png)

#### Modify customer screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ModifyCustomer.png)

#### Appointment screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ApptScreen.png)

#### Add appointment screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/AddApptScreen.png)

#### Modify appointment screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ModifyApptScreen.png)

#### Contact screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ContactScreen.png)

#### Reports screen
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ReportsMain.png)

#### Appointment by type report
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ApptByType.png)

#### Contact schedule report
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ContactSchedule.png)

#### Appointment by customer report
![Screenshot](https://github.com/RonMercier/Java-Appointment-Manager/blob/master/ApptManagerPics/ApptByCustomer.png)



### Application details

IDE: Intellij IDE 2021.2<br>
Java version: Java SE 11.0.9<br>
JavaFX: JavaFX-SDK-11.0.2<br>
mysql-connector-java-8.0.22<br>

### Description

This is a Java application for the C195 Software II class at WGU.
It connects to a SQL database and allows the user review, add, modify and delete appointments and save them in the database.
It also lets the user manage customers data that are going to be linked to the appointments.

I created a report section that displays appointments by user, all customer appointments and contact schedule.  An active
internet connection is required to use the application because it connects to an online database.

### Login information

User: test<br>
Password: test

User: admin<br>
Password: admin

### How to run the application

  Start by downloading JavaFX 11.0.2. (No longer packaged with JDK11+) - https://gluonhq.com/products/javafx/

  Once this has downloaded, add the lib folder to your project by following the steps below
  
    1.  File -> Project Structure -> libraries
  
    2.  Add the lib folder of your downloaded JavaFX library.
 
  PATH variable for the lib folder needs to be added to IntelliJ. This will make the step of adding VM options easier.<br>
  
  Follow the steps below
  
    3.  File -> Settings -> Path Variables (Under Appearance & Behavior sub-menu)
    
    4.  Create a new variable named 'PATH_TO_FX' and point it to the same lib folder from the last step.

  Finally, We need to add VM options, so the application uses the JavaFX library.<br>

  Go to:<br>

    5.  Run -> Edit Configurations and in the top-right 'Modify Options' dropdown, select 'Add VM Options'.
    
  This will add a new field in the Configuration Panel for VM Options.
  
  Paste this into the field:<br>

      --module-path ${PATH_TO_FX} --add-modules javafx.fxml,javafx.controls,javafx.graphics<br>

  ### MySQL
  
  MySQL can be added by using the Maven package manager in Intellij<br>

     1. File -> Project Structure -> libraries (Under Project Structure)
     
     2. Select add(+) and choose Maven from the options and search for MySQL.

