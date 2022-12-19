package com.jake.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jake.models.Safe;

@Repository
public interface SafeRepository extends CrudRepository<Safe, Integer>, JpaRepository<Safe, Integer>{

}
