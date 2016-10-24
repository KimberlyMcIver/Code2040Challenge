# Code2040Challenge

Hello! 

Welcome to my application for Code2040's 2017 Fellowship!

In this repo you'll find 5 different classes for each step of the application. 

Registration.java (Step 1) - Here, I performed the intial connection with the API using Java's HttpURLConnection class.
I found using the HttpURLConnection class to be too lengthy and thus switched over OkHttp for the rest of the steps, which is more efficient and has a faster
connection time to the endpoints. In the precending classes, I created request methods for each class that was called in Registration.java.

ReverseMe.java (Step 2) - Here, I connected to the retrieval endpoint using OKHttp to retrieval the string to reverse. I created a 
reverse method and sent the new reversed version of the string back to the validation endpoint.

NeedleInHayStack.java (Step 3) - In this class, I retrieved the dictionary from the endpoint and created a method to find the index
position of the needle within the haystack. Finally I took that needle index and sent it back to the API's validation endpoint.

Prefix.java (Step 4) - In this class, I recieved the info from the retrieval endpoint and passed the information to a method
that finds each word without the beginning prefix and adds it to a new array. This new array is sent back to the API's validation endpoint
through the sendNewArrayBack method.

DatingGame.java (Step 5) - In this class, I recieved the datestamp and interval from the API's retrieval endpoint and singled these two values
out with the help of my getDate and getSeconds methods. From there, I added the two by converting the interval to seconds and formatting. Finally, 
I called my add method in my getFinalTime method and sent the result back to the API's validation endpoint.

Final Take Away: 

I found Code2040's Fellowship application challenging but fun to work on! The challenge pushed me to learn more about Http calls, Http status codes, 
receiving data from an endpoint, processing that data and correctly and finally sending that data back. 
