FROM java:alpine

MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 环境变量
ENV DEPLOY_PATH=/var/deploy/barrel
ENV LOG_PATH=/var/deploy/barrel/logs
ENV APP_PORT=9999

WORKDIR $DEPLOY_PATH

ADD ./target/blade-gateway.jar $DEPLOY_PATH/apps/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

# 端口
EXPOSE $APP_PORT