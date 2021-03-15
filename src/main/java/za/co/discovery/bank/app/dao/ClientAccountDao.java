package za.co.discovery.bank.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.discovery.bank.app.data.model.ClientAccount;
import za.co.discovery.bank.app.data.model.reporting.TransactionalAccountReport;
import za.co.discovery.bank.app.enums.AccountTypeCodeEnum;

import java.util.List;
import java.util.Optional;

/**
 * This is the data access interface used for retrieving data from and searching the ClientAccount
 * entity table.
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
public interface ClientAccountDao extends JpaRepository<ClientAccount, String> {

    @Query("select a from ClientAccount as a where a.client.clientId = :clientId and a.accountType.transactional = :isTransactional")
    List<ClientAccount> getAllClientAccounts(@Param("clientId") Integer clientId, @Param("isTransactional") boolean isTransactional);

    @Query("select a from ClientAccount as a where a.client.clientId = :clientId and a.accountType.accountTypeCode = :accountTypeCode")
    List<ClientAccount> getAllClientAccountsByAccountType(@Param("clientId") Integer clientId, @Param("accountTypeCode")AccountTypeCodeEnum accountTypeCode);

    @Query("select a from ClientAccount as a where a.client.clientId = :clientId and a.clientAccountNumber = :accountNumber and a.accountType.transactional = :isTransactional")
    Optional<ClientAccount> getClientAccountByClientAccountNumber(@Param("clientId") Integer clientId, @Param("accountNumber") String accountNumber, @Param("isTransactional") boolean isTransactional);

    @Query(value = "SELECT CA.* " +
            "FROM CLIENT_ACCOUNT AS CA, " +
                "(SELECT SA.CLIENT_ID, MAX(SA.DISPLAY_BALANCE) AS MAX_BALANCE " +
                    "FROM CLIENT_ACCOUNT AS SA, ACCOUNT_TYPE AS ACT " +
                    "WHERE ACT.ACCOUNT_TYPE_CODE = SA.ACCOUNT_TYPE_CODE " +
                    "AND ACT.TRANSACTIONAL = 1 " +
                    "GROUP BY SA.CLIENT_ID" +
                ") AS CAB " +
            "WHERE CA.CLIENT_ID = CAB.CLIENT_ID " +
            "AND CA.DISPLAY_BALANCE = CAB.MAX_BALANCE " +
            "ORDER BY CA.CLIENT_ID",
            nativeQuery = true
    )
    List<ClientAccount> getTransactionalAccountsReport();

    @Query(value = "SELECT " +
            "CONCAT(CLIENT.TITLE, ' ', CLIENT.NAME, ' ', CLIENT.SURNAME), " +
            "SUM(CASE " +
                    "WHEN ACCOUNT_TYPE.ACCOUNT_TYPE_CODE = 'HLOAN' THEN CLIENT_ACCOUNT.DISPLAY_BALANCE " +
                    "WHEN ACCOUNT_TYPE.ACCOUNT_TYPE_CODE = 'PLOAN' THEN CLIENT_ACCOUNT.DISPLAY_BALANCE " +
                    "ELSE 0 " +
                "END), " +
            "SUM(CASE " +
                    "WHEN ACCOUNT_TYPE.TRANSACTIONAL = 1 AND ACCOUNT_TYPE.ACCOUNT_TYPE_CODE = 'CCRD' THEN CLIENT_ACCOUNT.DISPLAY_BALANCE - CCL.ACCOUNT_LIMIT " +
                    "WHEN ACCOUNT_TYPE.TRANSACTIONAL = 1 AND ACCOUNT_TYPE.ACCOUNT_TYPE_CODE != 'CCRD' THEN CLIENT_ACCOUNT.DISPLAY_BALANCE " +
                    "ELSE 0 " +
                "END), " +
            "SUM(CASE " +
                    "WHEN ACCOUNT_TYPE.ACCOUNT_TYPE_CODE = 'CCRD' THEN CLIENT_ACCOUNT.DISPLAY_BALANCE - CCL.ACCOUNT_LIMIT " +
                    "ELSE CLIENT_ACCOUNT.DISPLAY_BALANCE " +
                "END)" +
            "FROM CLIENT_ACCOUNT " +
            "LEFT JOIN ACCOUNT_TYPE ON ACCOUNT_TYPE.ACCOUNT_TYPE_CODE = CLIENT_ACCOUNT.ACCOUNT_TYPE_CODE " +
            "LEFT JOIN CLIENT ON CLIENT.CLIENT_ID = CLIENT_ACCOUNT.CLIENT_ID " +
            "LEFT OUTER JOIN CREDIT_CARD_LIMIT AS CCL ON CCL.CLIENT_ACCOUNT_NUMBER = CLIENT_ACCOUNT.CLIENT_ACCOUNT_NUMBER " +
            "GROUP BY CLIENT.TITLE, CLIENT.NAME, CLIENT.SURNAME",
            nativeQuery = true
    )
    List<Object[]> getFinancialPositionReport();
}
