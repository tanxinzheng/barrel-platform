FROM java:alpine

# MAINTAINER tanxinzheng "tanxinzheng@139.com"

# 环境变量
ENV DEPLOY_PATH=/var/deploy
ENV APP_NAME=boot-admin-1.0.0-SNAPSHOT.jar
ENV APP_PORT=8000

# 端口
EXPOSE $APP_PORT

# 添加应用包到部署目录
ADD $APP_NAME $DEPLOY_PATH

# ENTRYPOINT
ENTRYPOINT ["java"]

# CMD
CMD ["-Dserver.port=8000", "-jar", "boot-admin-1.0.0-SNAPSHOT.jar"]