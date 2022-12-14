package com.tplogistics.core.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "route")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Route {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    UUID routeId;

    /*
     * From location reference
     * */
    @ManyToOne()
    @JoinColumn(name = "from_location_id")
    Location fromLocation;
    /*
     * To location reference
     * */
    @ManyToOne()
    @JoinColumn(name = "to_location_id")
    Location toLocation;

    Double length;
    Double tripBasedCost;
    Double tonBasedLimit;
    Boolean isEnabled;

    /*
     * Reference
     * */
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    List<Job> jobs;
}
