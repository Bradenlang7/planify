package com.planify.planify.entity;

import com.planify.planify.enums.ApprovalStatusEnum;
import jakarta.persistence.*;
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
    @ToString.Exclude
    private Plan plan;

    /**
     * The user who is approving the plan.
     */
    @ManyToOne
    @JoinColumn(name = "approver_id", nullable = false)
    @ToString.Exclude
    private User user;

    /**
     * The approval status (PENDING, APPROVED, REJECTED).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApprovalStatusEnum status = ApprovalStatusEnum.PENDING;

    /**
     * The timestamp when the approval was created.
     * Managed by the database.
     */
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
