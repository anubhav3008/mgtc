mvn clean install
~/Software/tomcat/apache-tomcat-8.5.32/bin/shutdown.sh 
sleep 5
rm -rf ~/Software/tomcat/apache-tomcat-8.5.32/webapps/*
cp target/mgtc-1.0.0.war ~/Software/tomcat/apache-tomcat-8.5.32/webapps/mgtc.war
~/Software/tomcat/apache-tomcat-8.5.32/bin/startup.sh
tail -100f ~/Software/tomcat/apache-tomcat-8.5.32/logs/catalina.out
