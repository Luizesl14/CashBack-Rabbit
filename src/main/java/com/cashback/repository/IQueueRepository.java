package com.cashback.repository;


import com.cashback.model.QueueConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IQueueRepository extends JpaRepository<QueueConfig, Integer> {
    @Query("SELECT q FROM QueueConfig q WHERE q.nameQueue = :name")
    Optional<QueueConfig> findByQueueName(@Param("name") String name);

    @Query("SELECT q FROM QueueConfig q WHERE q.type = :type")
    Optional<QueueConfig> findByType(@Param("type") String type);
}
