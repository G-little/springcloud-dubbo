FROM anapsix/alpine-java:8u202b08_jdk
ARG JAR_FILE
ADD target/${JAR_FILE} app.jar
RUN apk add tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone && \
    apk del tzdata

ENV LANG="en_US.UTF-8"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=Asia/Shanghai","-jar","/app.jar"]
EXPOSE 20880