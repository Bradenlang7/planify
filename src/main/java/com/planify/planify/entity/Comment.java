package com.planify.planify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
@Table(name = "comments")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @ToString.Exclude
    @ManyToOne
    @NotNull
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ToString.Exclude
    @ManyToOne
    @NotNull
    @JoinColumn(name = "commenter_id", nullable = false)
    private User commenter;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    //timestamp handled by the database
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public Comment(Plan plan, User commenter, String content) {
        if (plan == null || commenter == null || content == null) {
            throw new IllegalArgumentException("Null parameter");
        }
        this.plan = plan;
        this.commenter = commenter;
        this.content = content;
    }

    public Comment() {
    }
}
