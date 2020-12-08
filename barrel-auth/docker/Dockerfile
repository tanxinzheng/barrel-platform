FROM tanxz/jdk1.8:latest

MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 设置应用环境变量
ENV DEPLOY_PATH=/var/deploy
ENV APP_PATH=/var/deploy/barrel-auth
ENV APP_PORT=9901
ENV APP_DEPLOY_FILE=barrel-auth.tar.gz
ENV APP_JAR_FILE=$APP_PATH/barrel-auth.jar
ENV APP_JAR_CONFIG=$APP_PATH/config
ENV APP_JAR_LIB=$APP_PATH/lib
ENV JAVA_OPTS=$JAVA_OPTS

WORKDIR $APP_PATH

ADD $APP_DEPLOY_FILE $DEPLOY_PATH

COPY entrypoint.sh /var/run/
RUN chmod -R 755 /var/run/entrypoint.sh

# 端口
EXPOSE $APP_PORT

ENTRYPOINT ["/var/run/entrypoint.sh"]
