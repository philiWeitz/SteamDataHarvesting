### kill running tomcat process
ps -ef | grep tomcat | awk '{print $2}' | xargs sudo kill -9

### remove old port forwarding in case it was already done
sudo /sbin/iptables -t nat -D PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080
### add new port forwarding (80 to 8080)
sudo /sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

### install tomcat server
cd /opt/tomcat
sudo rm -rf apache-tomcat-*
cp common/apache-tomcat-8.0.21.zip .
unzip apache-tomcat-8.0.21.zip

chmod +x apache-tomcat-8.0.21/bin/*.sh
cp common/tomcat-users.xml apache-tomcat-8.0.21/conf/


####### get the project
sudo rm -rf SteamDataHarvesting
git clone https://github.com/philiWeitz/SteamDataHarvesting.git

cd SteamDataHarvesting/AngularJS
scripts/installRequirements.sh

cd /opt/tomcat
cp common/persistence.xml SteamDataHarvesting/Datamodel/src/main/resources/META-INF/


#### start tomcat and deploy
apache-tomcat-8.0.21/bin/startup.sh

cd SteamDataHarvesting
mvn clean install -DskipTests=true
mvn tomcat:redeploy -DskipTests=true


