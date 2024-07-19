package com.example.accounts.repository;

import com.example.accounts.entity.ReferenceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRecordRepository extends JpaRepository<ReferenceRecord, Long> {
}