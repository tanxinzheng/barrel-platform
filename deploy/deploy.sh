#!/usr/bin/env bash
export RUN_AS_USER=admin
export BASH_ENV=/etc/profile
echo $PATH
rm -rf barrel-framework
rm -rf barrel-plateform
git clone -b 1.0.0 https://gitee.com/tanxinzheng/barrel-framework.git
git clone -b 1.0.0 https://gitee.com/tanxinzheng/barrel-platform.git

mvn install -f barrel-framework/pom.xml
mvn install -f barrel-plateform/pom.xml -Pstg

tar -zxvf barrel-plateform/barrel-system/target/barrel-system.tar.gz -C /app/deploy
tar -zxvf barrel-plateform/barrel-gateway/target/barrel-gateway.tar.gz -C /app/deploy
tar -zxvf barrel-plateform/barrel-service/barrel-module-system/target/barrel-system.tar.gz -C /app/deploy

echo "Restart Barrel-Auth Start...."
sudo systemctl restart barrel-system
echo "Restart Barrel-Auth End...."

echo "Restart Barrel-System Start...."
sudo systemctl restart barrel-system
echo "Restart Barrel-System End...."

echo "Restart Barrel-Gateway Start...."
sudo systemctl restart barrel-gateway
echo "Restart Barrel-Gateway End...."