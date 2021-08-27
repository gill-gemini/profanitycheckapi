# profanitycheck

Profanity Check API helps to scan and censor txt documents for obsene words(.txt , .csv extensions). There is no authentication currently , so API is accessible , when you hit the end point. The app is currently deployed to Heroku. 

* **URL**

  https://scancontent.herokuapp.com/

  OR

  localhost:8080

  The app on heroku is running on free dynos. So it will give time reachout error for first hit. After the server is awake , it will serve your requests.

* **METHOD && ENDPOINTS**

  `POST`

  https://scancontent.herokuapp.com `/scancontent`
  * **Data Params**
    * **Body** Binary of the txt file
  
  
  This endpoint returns a boolean value and a List. If there are obsene words in the file and an array of obsene words which were found in the file.
  
  * **Success Response:**
    * **Code:** 200 <br />
    **Content:** `{ "result" : true , ["obscene","obscene","obscene","obscene"] }`

  https://scancontent.herokuapp.com `/censorcontent`
    * **Data Params**
    * **Body** Binary of the txt file

    This endpoint returns the censor content of the file in the response.
 
    * **Success Response:**
      * **Code:** 200 <br />
      **Content:** `{ How are you **** }`



To run it locally from your terminal /CMD.

1) cd <directory of the project>
2)  mvn clean install
3)  java -jar target/demo-0.0.1-SNAPSHOT.jar
  For third step make sure , you have the jar file under the target folder or change it accordingly.
  
To run it through the editor , clone the repo , import to your editor . I personally used IntelliJ to run and code the app. 
  
  The following snapshots are from the postman , while the testing the localhost and Heorku app.
  
  
  
  <img width="943" alt="Screenshot 2021-08-27 at 16 53 56" src="https://user-images.githubusercontent.com/1822240/131139846-70be8790-e929-49ba-a233-5ca308c251c9.png">
<img width="851" alt="Screenshot 2021-08-27 at 17 03 53" src="https://user-images.githubusercontent.com/1822240/131139857-d906787c-6f80-47b2-9e6a-614f3a673367.png">

 


