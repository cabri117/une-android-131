package Http;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class JSONResponseHandler implements HttpResponseHandler {

    private String tag;
    private String encoding;
    private HttpJSONResponseFormatter responseFormatter;

    public JSONResponseHandler(String tag, String encoding, HttpJSONResponseFormatter ResponseFormatter) {
        this.tag = tag;
        this.encoding = encoding;
        this.responseFormatter = ResponseFormatter;
    }

    @Override

    public List<Object> handleResponse(InputStream response) {
        BufferedReader reader = null;
        List<String> result = new ArrayList <>();

        try {
            reader = new BufferedReader(new InputStreamReader(response, encoding));

            for (String line; (line = reader.readLine()) != null; ) {

                result.add(line);
            }

            try {

                JSONArray jsonArray = new JSONArray(result.toString());
                JSONArray tagArray =  (JSONArray) jsonArray.getJSONObject(0).get(tag);
                ArrayList <Object> formattedJSONArray = new ArrayList<>();
                for (int i=0; i < tagArray.length(); i++) {
                    JSONObject jsonObject = tagArray.getJSONObject(i);
                    formattedJSONArray.add(responseFormatter.formatJSONResponse(jsonObject));
                }
                return  formattedJSONArray;

            } catch (JSONException e ){
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //clean up
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}