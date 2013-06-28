Yast Java User API Library
=================

Java library for accessing Yast's user API http://www.yast.com/wiki/index.php/API

Example project: https://github.com/YastTimeTracker/yast-java-lib-user-example


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


Direct link to the latest jar: https://github.com/YastTimeTracker/yast-java-lib-user/blob/mvn-repo/com/yast/lib/yast-lib-user/1.0.0/yast-lib-user-1.0.0.jar
There's only one dependency: org.apache.httpcomponents.httpclient (download: 
http://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.3-beta2/httpclient-4.3-beta2.jar )