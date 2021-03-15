package za.co.discovery.bank.app.rest.response;

import za.co.discovery.bank.app.rest.response.entities.CurrencyAccountResponse;

import java.util.List;

public class CurrencyAccountsListDataHolderResponse extends GenericWSDataHolderResponse {

    private List<CurrencyAccountResponse> accounts;

    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     * @param accounts list of accounts to be returned
     */
    public CurrencyAccountsListDataHolderResponse(String responseCode, String responseMessage,
                                                  int status, long timeStamp, List<CurrencyAccountResponse> accounts) {
        super(responseCode, responseMessage, status, timeStamp);
        this.accounts = accounts;
    }

    public List<CurrencyAccountResponse> getAccounts() {
        return accounts;
    }

    public void setUser(List<CurrencyAccountResponse> clients) {
        this.accounts = clients;
    }
}
