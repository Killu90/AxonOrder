package com.novomind.read.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novomind.read.queryobjects.OrderQueryObject;

@Repository
public interface OrderQueryObjectRepository extends JpaRepository<OrderQueryObject, String> {

}
