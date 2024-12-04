package com.planify.planify.entity;

import com.planify.planify.enums.ApprovalStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Represents an approval for a specific plan by a user.
 */
@Entity
@Table(name = "approvals", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"plan_id", "approver_id"})
})
@Getter
@Setter
@ToString
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The plan associated with the approval.
     */
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private Plan plan;

    
    @ManyToOne
    @JoinColumn(name = "approver_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private User user;


    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private ApprovalStatusEnum status = ApprovalStatusEnum.PENDING;


    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public Approval() {
    }

    public Approval(Plan plan, User user) {
        if (plan == null || user == null) {
            throw new IllegalArgumentException("Plan and User cannot be null");
        }
        this.plan = plan;
        this.user = user;
    }
}
