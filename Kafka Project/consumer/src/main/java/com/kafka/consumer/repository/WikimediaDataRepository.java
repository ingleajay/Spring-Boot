package com.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafka.consumer.entity.WikimediaData;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}