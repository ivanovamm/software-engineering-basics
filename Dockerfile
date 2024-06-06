FROM quay.io/wildfly/wildfly
ADD target/lab2_4-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
