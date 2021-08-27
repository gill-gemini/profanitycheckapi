# profanitycheck

Profanity Check API helps to scan and censor txt documents for obsene words(.txt , .csv extensions). There is no authentication currently , so API is accessible , when you hit the end point. The app is currently deployed to Heroku. The API is built with Java and Spring framework. Integrations tests have been written , which test all the endpoints and the scan logic. 

The project also contains a service for fetching large contentversion from the Salesforce Environment. This fetching service was written for fetching large files (>12MB) from the SOAP API. This particular fetching service is work in progess and not complete.

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

  * **SAMPLE CALL**
  
    curl --request POST --data-binary watchyourtonguefuck  https://scancontent.herokuapp.com/censorcontent
    
    curl --request POST --data-binary watchyourtonguefuck  https://scancontent.herokuapp.com/scancontent
    
    A sample call from POSTMAN
    
   <img width="851" alt="Screenshot 2021-08-27 at 17 03 53" src="https://user-images.githubusercontent.com/1822240/131172774-a19b477c-f188-4d94-bdac-5eb176dfe6c3.png">



  * **BUILD LOCAL INSTRUCTIONS**
    
    To run it locally from your terminal /CMD.

      1) cd Directory of the project
  
      2)  mvn clean install
  
      3)  java -jar target/demo-0.0.1-SNAPSHOT.jar
  
        For third step make sure , you have the jar file under the target folder or change it accordingly.
  
      To run it through the editor , clone the repo , import to your editor . I personally used IntelliJ to run and code the app. 
  
  * **DEPLOY INSTRUCTIONS HEROKU**
      Follow the instructions on the page to deploy to Heroku.
      https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
  
  
  
 

 


