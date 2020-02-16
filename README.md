# stw300cem-final-assignment-rockushal3
stw300cem-final-assignment-rockushal3 created by GitHub Classroom
## JourneyMate
### Introduction
Journey Mate helps you find other travellers to explore the world with. Find people near your current location, and add your future travel plans to see who you will cross paths with. So, this application will be beneficial for those users who are willing to travel regularly. To gain most of the benefits of the system, the user will have to register to the application and update their trip plans and profile to find mates. This application allows them to add people and search profiles. This application will also help the users to socialize with different people. 
  
### Features of the project:
* CRUD operations for adding, viewing, deleting and updating post details
* Log in/Registration System
* Profiling users
* Profiling update
* In app notifications
* High priority notifications
* Suggestions according to friend
* Search profile according to travel place
* Implementation of different sensors
* Shake to open register functionality
* Wearable module to show notifications

### Android project Youtube video link
https://youtu.be/vI8JmOyxmV8
  
### API link:
https://github.com/softwarica-github/t2-backend-api-rockushal3

### REST client
Retrofit is type-safe REST client for Android and Java which aims to make it easier to consume RESTful web services. Retrofit 2 by default leverages OkHttp as the networking layer and is built on top of it. Retrofit automatically serialises the JSON response using a POJO(Plain Old Java Object) which must be defined in advanced for the JSON Structure. To serialise JSON we need a converter to convert it into Gson first. We need to add the following dependencies in our build.grade file.  
  ```
  implementation 'com.squareup.retrofit2:retrofit:version'
  implementation 'com.squareup.retrofit2:converter-gson:version'
  ```  
We can use the appropriate Retrofit annotations for each HTTP method: @GET, @POST, @PUT, @DELETE, @PATCH or @HEAD. In method declaration, we must return the data that is expected from the server wrapped it into a typed Retrofit Call< > class. In method parameter, we can pass path and query parameters, and request body as well in PUT/POST requests.

