package es.blog.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Quini_Dev
 */
public class VerifyRecaptcha {
    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
        public static final String secret = "yourSecretKey";
        
        public static boolean verify(String gRecaptchaResponse) throws IOException {
            if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
                return false;
            }

            try {
                URL obj = new URL(url);
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

                // add reuqest header
                con.setRequestMethod("POST");
                con.setRequestProperty("Accept-Language", "es-ES,es;q=0.5");

                String postParams = "secret=" + secret + "&response="
                        + gRecaptchaResponse;

                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + postParams);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());

                //parse JSON response and return 'success' value
                
                JSONObject jsonObject = new JSONObject(response.toString());

                return jsonObject.getBoolean("success");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
}
