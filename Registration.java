package code20401;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.net.URL;
     
        
public class Registration {
    private final String tokenValue = "aa8874426685ac9674ccf75518f60ec0";
    
    public static void main(String[] args) throws Exception {
    
        Registration http = new Registration();
        http.request();
    }
     
    private void request() throws Exception {
    
        String url = "http://challenge.code2040.org/api/register";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        con.setRequestMethod("POST");
        con.setRequestProperty("token", tokenValue);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");
        
        String myJsonData = 
                "{\"token\":\"aa8874426685ac9674ccf75518f60ec0\",\"github\":\"https://github.com/KimberlyMcIver/Code2040Challenge.git\"}";
        
        con.setDoOutput(true);
        try (DataOutputStream request = new DataOutputStream(con.getOutputStream())) {
            request.writeBytes(myJsonData);
            request.flush();
        }
        
        int outputCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + myJsonData);
        System.out.println("Output Code: " + outputCode);
        
        StringBuffer outputData;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        )) {
            String output;
            outputData = new StringBuffer();
            while ((output = in.readLine()) != null) {
                outputData.append(output);
            }
        }
        System.out.println(outputData.toString());
    }
            
}
