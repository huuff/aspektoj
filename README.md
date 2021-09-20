[![CircleCI](https://circleci.com/gh/huuff/aspektoj/tree/master.svg?style=svg)](https://circleci.com/gh/huuff/aspektoj/tree/master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/xyz.haff/aspektoj/badge.svg?style=plastic)](https://search.maven.org/artifact/xyz.haff/aspektoj/0.0.8/jar)

## Publishing to central
```
export JDK_JAVA_OPTIONS='--illegal-access=warn'
mvn release:clean release:prepare
mvn release:perform
```
