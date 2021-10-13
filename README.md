[![CircleCI](https://circleci.com/gh/huuff/aspektoj/tree/master.svg?style=svg)](https://circleci.com/gh/huuff/aspektoj/tree/master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/xyz.haff/aspektoj/badge.svg?style=plastic)](https://search.maven.org/artifact/xyz.haff/aspektoj/0.0.8/jar)

# Aspektoj
This was a toy project to learn AspectJ, most of these aspects aren't really suitable for any kind of production usage (Although I've used some of them for debugging).


## Issues beware
Support of AOP is almost nonexistent, IDEs and the own Aspectj compiler aren't very good at guiding beginners to solve issues. Particularly, be very careful when optimizing imports, as idea will remove annotations imports from aspects that deems unused, but that are actually needed. The Aspectj compiler won't complain either, but your aspects won't ever get weaved.

Also, using autocompletion will completely fuck an annotation file at some points.
