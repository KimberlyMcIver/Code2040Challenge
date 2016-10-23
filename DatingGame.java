package code2040;
import okhttp3.*;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;


public class DatingGame {
   
    private static final String TOKEN_VALUE = "aa8874426685ac9674ccf75518f60ec0";
    private static final String POST_ENDPOINT = "http://challenge.code2040.org/api/dating";
    private static final String RETURN_ENDPOINT = "http://challenge.code2040.org/api/dating/validate";
    
    // Receive dictionary from the API's retrieval endpoint 
    
    private static String receiveInfo() throws IOException {
    
        OkHttpClient client = new OkHttpClient();
        
            RequestBody body = new FormBody.Builder()
                .add("token", TOKEN_VALUE)
                .build();
                        
            Request newRequest = new Request.Builder()
                .url(POST_ENDPOINT)
                .post(body)
                .build();
            
            String InfoToString;
              
            Response response = client.newCall(newRequest).execute();
            InfoToString = response.body().string();
              
        return InfoToString;
    }
    
    // Singles out the datestamp info from the dictionary and returns it
    
    private static String getDate(String retrievedJson) throws JSONException, IOException {
    
        JSONObject jsonTime = new JSONObject(retrievedJson);
        String givenDateStamp = jsonTime.getString("datestamp");
        return givenDateStamp;
    
    }
    
    // Singles out the interval info from the dictionary and returns it
    
    private static int getSeconds(String retrievedJson) throws JSONException, IOException {
    
        JSONObject jsonTime = new JSONObject(retrievedJson);
        int interval = jsonTime.getInt("interval");
        return interval;
        
    }
    
    // Adds interval (in seconds) to result, formats and returns new time
    
    private static String addSecondsToDate(String givenDateStamp, int givenInterval) throws JSONException, IOException {
        
        DateTime result = new DateTime(givenDateStamp);
        result = result.plusSeconds(givenInterval);
        result = result.toDateTime(DateTimeZone.UTC);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(ISODateTimeFormat.dateTimeNoMillis()).toFormatter().withOffsetParsed();
        String newTime = formatter.print(result);
        return newTime;
    }
   
    // Gets the final time by calling addSecondsToDate() to add the original date and seconds
    
    private static String getFinalTime(String retrievedJson) throws JSONException, IOException {
        
        String gotDateStamp = getDate(retrievedJson);
        int gotInterval = getSeconds(retrievedJson);
        String finalTime = addSecondsToDate(gotDateStamp, gotInterval);
        return finalTime;
    
    }
    
    // Sends new finalTime back to the API's validation endpoint
    
    private void sendNewDateBack(String time) throws IOException {
    
        OkHttpClient client = new OkHttpClient();
       
        RequestBody body = new FormBody.Builder()
            .add("token", TOKEN_VALUE)
            .add("datestamp", time)
            .build();
        
        Request newRequest = new Request.Builder()
            .url(RETURN_ENDPOINT)
            .post(body)
            .build();
              
        Response response = client.newCall(newRequest).execute();
        System.out.println(response.body().string());          
    }
    
    // Request call for main method in Registration.java
    
    public void request() throws JSONException, IOException {
            
        String retrievedJson = receiveInfo();
            if (retrievedJson != null){
                String finalTime = getFinalTime(retrievedJson);
                sendNewDateBack(finalTime);
                System.out.println("\nSending 'POST' request to URL : " + POST_ENDPOINT);
                System.out.println("Posting: " + finalTime);
            }
    }
}
