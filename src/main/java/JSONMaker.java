import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by Shoma on 18.07.2017.
 */
public class JSONMaker {
    public JSONObject getJSONtoAdd(String fileName, int days, String method) throws IOException {
        JSONObject object = new JSONObject();

        JSONArray list = new JSONArray();
        list.add("soufee:" + fileName);
        FileConverter converter = new FileConverter();

        String fileInBytes = converter.encode(fileName);
        list.add(fileInBytes);
        list.add(days);

        object.put("method", method);
        object.put("params", list);
        return object;
    }

    public JSONObject getJSONtoRead(String fileName) {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();

        String method = "name_show";
        list.add("soufee:" + fileName);
        object.put("method", method);
        object.put("params", list);

        return object;
    }

    public JSONObject getJSONtoDelete(String fileName) {
        JSONObject object = new JSONObject();
        JSONArray list = new JSONArray();

        String method = "name_delete";
        list.add("soufee:" + fileName);
        object.put("method", method);
        object.put("params", list);

        return object;
    }

}
