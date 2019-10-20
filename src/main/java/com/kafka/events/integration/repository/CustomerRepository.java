package com.kafka.events.integration.repository;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kafka.events.integration.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Optional<Customer> findByLogin(String login);
	
	@Modifying
    @Query("update customers c set c.amount = c.amount + ?2 where c.login = ?1")
    void updateBalance(String login, BigDecimal amount);

    @Modifying
    @Query("update customers c set c.amount = c.amount - ?2 where c.login = ?1 and c.amount >= ?2")
    int withdrawAmount(String login, BigDecimal amount);
}
