FROM tanxz/jdk1.8:latest

MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 环境变量
ENV DEPLOY_PATH=/var/deploy/barrel
ENV LOG_PATH=/var/deploy/barrel/logs
ENV APP_PORT=9008
ENV APP_JAR_NAME=barrel-system
ENV APP_DEPLOY_FILE=barrel-system.zip
ENV APP_JAR_FILE=$DEPLOY_PATH/apps/barrel-system.jar
ENV APP_JAR_CONFIG=$DEPLOY_PATH/apps/config
ENV APP_JAR_LIB=$DEPLOY_PATH/apps/lib
ENV NACOS_CONFIG_NAMESPACE=638b14b0-f81f-44c7-a9c3-f8f4058c8e78

WORKDIR $DEPLOY_PATH

ADD $APP_DEPLOY_FILE $DEPLOY_PATH/$APP_DEPLOY_FILE
RUN unzip $APP_DEPLOY_FILE
RUN mv $APP_JAR_NAME apps

ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=fss -Dloader.path=${APP_JAR_LIB},${APP_JAR_CONFIG} -Dspring.cloud.nacos.config.namespace=${NACOS_CONFIG_NAMESPACE} -Dspring.cloud.nacos.discovery.namespace=${NACOS_CONFIG_NAMESPACE}","/var/deploy/barrel/apps/barrel-system.jar"]

# 端口
EXPOSE $APP_PORT