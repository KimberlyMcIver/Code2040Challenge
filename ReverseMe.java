package code2040;
import okhttp3.*;
import java.io.IOException;

public class ReverseMe {
   
    // Initializing empty string container "reversedString" as class variable for reverseString Method.
    
    String reversedString = ""; 
 
    private static final String TOKEN_VALUE = "aa8874426685ac9674ccf75518f60ec0";
    private static final String POST_ENDPOINT = "http://challenge.code2040.org/api/reverse";
    private static final String RETURN_ENDPOINT = "http://challenge.code2040.org/api/reverse/validate";
    
    // Receive string from the API's post endpoint
    
    private static String receiveInfo() throws IOException {
    
        OkHttpClient client = new OkHttpClient();
        
            RequestBody body = new FormBody.Builder()
                .add("token", TOKEN_VALUE)
                .build();
                        
            Request newRequest = new Request.Builder()
                .url(POST_ENDPOINT)
                .post(body)
                .build();
            
        String newString;
           
        Response response = client.newCall(newRequest).execute()
        newString = response.body().string();
        System.out.println("The given string is: " + newString);
        return newString;
    }
    
     // Reverse the given string from the API
     
    private String reverseString(String newString) throws IOException {
    
        for(int i = newString.length() - 1; i >= 0; i--){
            char letter = newString.charAt(i);
            reversedString = reversedString + letter;
        }
        System.out.println("The reversed string is: " + reversedString);
        return reversedString; 
    }  
   
    // Send the newly reversed string back to the API's validation endpoint.
    
    private void sendStringBack(String reversedString) throws IOException {
       
        OkHttpClient client = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder()
            .add("token", TOKEN_VALUE)
            .add("reversedSting", reversedString)
            .build();
            
        Request newRequest = new Request.Builder()
            .url(RETURN_ENDPOINT)
            .post(body)
            .build();
            
         Response response = client.newCall(newRequest).execute()
         System.out.print(response.body().string());
         }
    }
    
    // Request Call for main method in Registration.java
    
    public void request() throws IOException {
        
        String newString = receiveInfo();
            if (newString != null) {
            String reversedString = reverseString(newString);
            sendStringBack(reversedString);
            }
        System.out.println("\nSending 'POST' request to URL : " + POST_ENDPOINT);
        System.out.println("Posting: " + reversedString);
    } 
}
