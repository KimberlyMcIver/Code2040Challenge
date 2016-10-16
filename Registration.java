package code2040;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.net.URL;
     
// STEP 1: REGISTRATION

public class Registration {
    
    private final String tokenValue = "aa8874426685ac9674ccf75518f60ec0";
     
    private void request() throws Exception {
    
        String endpoint = "http://challenge.code2040.org/api/register";
        URL obj = new URL(endpoint);
        HttpURLConnection newConnection = (HttpURLConnection) obj.openConnection();
        newConnection.setRequestMethod("POST");
        newConnection.setRequestProperty("token", tokenValue);
        newConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        newConnection.setRequestProperty("Content-Type", "application/json");
        String myJsonData = "{\"token\":\"aa8874426685ac9674ccf75518f60ec0\",\"github\":\"https://github.com/KimberlyMcIver/Code2040Challenge.git\"}";
        newConnection.setDoOutput(true);
            
            try (DataOutputStream request = new DataOutputStream(newConnection.getOutputStream())) {
                request.writeBytes(myJsonData);
                request.flush();
            }
        
        int outputCode = newConnection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + endpoint);
        System.out.println("Posting: " + myJsonData);
        System.out.println("Output Code: " + outputCode);
        StringBuffer outputData;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(newConnection.getInputStream()))) {
                String output;
                outputData = new StringBuffer();
            
                while ((output = in.readLine()) != null) {
                    outputData.append(output);
                }
            }
        System.out.println(outputData.toString());
    }
    
    public static void main(String[] args) throws Exception {
    
        Registration step1 = new Registration();
        step1.request();
        
        ReverseMe step2 = new ReverseMe();
        step2.request();
        
        NeedleInHaystack1 step3 = new NeedleInHaystack1();
        step3.request();
        
        Prefix step4 = new Prefix();
        step4.request();
    }         
}

