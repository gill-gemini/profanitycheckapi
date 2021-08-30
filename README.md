# profanitycheck

Profanity Check API helps to scan and censor txt documents for obsene words(.txt , .csv extensions). There is no authentication currently , so API is accessible , when you hit the end point. The app is currently deployed to Heroku. The API is built with Java and Spring framework. Integrations tests have been written , which test all the endpoints and the scan logic. The list of banned words is fetched from the google link mentioned below. Currently this link is hardcoded and will be moved to config file in the next phase.

https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv

The project also contains a service for fetching large contentversion from the Salesforce Environment. This fetching service was written for fetching large files (>12MB) from the SOAP API. This particular fetching service is work in progress and not complete.

* **URL**

  The app on heroku is running on free dynos. So it will give time reachout error for first hit. After the server is awake , it will serve your requests.
  
  https://scancontent.herokuapp.com/

  OR
  
  If running locally.

  localhost:8080

  

* **METHOD && ENDPOINTS**

  `POST` `/v1/scancontent`
  
  * **Data Params**
    * **Body** Binary of the txt file
  
  
  This endpoint returns a boolean value and a List. If there are obsene words in the file, the response returns an array of obsene words which were found in the file and boolean value as true.
  
  * **Success Response:**
    * **Code:** 200 <br />
    **Content:** `{ "result" : true , ["obscene","obscene","obscene","obscene"] }`

  `POST` `/v1/censorcontent`
    
    * **Data Params**
    * **Body** Binary of the txt file

    This endpoint returns the censor content of the file in the response.
 
    * **Success Response:**
      * **Code:** 200 <br />
      **Content:** `{ How are you **** }`

  * **SAMPLE CALL**
  
  In this sample call , dombo is a banned word.
  
    curl --request POST --data-binary watchyourdombo https://scancontent.herokuapp.com/v1/censorcontent
    
    curl --request POST --data-binary watchyourtonguedombo https://scancontent.herokuapp.com/v1/scancontent
    
    
    I will recommend to test from POSTMAN , where one can attach the file from disc and select the option binary.

* **BUILD LOCAL INSTRUCTIONS**

    Dependencies:
       
       Maven 3.8.1
       
       Java version : java 12.0.2 
    
    Not Mandatory but preferred.
    Intellij , I personally started and tested project from Intellij Community 2019.2.
    
    Spring Boot Extension in IntelliJ.
    
    To run it locally from your terminal /CMD. 

      1) cd "Directory/project"
  
      2)  mvn clean install
  
      3)  java -jar target/demo-0.0.1-SNAPSHOT.jar
  
        For third step make sure , you have the jar file under the target folder or change it accordingly.
  
      To run it through the editor , clone the repo , import to your editor . I personally used IntelliJ to run and code the app. 

* **RUN TESTS LOCALLY**
   
   From Intellij , one can run the tests after opening the RestResourceIntegrationTest classs.
   
   From terminal, after moving to the directory of the project.
   
    `mvn -Dtest=RestResourceIntegrationTest test`
  
* **DEPLOY INSTRUCTIONS HEROKU**

      heroku login
  
      git init , add , commit 
      
      For already created Heroku app from UI.
      heroku git:remote -a {Heroku App Name}
      
      heroku create
      
      git push heroku master
      
      Follow the instructions on the page to deploy to Heroku.
      https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku
  
 * **NOTES AND ENHANCMENTS** 
  
  1. Salesforce client only utilizes the /scancontent endpoint.
  2. Dockerized version of the app will be implemented soon.
  3. Logging logic will be implemented.
  4. Logic for files >12 MB is partly implemented and will be taken as next feature.

 


