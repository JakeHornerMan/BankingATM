package com.jake.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jake.models.Account;
import com.jake.models.Safe;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>, JpaRepository<Account,Integer> {
	Account getByAccountNumber(int accountNumber);
}
