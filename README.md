# **Nimble**: **A custom Object Relational Mapping framework**

  ## What is ORM and why we use it
  
  Modern day applications usually separate out the data persistence layer logic from pure functional service layer logic. A Data Access Object(DAO) instance will be used to handle the communication with database for a specific object model. It's often to see DAOs keep repeating some lines of code just to instantiate a connection with database. It can be very lengthy, and DAOs can have very complicate relationships if you have multiple object model interact with each other in the service layer.
  
  To abstract away the boiler plate logic and keep the code DRY, Object Relational Mapping(ORM) come to the place. ORM can easily map an object model with a table in a database. Data manipulation process can be as simple as a method call like save(). The mapping process can be done by sets of programming process, XML mapping, or more popular way with Annotations.
  
  ## About Nimble
