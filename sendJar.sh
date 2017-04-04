#!/bin/bash

echo "building new jar files"
./mvnw clean package
echo "scp target/AlumniLinkBackend-0.1.0.jar hw:AlumniLink/jar"
scp target/AlumniLinkBackend-0.1.0.jar hw:AlumniLink/jar

# to start backend with logging
#java -jar AlumniLinkBackend-0.1.0.jar --logging.file=/home/zhang464/AlumniLink/jar/spring-boot-app.log

# to generate JoCoCo report for maven
# 1.change pom.xml
# 2.run ./mvnw test
# 3.run ./mvnw jococo:report
# 4.report located in target/site/jacoco/index.html

# to export the collection from mongo, will NOT preserve _id
#mongoexport -d test -c friends --pretty -o friends.json
# to import json into collections
#mongo --db test -c frineds --file friends.json

# TO backup and restore the mongo database
#mongodump --db test -c friends --out ./data/backup/
#mongorestore ./data/backup