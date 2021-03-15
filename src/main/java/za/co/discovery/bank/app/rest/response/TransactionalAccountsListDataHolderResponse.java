package za.co.discovery.bank.app.rest.response;

import za.co.discovery.bank.app.rest.response.entities.TransactionalAccountResponse;

import java.util.List;

public class TransactionalAccountsListDataHolderResponse extends GenericWSDataHolderResponse {

    private List<TransactionalAccountResponse> accounts;
    
    /**
     * @param responseCode webservice response code
     * @param responseMessage webservice response message
     * @param status HTTP status code
     * @param timeStamp time stamp of the reponse
     * @param accounts list of accounts to be returned
     */
    public TransactionalAccountsListDataHolderResponse(String responseCode, String responseMessage,
                                                       int status, long timeStamp, List<TransactionalAccountResponse> accounts) {
        super(responseCode, responseMessage, status, timeStamp);
        this.accounts = accounts;
    }

    public List<TransactionalAccountResponse> getAccounts() {
        return accounts;
    }

    public void setUser(List<TransactionalAccountResponse> clients) {
        this.accounts = clients;
    }
}
