#写在最前面：强烈建议先阅读官方教程
#选择构建用基础镜像（选择原则：在包含所用到的依赖前提下尽可能体积小）
FROM maven:3.6.0-jdk-8-slim as build

#指定构建工程中的工作目录
WORKDIR /app

# 将src目录下所有文件，拷贝到工作目录中src目录下
COPY src /app/src

# 将pom.xml文件，拷贝至工作目录中src目录
COPY pom.xml /app

# 执行代码编译命令
RUN mvn -f /app/pom.xml clean package

#选择运行时基础镜像
FROM alpine:3.13

ENV MYSQL_HOST 10.0.224.14
ENV MYSQL_USERNAME music
ENV MYSQL_PASSWORD zq969811.
ENV DATABASE_NAME music
#安装依赖包，如需其他依赖包，请到alpine依赖包管理下载
RUN  apk add --update --no-cache openjdk8-jre-base \
     && rm -f /var/cache/apk/*

#指定运行时的工作目录
WORKDIR /app

#将构建产物jar包拷贝到运行时目录中
COPY --from=build /app/target/music-0.0.1.jar .

# 暴露端口
EXPOSE 80

# 执行启动命令
CMD ["java", "-jar", "/app/music-0.0.1.jar"]