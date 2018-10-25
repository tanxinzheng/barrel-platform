FROM java:alpine

# MAINTAINER Ryuu KK "ryuu_kk@163.com"

# 环境变量
ENV WORK_PATH /home/project/dockerdemo
ENV APP_NAME boot-admin-1.0.0-SNAPSHOT.jar
ENV APP_VERSION 1.0.0-SNAPSHOT

EXPOSE 8000

#USER
#USER user:group

#VOLUME
VOLUME ["/home/project", "/tmp/data"]

#ADD

#COPY
COPY $APP_NAME $WORK_PATH/

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD

# WORKDIR
WORKDIR $WORK_PATH

# ENTRYPOINT
ENTRYPOINT ["java"]

# CMD
CMD ["-jar", "boot-admin-1.0.0-SNAPSHOT.jar"]