package com.emploiconnect.repository;

import com.emploiconnect.entity.Application;
import com.emploiconnect.enums.ApplicationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
