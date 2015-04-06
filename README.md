
Steam Data Harvesting
=================

## Requirements server side
- maven - https://maven.apache.org/download.cgi
- tomcat server 7+ configured to run on port 8080 - http://tomcat.apache.org/download-80.cgi

## Requirements client side
- nodejs - https://nodejs.org/

## Build commands
- building maven projects + deployment - mv clean install tomcat:redeploy 
- install nodejs dependencies - npm install
- install bower dependencies - bower install
 
## Tomcat configuration
- tomcat-users.xml
  - \<role rolename="manager-gui"/\>
  - \<user username="admin" password="admin" roles="manager-gui,manager-script"/\>

## Eclipse plugins (luna)
- Eclipse JST Server Adapters
- Maven Integration for Eclipse (Luna)
- AngularJS Eclipse

## Eclipse configuration
- Import the whole project as an existing maven project
- Right click on SteamDataHarvestingAngularJs -> Configure -> Convert to AngularJs Project
