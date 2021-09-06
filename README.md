## Publishing to central
```
export JDK_JAVA_OPTIONS='--illegal-access=warn'
mvn release:clean release:prepare
mvn release:perform
```
