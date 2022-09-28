package com.tplogistics.core.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "job")
public class Job {
    @Id
    @Column(name = "id")
    UUID jobId;

    // Job's information
    UUID driverId;
    UUID transportationId;
    UUID routeId;
    UUID fromGarage;
    UUID toGarage;
    Double distance;
    Double total;
    String pickupContactName;
    String pickupContactNo;
    String unloadContactName;
    String unloadContactNo;

    // Job's progress
    @Column(name = "status")
    Integer jobStatus;
    // Time statistics
    @Temporal(TemporalType.DATE)
    Date createdAt;
    @Temporal(TemporalType.DATE)
    Date assignedAt;
    @Temporal(TemporalType.DATE)
    Date acceptedAt;
    @Temporal(TemporalType.DATE)
    Date pickupArriveAt;
    @Temporal(TemporalType.DATE)
    Date pickupDoneAt;
    @Temporal(TemporalType.DATE)
    Date unloadArriveAt;
    @Temporal(TemporalType.DATE)
    Date unloadDoneAt;
    @Temporal(TemporalType.DATE)
    Date completedAt;
}
