FROM tanxz/jdk1.8:latest

MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 环境变量
ENV DEPLOY_PATH=/var/deploy/barrel
ENV LOG_PATH=/var/deploy/barrel/logs
ENV APP_PORT=9901
ENV APP_JAR_NAME=barrel-admin
ENV APP_DEPLOY_FILE=barrel-admin.jar
ENV APP_JAR_FILE=$DEPLOY_PATH/apps/barrel-admin.jar
#ENV APP_JAR_CONFIG=$DEPLOY_PATH/apps/config
#ENV APP_JAR_LIB=$DEPLOY_PATH/apps/lib
#ENV NACOS_CONFIG_NAMESPACE=638b14b0-f81f-44c7-a9c3-f8f4058c8e78

WORKDIR $DEPLOY_PATH

ADD $APP_DEPLOY_FILE $APP_JAR_FILE

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/var/deploy/barrel/apps/barrel-admin.jar"]

# 端口
EXPOSE $APP_PORT