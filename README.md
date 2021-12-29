# **Nimble**: **A custom Object Relational Mapping framework**

  ## What is ORM and why we use it
  
  Modern day applications usually separate out the data persistence layer logic from pure functional service layer logic. A Data Access Object(DAO) instance will be used to handle the communication with database for a specific object model. It's often to see DAOs keep repeating some lines of code just to instantiate a connection with database. It can be very lengthy, and DAOs can have very complicate relationships if you have multiple object model interact with each other in the service layer.
  
  To abstract away the boiler plate logic and keep the code DRY, Object Relational Mapping(ORM) come to the place. ORM can easily map an object model with a table in a database. Data manipulation process can be as simple as a method call like ormdriver.save(object). The mapping process can be done by sets of programming process, XML mapping, or more popular way with Annotations.
  
  ## About Nimble

  Nimble utilizes Java annotation feature to map Object with a table in database. Nimble support PostgresSQL database connection. A showcase web application named [tixter](https://github.com/211101-java-react-enterprise/tixter_p1) is available. You may also download Nimble for your own project.
  
  ## Technologies Used
  1. Java-8
  2. Maven-4
  3. PostgresJDBC-42.2.12
  
  ## Features
  **Allows you to do a method call in DAO layer logic**
  - **.creating** takes an object; insert it to database
  - **.reading**
    - takes an object class; select and return the whole table   
    - takes an object and a primary key value; select and return if entry exist
    - takes an object class, a field name, and field value; select and return if entry exist
  - **.delete** takes an object class and primary key value; delete entry from table
  - **.update** takes an ojbect class, a primary key value, a field name, and a field value; update entry information

  ###### TODO-List:
  - Support mulitiple database venders like MySQL
  - Auto generate tables in database if desired table not exists already
  - Support more queries like join or union

  ## Using Nimble
  ###### Getting Started
  - Clone Nimble to local 
      > git clone https://github.com/211101-java-react-enterprise/nimble_p1
  - Update db.properties file in resource folder to your database credentials
  - In Nimble IDE terminal run > mvn install
  - Open your project and include nimble artifact information as a dependency in your pom file
  - Rebuild your project
 ###### Using Nimble
  - In your data or service layer logic instantiate orm driver
    > OrmServiceDriver orm = new OrmServiceDriver();
  - Use method call like orm.delete() to manipulate data, see required parameter in **Features**
  
  ## Contributors
  - Michael Chau
  - Qi Zhang
