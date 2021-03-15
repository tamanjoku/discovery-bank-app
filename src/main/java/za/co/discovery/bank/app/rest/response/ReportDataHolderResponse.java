package za.co.discovery.bank.app.rest.response;

import java.util.List;

public class ReportDataHolderResponse<T> extends GenericWSDataHolderResponse {

    private List<T> data;

    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     * @param data report data list
     */
    public ReportDataHolderResponse(String responseCode, String responseMessage,
                                    int status, long timeStamp, List<T> data) {
        super(responseCode, responseMessage, status, timeStamp);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setUser(List<T> dispenseValues) {
        this.data = dispenseValues;
    }
}
