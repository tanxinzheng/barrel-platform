#!/usr/bin/env bash
export RUN_AS_USER=admin
export BASH_ENV=/etc/profile
echo $PATH
cd ../../
mvn clean install -f barrel-framework/pom.xml
mvn clean install -f barrel-platform/pom.xml

scp barrel-platform/barrel-auth/target/barrel-auth.tar.gz admin@dev.ecs:/app/deploy/barrel-auth.tar.gz
scp barrel-platform/barrel-gateway/target/barrel-gateway.tar.gz admin@dev.ecs:/app/deploy/barrel-gateway.tar.gz

