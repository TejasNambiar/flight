package com.transactions.flight.repository;

import com.transactions.flight.entity.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPassengerRepository extends JpaRepository<PassengerInfo,Long> {
}
