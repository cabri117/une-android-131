package Earthquakes;


import Http.HttpJSONResponseFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class Earthquakes implements HttpJSONResponseFormatter
{

    public static final String SITEURL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=aporter";
    private static final String LONGITUDE_TAG = "lng";
    private static final String LATITUDE_TAG = "lat";
    private static final String MAGNITUDE_TAG = "magnitude";
    public static final String TAG = "earthquakes";


    @Override
    public String formatJSONResponse(JSONObject earthquake) throws JSONException {


            return MAGNITUDE_TAG + ":"
                    + earthquake.get(MAGNITUDE_TAG) + ","
                    + LATITUDE_TAG + ":"
                    + earthquake.getString(LATITUDE_TAG) + ","
                    + LONGITUDE_TAG + ":"
                    + earthquake.get(LONGITUDE_TAG);
        }

}
