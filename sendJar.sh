#!/bin/bash

echo "scp target/AlumniLinkBackend-0.1.0.jar hw:AlumniLink/jar"
scp target/AlumniLinkBackend-0.1.0.jar hw:AlumniLink/jar

# to generate JoCoCo report for maven
# 1.change pom.xml
# 2.run ./mvnw test
# 3.run ./mvnw jococo:report
# 4.report located in target/site/jacoco/index.html