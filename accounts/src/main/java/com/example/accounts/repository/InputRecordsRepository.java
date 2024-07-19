package com.example.accounts.repository;


import com.example.accounts.entity.InputRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRecordsRepository extends JpaRepository<InputRecords, Long> {

}


