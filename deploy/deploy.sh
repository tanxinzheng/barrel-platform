#!/usr/bin/env bash
export RUN_AS_USER=admin
export BASH_ENV=/etc/profile
echo $PATH
rm -rf barrel-framework
rm -rf barrel-platform
git clone -b 1.0.0 https://gitee.com/tanxinzheng/barrel-framework.git
git clone -b 1.0.0 https://gitee.com/tanxinzheng/barrel-platform.git

mvn clean install -f barrel-framework/pom.xml
mvn clean install -f barrel-platform/pom.xml

tar -zxvf barrel-platform/barrel-auth/target/barrel-auth.tar.gz -C /app/deploy
tar -zxvf barrel-platform/barrel-gateway/target/barrel-gateway.tar.gz -C /app/deploy
tar -zxvf barrel-platform/barrel-service/barrel-module-system/target/barrel-system.tar.gz -C /app/deploy

echo "Restart Barrel-Auth Start...."
sudo systemctl restart barrel-system
echo "Restart Barrel-Auth End...."

echo "Restart Barrel-System Start...."
sudo systemctl restart barrel-system
echo "Restart Barrel-System End...."

echo "Restart Barrel-Gateway Start...."
sudo systemctl restart barrel-gateway
echo "Restart Barrel-Gateway End...."