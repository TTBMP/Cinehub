mysql --version
mysql -u admin --password="admin" < ./data/local/src/main/resources/cinemadb.sql
mysql -e 'call popola();' cinemadb
