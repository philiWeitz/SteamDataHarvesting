
export TOMCAT=apache-tomcat-8.0.21
export SERVER_HOME=/opt/tomcat


### kill running tomcat process
ps -ef | grep tomcat | awk '{print $2}' | xargs sudo kill -9


### remove old port forwarding in case it was already done
sudo /sbin/iptables -t nat -D PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
### add new port forwarding
sudo /sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080


### install tomcat server
cd $SERVER_HOME
sudo rm -rf apache-tomcat-*
cp common/$TOMCAT.zip .
unzip $TOMCAT.zip

### make all scripts executable
chmod +x $TOMCAT/bin/*.sh


####### get the project
sudo rm -rf SteamDataHarvesting
git clone https://github.com/philiWeitz/SteamDataHarvesting.git

cd SteamDataHarvesting/AngularJS
scripts/installRequirements.sh

cd /opt/tomcat
cp common/persistence.xml SteamDataHarvesting/Datamodel/src/main/resources/META-INF/


### start tomcat and deploy
$TOMCAT/bin/startup.sh

cd SteamDataHarvesting
mvn clean install -DskipTests=true
mvn tomcat:redeploy -DskipTests=true


### make the project available as root
cd ../$TOMCAT/webapps/
rm -rf ROOT/*
cp -rf SteamDataHarvestingAngularJS/*  ROOT/
