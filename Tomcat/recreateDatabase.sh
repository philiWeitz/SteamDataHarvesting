export DATABASE_NAME=steamdataharvesting
export DUMP_FILE_PATH=/opt/tomcat/common/steamdataharvesting.dump

echo $DATABASE_NAME

sudo /etc/init.d/postgresql restart
sudo -u postgres psql -c "DROP DATABASE steamdataharvesting;"
sudo -u postgres psql -c "CREATE DATABASE steamdataharvesting;"

echo "Push SteamDataHarvesting.dump file to database (Y/N)"
read input

if [ "$input" == "Y" ]; then
  sudo -u postgres psql $DATABASE_NAME < $DUMP_FILE_PATH
fi
