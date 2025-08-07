import java.util.HashMap;
import java.util.Map;

public class MultiResponse extends Response {
    private Map<String, String> responseTable;

    public MultiResponse() {
        super();
        responseTable = new HashMap<>();
    }

    public MultiResponse(int questionID, int responseID) {
        super(questionID, responseID);
        responseTable = new HashMap<>();
    }

    @Override
    public boolean isValid() {
        return responseTable != null && !responseTable.isEmpty();
    }

    public Map<String, String> getResponseTable() { return responseTable; }
    public void addResponse(String key, String value) { responseTable.put(key, value); }
    public void setResponseTable(Map<String, String> responseTable) { this.responseTable = responseTable; }
}
