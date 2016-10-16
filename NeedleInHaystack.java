package code2040;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class NeedleInHaystack {
    
    private static final String TOKEN_VALUE = "aa8874426685ac9674ccf75518f60ec0";
    private static final String POST_ENDPOINT = "http://challenge.code2040.org/api/haystack";
    private static final String RETURN_ENDPOINT = "http://challenge.code2040.org/api/haystack/validate";
    
    // Receive dictionary from the API's retrieval endpoint
    
    private static String receiveInfo() throws Exception {
    
        OkHttpClient client = new OkHttpClient();
        
            RequestBody body = new FormBody.Builder()
                .add("token", TOKEN_VALUE)
                .build();
                        
            Request newRequest = new Request.Builder()
                .url(POST_ENDPOINT)
                .post(body)
                .build();
        try (Response response = client.newCall(newRequest).execute()){
            return response.body().string();
        }  
    }
    
    // Find the needle position in the Haystack array
    
    private static int findNeedle(String collection) throws Exception {
        
        try{
            int needleIndex = -1;
            JSONObject JsonObj = new JSONObject(collection);
            String needle = JsonObj.getString("needle");
            JSONArray givenHaystack = JsonObj.getJSONArray("haystack");

                for(int i = 0; i < givenHaystack.length(); i++){
                    if(givenHaystack.getString(i).contains(needle)){
                    System.out.println("Found needle at index " + i);
                    needleIndex = i;
                    }
                }
            return needleIndex;
            
        } catch (JSONException e) {
            return -1;
        } 
    }

    // Send the needle position to the API's validation endpoint
    
    private static void sendNeedleIndexBack(int needleIndex) throws Exception {
        String needle = ((Integer) needleIndex).toString();
        OkHttpClient client = new OkHttpClient();
        
        RequestBody body = new FormBody.Builder()
            .add("token", TOKEN_VALUE)
            .add("needle", needle)
            .build();
            
        Request newRequest = new Request.Builder()
                    .url(RETURN_ENDPOINT)
                    .post(body)
                    .build();
            
            try(Response response = client.newCall(newRequest).execute()) {
                System.out.print(response.body().string());
            
            }catch (Exception e){
            }
    }
    
    // Request call for main method in Registration.java

    public void request() throws Exception {
        String collection = receiveInfo();
        int needleIndex = findNeedle(collection);
            if (needleIndex != -1) { 
                sendNeedleIndexBack(needleIndex);
            }    
        System.out.println("\nSending 'POST' request to URL : " + POST_ENDPOINT);
        System.out.println("Posting: " + needleIndex);
    }
}
