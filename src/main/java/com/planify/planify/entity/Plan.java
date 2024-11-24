package com.planify.planify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //specify the user that created the plan
    @ManyToOne
    @NotNull
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "start_time", nullable = false)
    @NotNull
    private ZonedDateTime startTime;

    //end time is optional to the user
    @Column(name = "end_time")
    private ZonedDateTime endTime;

    public Plan(User creator, @NonNull String title, String description, String location, ZonedDateTime startTime, ZonedDateTime endTime) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Plan() {

    }
}
