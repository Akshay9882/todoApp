Version of software used in project:  

   1.JDK 1.8 update 241  
   2.Netbeans IDE 11.2  
   3.Glassfish 5.0.1   
   4.JAVA EE7(Pre-installed with Glassfish)  
   5.mysql-connector-java-8.0.19.jar  

**=======Configuration for Web Application server==============**     
- For Setting UP JNDI resources and JDNI Connection Pool:   
- Copy the properties in file Glassfish_domain_Config.xml to your Glassfish server domain.xml file located at your glassfish home config path Example:H:\Glassfish\glassfish5.0.1\glassfish\domains\domain1\config  
- After copying check the properties as per your system  configuration and modify if needed.It includes details for connecting to MySQL DB

**=======Configuration for DB Driver===========================**   
- Copy mysql-connector-java-8.0.19.jar file in your glassfish home lib location
Example:H:\Glassfish\glassfish5\glassfish\lib

**=======Configuration for running in IDE======================**   
- Create java project in Netbeans IDE and copy the src folder in your project directory. 
- Import the libs from Lib folder in your project.  
- Open the project in Netbeans IDE and then Run it.

 
