# roox
This app is an implementation one of the test tasks that is have.

Date of implementation: Feb 2016

Description:
- [Russian](Russian.txt)
- [English](English.txt)

What you can find in this app:
+  Spiring boot
+  basic authenticatino with Spring security
+  JPA (hibernate) + Spring Data
+  in-memeory databae H2 is used
+  RESTful application

What you will not find in this app (yet):
+  unit test was broken by the last major changes. Still under construction. (Coming really soon)
+  there is no proper logging yet
+  there is no UI... at all ... just REST API with JSON output

How to run:
* run the `java -jar build/RooxApplication.jar` from the root of the repository
* * (Optional) build project using maven command `mvn clean install`. Then you need to run the `jar` from forder `target`
* open <a href="localhost:8080">localhost</a> page
* follow the instrustions on the home page

All pre-defined users has names `UserX` where `X` is number from 0 to 9. All users has the same password `pass`
User6-9 is locked by default. you should not be ablw to login using this users.
