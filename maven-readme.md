# Mavenized OX (fork version 6.1.0)

In order to use openxava with maven functionalities, follow this steps:

# Install offline jars

These jars are not public and are hard to Mavenized:

**openxava-jdbc-adapters**

```
mvn install:install-file -Dfile=./OpenXava/web/WEB-INF/lib/ox-jdbc-adapters.jar -DgroupId=org.openxava -DartifactId=openxava-jdbc-adapters -Dversion=6.1.0 -Dpackaging=jar
```

**hibernate-core-5.3.9.Fixed.jar**

```
mvn install:install-file -Dfile=./Jars/hibernate-core-5.3.9.Fixed.jar -DgroupId=org.hibernate -DartifactId=hibernate-core -Dversion=5.3.9.Fixed -Dpackaging=jar
```

# Create openxava libraries as maven projects and install them

This ant task will create two new folder (OpenXavaMvn & AddonsMvn) which are a mavenized version of  OpenXava and Addons folders

```
ant
mvn clean install -f OpenXavaMvn/pom.xml
mvn clean install -f AddonsMvn/pom.xml
```
