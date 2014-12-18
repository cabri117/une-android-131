package Http;

import java.io.InputStream;
import java.util.List;


public interface HttpResponseHandler {
        public List<Object> handleResponse (InputStream responseStream);
}
