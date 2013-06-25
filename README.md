yast-java-lib-user
==================

Java library for accessing Yast's user API http://www.yast.com/wiki/index.php/API


Add this to your pom.xml
 
```xml
  <repositories>
    <repository>
        <id>yast-lib-user-mvn-repo</id>
        <url>https://raw.github.com/YastTimeTracker/yast-java-lib-user/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>com.yast.lib</groupId>
      <artifactId>yast-lib-user</artifactId>
      <version>1.0.0</version>
    </dependency>
  </dependencies>
```