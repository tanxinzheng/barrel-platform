#!/bin/sh

java -jar $APP_JAR_FILE $JAVA_OPTS -Dloader.path=${APP_JAR_LIB},${APP_JAR_CONFIG}
