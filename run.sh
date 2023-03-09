#!/bin/sh
buildVersion=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
artifactId=$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)

./mvnw clean install

java -jar ./target/$artifactId-$buildVersion.jar