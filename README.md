ms applicant's application

online app on [Heroku](https://msaa.herokuapp.com/)

install & start (localhost:8080):

```
git clone https://github.com/kojotak/msaa.git msaa
cd msaa
mvn clean package 
java -jar target/dependency/webapp-runner.jar target/*.war
```