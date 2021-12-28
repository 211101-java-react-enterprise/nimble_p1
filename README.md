# **Nimble**: **A custom Object Relational Mapping framework**

  ## What is ORM and why we use it
  
  Modern day applications usually separate out the data persistence layer logic from pure functional service layer logic. A Data Access Object(DAO) instance will be used to handle the communication with database for a specific object model. It's often to see DAOs keep repeating some lines of code just to instantiate a connection with database. It can be very lengthy, and DAOs can have very complicate relationships if you have multiple object model interact with each other in the service layer.
  
  To abstract away the boiler plate logic and keep the code DRY, Object Relational Mapping(ORM) come to the place. ORM can easily map an object model with a table in a database. Data manipulation process can be as simple as a method call like object.save(). The mapping process can be done by sets of programming process, XML mapping, or more popular way with Annotations.
  
  ## About Nimble

  Nimble utilizes Java annotation feature to map Object with a table in database.
  
  ###### Features
  - **.creating** takes an object; insert it to database
  - **.reading**
    - takes an object class; select and return the whole table   
    - takes an object and a primary key value; select and return if entry exist
    - takes an object class, a field name, and field value; select and return if entry exist
  - **.delete** takes an object class and primary key value; delete entry from table
  - **.update** takes an ojbect class, a primary key value, a field name, and a field value; update entry information

  ###### Using Nimble
  - Clone Nimble to local
  - Update db.properties file in resource folder to your database credentials
  - In Nimble IDE terminal run 
  
  ## Contributors
  - Michael Chau
  - Qi Zhang
