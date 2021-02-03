mysql --version
mysql -e "CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';"
mysql -e "GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';"
mysql -e "FLUSH PRIVILEGES;"
mysql -u admin --password="admin" < ./data/local/src/main/resources/cinemadb.sql
mysql -e 'call popola();' cinemadb
