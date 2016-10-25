package code2040;
import okhttp3.*;
import org.json.*;

public class Prefix {
 
    private static final String TOKEN_VALUE = "aa8874426685ac9674ccf75518f60ec0";
    private static final String POST_ENDPOINT = "http://challenge.code2040.org/api/prefix";
    private static final String RETURN_ENDPOINT = "http://challenge.code2040.org/api/prefix/validate";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    
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
            
        String infoToString;
              
        Response response = client.newCall(newRequest).execute();
        infoToString = response.body().string();
        System.out.println(infoToString);
     
        return infoToString;
    }
    
    // Iterates through json array, looks for words that do not start with the prefix and adds them to new string. Converts new string to array.
 
    private static JSONArray findStringsWOPrefix(String strings) throws JSONException, IOException {
   
            JSONObject jsonObj = new JSONObject(strings);
            String prefix = jsonObj.getString("prefix");
            JSONArray jsonArr = jsonObj.getJSONArray("array");
            JSONArray jsonArr1 = new JSONArray();
        
                for(int i = 0; i < jsonArr.length(); i++) {
                    String currentString = jsonArr.getString(i);
                    
                    if(!currentString.startsWith(prefix)) {
                        jsonArr1.put(currentString);          
                    }
                }
            return jsonArr1;
    }        
    
    // Sends new array back to API's validation endpoint
 
    private static void sendNewArrayBack(JSONArray jsonObj1) throws JSONException, IOException {
    
        OkHttpClient client = new OkHttpClient();
        RequestBody body = buildBody(jsonObj1);
            if (body == null) {
                System.out.println("error");
                return;
            }
            
        Request newRequest = new Request.Builder()
                    .url(RETURN_ENDPOINT)
                    .post(body)
                    .build();
            
         Response response = client.newCall(newRequest).execute();
         String responseString = response.body().string();
         System.out.println(responseString);    
    }
    
    private static RequestBody buildBody(JSONArray jsonObj1) throws JSONException, IOException {
    
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("token", TOKEN_VALUE);
        jsonObj.put("array", jsonObj1);
        RequestBody body = RequestBody.create(JSON, jsonObj.toString());
        return body;  
    }
    
    // Request call for main method in Registration.java
 
    public void request() throws JSONException, IOException {
        
        String json = receiveInfo();
        JSONArray jsonArr1 = findStringsWOPrefix(json);
            if (jsonArr1 != null) {
                sendNewArrayBack(jsonArr1);
            }
        System.out.println("\nSending 'POST' request to URL : " + POST_ENDPOINT);
        System.out.println("Posting: " + jsonArr1);
    }
}
