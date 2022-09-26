package com.tplogistics.repository;

import com.tplogistics.core.domain.entity.JobTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobTrackingRepository extends JpaRepository<JobTracking, UUID> {

}
