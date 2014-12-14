package Http;


import java.util.List;

public interface OnTaskFinishedHandler {

    public void OnTaskFinished(int taskId, List<String> results);
}
