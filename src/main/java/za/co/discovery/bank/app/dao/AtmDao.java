package za.co.discovery.bank.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.discovery.bank.app.data.model.Atm;

/**
 * This is the data access interface used for retrieving data from and searching the Atm
 * entity table.
 * 
 * @author Torti Ama-Njoku @ Discovery
 */
public interface AtmDao extends JpaRepository<Atm, Integer> {}
