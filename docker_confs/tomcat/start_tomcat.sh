#!bin/bash
ln -s /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps/
cp /tmp/tomcat-manager-context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
catalina.sh run