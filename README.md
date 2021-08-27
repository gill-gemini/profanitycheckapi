# profanitycheck

Profanity Check API helps to scan and censor txt documents for obsene words. There is no authentication currently , so API accessible , when you hit the end point. The app is currently deployed to Heroku. 
https://scancontent.herokuapp.com/

The app on heroku is running on free dynos. So it will give time reachout error for first hit. After the server is awake , it will serve your requests.

There are two endpoints for the API.

/censorcontent

This endpoint returns the censor content of the file in the response.

/scancontent
This endpoint returns True , if there are obsene words in the file and an array of obsene words which were found in the file.


To run it locally from your terminal /CMD.

1) cd <directory of the project>
2)  mvn clean install
3)  java -jar target/demo-0.0.1-SNAPSHOT.jar
  For third step make sure , you have the jar file under the target folder or change it accordingly.
 


