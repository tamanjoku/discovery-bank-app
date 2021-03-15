package za.co.discovery.bank.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.discovery.bank.app.data.model.CurrencyConversionRate;

/**
 * This is the data access interface used for retrieving data from and searching the ClientAccount
 * entity table.
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
public interface CurrencyConversionRateDao extends JpaRepository<CurrencyConversionRate, String> {

    @Query("select c from CurrencyConversionRate as c where c.currency.currencyCode = :currencyCode")
    CurrencyConversionRate getCurrencyConversionRateByCurrencyCode(@Param("currencyCode") String currencyCode);
}
