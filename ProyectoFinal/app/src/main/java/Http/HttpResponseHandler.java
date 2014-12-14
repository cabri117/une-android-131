package Http;

import java.io.InputStream;
import java.util.List;


public interface HttpResponseHandler {
        public List<String> handleResponse (InputStream responseStream);
}
