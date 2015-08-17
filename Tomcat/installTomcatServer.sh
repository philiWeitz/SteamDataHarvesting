
export TOMCAT=apache-tomcat-8.0.21
export SERVER_HOME=/opt/tomcat



####### expected file structure #######
# under SERVER_HOME:		      #
#   - /common/tomcat-users.xml        #
#   - /common/tomcat-users.xml        #
#   - /common/<tomcat zip-file>       #
#######################################



####### kill running tomcat process
ps -ef | grep tomcat | awk '{print $2}' | xargs sudo kill -9


####### remove old port forwarding in case it was already done
sudo /sbin/iptables -t nat -D PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080


####### remove old tomcat directory and unzip new tomcat server from common directory
cd $SERVER_HOME
sudo rm -rf apache-tomcat-*
cp common/$TOMCAT.zip .
unzip $TOMCAT.zip


####### make all scripts in the tomcat bin directory executable
chmod +x $TOMCAT/bin/*.sh


####### add admin user to deploy war file from command line
cp $TOMCAT/conf/tomcat-users.xml $TOMCAT/conf/tomcat-users.xml.keep 
cp common/tomcat-users.xml $TOMCAT/conf/


####### remove old source code and get a clean version from github
sudo rm -rf SteamDataHarvesting
git clone https://github.com/philiWeitz/SteamDataHarvesting.git


####### install all required javascript libraries
cd SteamDataHarvesting/AngularJS
scripts/installRequirements.sh


####### copies the production database configuration
cd $SERVER_HOME
cp -rf common/database.properties SteamDataHarvesting/WebServices/src/main/resources/


####### start tomcat and deploy
$TOMCAT/bin/startup.sh


####### compile and deploy the source code
cd SteamDataHarvesting
mvn clean install -DskipTests=true
mvn tomcat:redeploy -DskipTests=true


####### make the angularjs project available as root application
cd $SERVER_HOME/$TOMCAT/webapps/
rm -rf ROOT/*
cp -rf SteamDataHarvestingAngularJS/*  ROOT/


####### kill running tomcat process
ps -ef | grep tomcat | awk '{print $2}' | xargs sudo kill -9


####### remove admin user
cd $SERVER_HOME
rm $TOMCAT/conf/tomcat-users.xml
mv $TOMCAT/conf/tomcat-users.xml.keep $TOMCAT/conf/tomcat-users.xml


####### add new port forwarding
sudo /sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080


####### start tomcat
$TOMCAT/bin/startup.sh

