wget http://repo.mysql.com/mysql-apt-config_0.8.15-1_all.deb
dpkg -i mysql-apt-config_0.8.15-1_all.deb
apt-get update -q
apt-get install -q -y --allow-unauthenticated -o Dpkg::Options::=--force-confnew mysql-server
systemctl restart mysql
mysql_upgrade
mysql --version
mysql -e "CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';"
mysql -e "FLUSH PRIVILEGES;"
mysql -u admin --password="admin" < ./data/local/src/main/resources/cinemadb.sql
mysql -e 'call popola();' cinemadb
