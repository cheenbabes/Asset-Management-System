## Asset Management System ##

This is a customizable implementation of a quartermaster or asset management system. Some uses may include:
* An outdoor outfitter checking out gear
* An internal corporate system for checking out laptops, projectors, etc
* A quartermaster system for an organization such as Boy Scouts, etc

**Features**
* Full configuration of different assets and grouping by asset type
* Categories of assets
* A full and customizable security system with role based authorization
* Picture uploads
* Pre loaded usage reports and statistics on users and assets
* Inventory management

**What this system doesn't do**
* Any type of billing
* Reservations of assets at any point in the future or past, i.e. only immediate checkout


### Setup ###
====================

**Pre-reqs**
* Java 7
* MySQL database
* Tomcat 7


#### Installation ####
* **Clone the repository** by 
```
git clone https://github.com/cheenbabes/Asset-Management-System.git
```
* **Create your databases**. You will need two databases, one for the test suite, and one for production. You can find scripts for both the main and test databases in DatabaseDocs/scripts. The easiest is to run `quartermaster_dev_data_script.sql` and `quartermaster_test_data_script.sql`. This will populate your database with data.
* **Examine the config files**. Open up `spring-persistence.xml` in `src/main/resources` and check the database config. Make sure the password and username for your database is correct.

#### Test and RUN ####
* Use your IDE or cd into the directory and run `mvn clean install`
* If you have Tomcat configured correctly, you should be able to visit the page at localhost:8000
