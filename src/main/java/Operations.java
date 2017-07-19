import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Shoma on 18.07.2017.
 */
public class Operations {

    public static String addfile(String fileName, String url) throws IOException {
        JSONMaker maker = new JSONMaker();
        JSONObject object = maker.getJSONtoAdd(fileName, 7, "name_new");
        System.out.println(object);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(object.toJSONString());
        post.setEntity(entity);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        String toreturn = "Response Code : " + response.getStatusLine().getStatusCode();
        return toreturn;

    }

    public static String readFile(String fileName, String url) throws IOException, ParseException {
        JSONMaker maker = new JSONMaker();
        JSONObject object = maker.getJSONtoRead(fileName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(object.toJSONString());
        post.setEntity(entity);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        String toreturn = "Response Code : " + response.getStatusLine().getStatusCode();
        StringBuilder builder = new StringBuilder();
        JSONParser parser = new JSONParser();
        BufferedReader stream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = stream.readLine()) != null) {
            builder.append(line);
        }
        JSONObject o = (JSONObject) parser.parse(builder.toString());
        o = (JSONObject) o.get("result");
        String encodedfile = (String) o.get("value");
        FileConverter converter = new FileConverter();
        converter.decode(encodedfile, fileName);
        return toreturn;

    }

    public static String deleteFile(String fileName, String url) throws IOException {
      JSONMaker jsonMaker = new JSONMaker();
      JSONObject object = jsonMaker.getJSONtoDelete(fileName);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(object.toJSONString());
        post.setEntity(entity);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        String toreturn = "Response Code : " + response.getStatusLine().getStatusCode();
        return toreturn;
    }
    public static String updateFile(String fileName, int days, String url) throws IOException {
        JSONMaker jsonMaker = new JSONMaker();
        JSONObject object = jsonMaker.getJSONtoAdd(fileName, days, "name_update");
        System.out.println(object);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(object.toJSONString());
        post.setEntity(entity);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = client.execute(post);
        String toreturn = "Response Code : " + response.getStatusLine().getStatusCode();

        return toreturn;
    }



}
