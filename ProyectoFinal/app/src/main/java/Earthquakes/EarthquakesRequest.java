package Earthquakes;


import Http.HttpJSONResponseFormatter;
import org.json.JSONException;
import org.json.JSONObject;

public class EarthquakesRequest implements HttpJSONResponseFormatter
{

    public static final String SITEURL = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=aporter";
    public static final String LONGITUDE_TAG = "lng";
    public static final String LATITUDE_TAG = "lat";
    public static final String MAGNITUDE_TAG = "magnitude";
    public static final String TAG = "earthquakes";
    public static final String ID_TAG = "eqid";




    @Override
    public Earthquake formatJSONResponse(JSONObject earthquakeJSON) throws JSONException {

        return new Earthquake(
                            earthquakeJSON.getString(ID_TAG),
                            Float.valueOf(earthquakeJSON.getString(LONGITUDE_TAG)),
                            Float.valueOf(earthquakeJSON.getString(LATITUDE_TAG)),
                            Float.valueOf(earthquakeJSON.getString(MAGNITUDE_TAG))
                            );

        }

}
