package Http;


import org.json.JSONException;
import org.json.JSONObject;

public interface HttpJSONResponseFormatter {
    public String formatJSONResponse(JSONObject response) throws JSONException;
}
