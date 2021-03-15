package za.co.discovery.bank.app.rest.response;

import za.co.discovery.bank.app.rest.response.entities.WithdrawalDispenseResponse;

import java.util.List;

public class WithdrawalDataHolderResponse extends GenericWSDataHolderResponse {

    private List<WithdrawalDispenseResponse> dispenseValues;

    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     * @param dispenseValues dispense values to be returned
     */
    public WithdrawalDataHolderResponse(String responseCode, String responseMessage,
                                        int status, long timeStamp, List<WithdrawalDispenseResponse> dispenseValues) {
        super(responseCode, responseMessage, status, timeStamp);
        this.dispenseValues = dispenseValues;
    }

    public List<WithdrawalDispenseResponse> getDispenseValues() {
        return dispenseValues;
    }

    public void setUser(List<WithdrawalDispenseResponse> dispenseValues) {
        this.dispenseValues = dispenseValues;
    }
}
