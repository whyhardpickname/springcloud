# Docker image for springboot application
# VERSION 0.0.1
# Author: bolingcavalry

### 基础镜像，使用alpine操作系统，openjkd使用8u201
FROM openjdk:8u201-jdk-alpine3.9

#作者
MAINTAINER BolingCavalry <zq2599@gmail.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

#应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD target/cloud-provider-payment8004-1.0-SNAPSHOT.jar app.jar

#启动容器时的进程
ENTRYPOINT ["java","-jar","/app.jar"]

#暴露端口
EXPOSE 8004