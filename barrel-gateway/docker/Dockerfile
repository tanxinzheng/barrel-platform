FROM tanxz/jdk1.8:latest

MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 环境变量
ENV DEPLOY_PATH=/var/deploy/barrel
ENV LOG_PATH=/var/deploy/barrel/logs
ENV APP_PORT=9999
ENV APP_JAR_NAME=barrel-gateway
ENV APP_DEPLOY_FILE=barrel-gateway.tar.gz
ENV APP_JAR_FILE=$DEPLOY_PATH/apps/barrel-gateway.jar
ENV APP_JAR_CONFIG=$DEPLOY_PATH/apps/config
ENV APP_JAR_LIB=$DEPLOY_PATH/apps/lib

WORKDIR $DEPLOY_PATH

ADD $APP_DEPLOY_FILE $DEPLOY_PATH
#RUN unzip $APP_DEPLOY_FILE
RUN mv $APP_JAR_NAME apps

# 端口
EXPOSE $APP_PORT

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS} -Dloader.path=${APP_JAR_LIB},${APP_JAR_CONFIG}","-jar","/var/deploy/barrel/apps/barrel-gateway.jar"]
