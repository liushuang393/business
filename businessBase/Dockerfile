# 使用基础的Java 11镜像
FROM tomcat:9-jdk11-openjdk

#RUN apk add --update tomcat10

# 追加JVM参数到现有的CATALINA_OPTS
ENV CATALINA_OPTS="$CATALINA_OPTS -Xmx1024m -Xms256m -Xss2m"

# 设置工作目录
WORKDIR /usr/local/tomcat/webapps/

# 复制应用程序的JAR文件到镜像中
COPY target/businessBase_1.0.0.RELEASE.war app.war

# 暴露应用程序的端口
EXPOSE 8080

# 指定容器启动时运行的命令
# CMD ["java", "-jar", "app.war"]
CMD ["catalina.sh", "run"]
