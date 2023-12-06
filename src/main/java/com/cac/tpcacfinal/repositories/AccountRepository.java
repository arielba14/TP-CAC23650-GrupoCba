package com.cac.tpcacfinal.repositories;

import com.cac.tpcacfinal.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByCbu(String cbu);
    Account findAccountByAlias(String alias);

}
