import com.Upwork.api.Config;
import com.Upwork.api.OAuthClient;
import com.Upwork.api.Routers.Jobs.Search;
import org.json.JSONException;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws JSONException {
        Properties keys = new Properties();
        keys.setProperty("consumerKey", "1a81dc23d141a41e3e43ea1460b7df12");
        keys.setProperty("consumerSecret", "7e10ccc5df40b226");
        Config config = new Config(keys);
        OAuthClient client = new OAuthClient(config);
        String authzUrl = client.getAuthorizationUrl();

        Scanner scanner = new Scanner(System.in);

        System.out.println(authzUrl);
        System.out.println("1. Copy paste the following url in your browser : ");
        System.out.println(authzUrl);
        System.out.println("2. Grant access ");
        System.out.println("3. Copy paste the oauth_verifier parameter here :");

        String oauth_verifier = scanner.nextLine();
        scanner.close();

        String verifier = null;
        try {
            verifier = URLDecoder.decode(oauth_verifier,"UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> token = client.getAccessTokenSet(verifier);


        Search jobs = new Search(client);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("q", "python");
        params.put("title", "Web Developer");

        jobs.find(params);

    }



}
