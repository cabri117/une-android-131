package Http;


import org.json.JSONException;
import org.json.JSONObject;

public interface HttpJSONResponseFormatter {
    public Object formatJSONResponse(JSONObject response) throws JSONException;
}
