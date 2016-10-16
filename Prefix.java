package code2040;
import okhttp3.*;
import org.json.*;

public class Prefix {
 
    private static final String TOKEN_VALUE = "aa8874426685ac9674ccf75518f60ec0";
    private static final String POST_ENDPOINT = "http://challenge.code2040.org/api/prefix";
    private static final String RETURN_ENDPOINT = "http://challenge.code2040.org/api/prefix/validate";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
     
    private static String receiveInfo() throws Exception {
    
        OkHttpClient client = new OkHttpClient();
        
            RequestBody body = new FormBody.Builder()
                .add("token", TOKEN_VALUE)
                .build();
                        
            Request newRequest = new Request.Builder()
                .url(POST_ENDPOINT)
                .post(body)
                .build();
            
            String givenString;
              
            try { 
                Response response = client.newCall(newRequest).execute();
                givenString = response.body().string();
                System.out.println(givenString);
            } catch (Exception e) {
                return null;
            }
        return givenString;
    }
    
    private static JSONArray findStringsWOPrefix(String strings) throws Exception {
       /*
        try {
            JSONObject jsonObj = new JSONObject(strings);
            String prefix = jsonObj.getString("prefix");
            JSONArray jsonArr = jsonObj.getJSONArray("array");
            JSONArray dicArr = new JSONArray();
                for(int i = 0; i < jsonArr.length(); i++) {
                String currentString = jsonArr.getString(i);
                
                if(!currentString.startsWith(prefix)) {
                dicArr.put(currentString);
                }       
            }
                return dicArr;
            
            */
            try {
            JSONObject jsonObj = new JSONObject(strings);
            String prefix = jsonObj.getString("prefix");
            JSONArray jsonArr = jsonObj.getJSONArray("array");
            String stringWOPrefix = "";
        
                for(int i = 0; i < jsonArr.length(); i++) {
                    String currentString = jsonArr.getString(i);
                    
                    if(!currentString.startsWith(prefix)) {
                        stringWOPrefix = stringWOPrefix + currentString;          
                    }
                }
                System.out.println(stringWOPrefix);
                
                
                String response = receiveInfo();
                JSONObject jsonObject = new JSONObject(response);
                String [] strings1 = JSONObject.getJSONArray(jsonObject);
                JSONArray jsonArray1 = jsonObject.toJSONArray(new JSONArray(strings1));
                return jsonArray1;
                /*
               
              `JSONObject jsonobj2 = new JSONObject(stringWOPrefix.toString());
                System.out.println(jsonObj1);
                JSONArray collection = jsonObj1.getJSONArray("array");
                collection = jsonObj1.getJSONArray("array");
                System.out.println(jsonObj1);
                System.out.println("This is my new array " + jsonObj1);
                return jsonObj1;
                
                
                
                
               */
            
        } catch (JSONException e) {
            return null;
        }
        
        // JSONObject jsonObj1 = new JSONObject(stringWOPrefix);
        // JSONArray collection = jsonObj1.getJSONArray("array");
        // return collection;
    }        
        
    private static void sendNewArrayBack(JSONArray jsonObj1) throws Exception {
    
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
            
            try { 
                Response response = client.newCall(newRequest).execute();
                String responseString = response.body().string();
                System.out.println(responseString);
            
            } catch (Exception e){
            }
    }
    
    private static RequestBody buildBody(JSONArray jsonObj1) throws Exception {
    
        JSONObject jsonObj = new JSONObject();
    
            try {
                jsonObj.put("token", TOKEN_VALUE);
                jsonObj.put("array", jsonObj1);
                RequestBody body = RequestBody.create(JSON, jsonObj.toString());
                return body;

            } catch (JSONException e) {
            return null;
            }   
    }
    
    public void request() throws Exception {
        
        String json = receiveInfo();
        JSONArray jsonObj1 = findStringsWOPrefix(json);
            if (jsonObj1 != null) {
                sendNewArrayBack(jsonObj1);
            }
        System.out.println("\nSending 'POST' request to URL : " + POST_ENDPOINT);
        System.out.println("Posting: " + jsonObj1);
    }
}
