
Steam Data Harvesting
=================

## Requirements server side
- maven - https://maven.apache.org/download.cgi
- tomcat server 7 or higher configured to run on port 8080 - http://tomcat.apache.org/download-80.cgi

## Requirements client side
- nodejs - https://nodejs.org/
- IMPORTANT! restart IDE after nodejs was installed (adds a path variable)

## Build commands
- SteamDataHarvestingAngularJS -> scripts -> installRequirements
- SteamDataHarvestingAngularJS -> scripts -> installBowerDependencies
- right click on SteamDataHarvesting project -> run as -> maven build
- goals: "clean install tomcat:redeploy" or "clean install tomcat:redeploy -DskipTests=true"
 
## Tomcat configuration
- insert the following two lines into the tomcat-users.xml file
  - \<role rolename="manager-gui"/\>
  - \<user username="admin" password="admin" roles="manager-gui,manager-script"/\>

## Eclipse plugins (luna)
- Eclipse JST Server Adapters (Tomcat integration for Eclipse)
- Maven Integration for Eclipse (Luna)
- AngularJS Eclipse

## Eclipse configuration
- import the whole project as an existing maven project
- right click on SteamDataHarvestingAngularJs -> Configure -> Convert to AngularJs Project

## Configure PostgreSQL database
- create a new database (e.g. name = steamDataHarvesting)
- open "\WebServices\src\main\resources\database.properties"
- change the database url (name), user and password

## Configure properties after deployment
- files can be found under: "\webapps\SteamDataHarvestingWebServices\WEB-INF\classes\"
- config.properties - contains general properties such as the cron expression, timeouts, number of reviews collected, urls, ...
- log4j2.xml - contains the configuration for the logger (it is possible to change the log level and logging style)
- database.properties - contains all information about database type, name, user and password
- after changing the configuration the server needs to be restarted!
