package Http;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HttpRequestTask extends AsyncTask < Void, Void, List <Object> > {

    private URL url;
    private HttpResponseHandler responseHandler;
    private OnTaskFinishedHandler onTaskFinishedHandler;
    private  String method;
    private int taskId;

    @Override
    protected void onPostExecute(List<Object> response) {

        onTaskFinishedHandler.OnTaskFinished(taskId, response);
        super.onPostExecute(response);

    }

    public HttpRequestTask(int taskId, URL url, String method, HttpResponseHandler responseHandler, OnTaskFinishedHandler onTaskFinishedHandler) {

        this.taskId = taskId;
        this.url = url;
        this.method = method;
        this.responseHandler = responseHandler;
        this.onTaskFinishedHandler = onTaskFinishedHandler;

    }

    @Override
    protected List<Object> doInBackground(Void... voids) {

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // set connection METHOD
            connection.setRequestMethod(method);
            // give it 15 seconds to respond
            connection.setReadTimeout(15 * 1000);
            //make some HTTP header nicety
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // OK
                return responseHandler.handleResponse(connection.getInputStream());
                // Server returned HTTP error code.
            } else {
                // otherwise, if any other status code is returned, or no status
                // code is returned, do stuff in the else block
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
        // Close Connection on finish No matter What
            if (connection != null) connection.disconnect();
        }
        return null;
    }
}
