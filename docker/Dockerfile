FROM tomcat:9.0.59-jre11-temurin
MAINTAINER delki8 <delki8@gmail.com>
COPY ./javacrud-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
COPY ./tomcat-lib/*.jar /usr/local/tomcat/lib/
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]