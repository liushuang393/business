# Java 11 ベースの基本イメージを使用します
FROM tomcat:9.0.55-jdk11

# tomcat10 をインストールします
#RUN apk add --update tomcat10

# 既存の CATALINA_OPTS に JVM パラメータを追加します
ENV CATALINA_OPTS="$CATALINA_OPTS -Xmx1024m -Xms256m -Xss2m"

# 作業ディレクトリを設定します
WORKDIR /usr/local/tomcat/webapps/

# webapps.dist 内のすべてのファイルを webapps にコピーします
RUN cp -r $CATALINA_HOME/webapps.dist/* $CATALINA_HOME/webapps/

# tomcat-users.xml を conf ディレクトリにコピーします
COPY tomcat-users.xml $CATALINA_HOME/conf/

# context.xml を manager/META-INF ディレクトリにコピーします
COPY context.xml $CATALINA_HOME/webapps/manager/META-INF/

# アプリケーションの JAR ファイルをイメージにコピーします
COPY target/businessBase_1.0.0.RELEASE.war $CATALINA_HOME/webapps/businessBase.war
#COPY sample.war $CATALINA_HOME/webapps/

# アプリケーションのポート番号を公開します
EXPOSE 9091
# コンテナの起動時に実行するコマンドを指定します
# CMD ["java", "-jar", "app.war"]
CMD ["catalina.sh", "run"]