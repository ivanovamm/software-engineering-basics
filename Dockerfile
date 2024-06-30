FROM quay.io/wildfly/wildfly
ADD target/opi_lab3-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/
