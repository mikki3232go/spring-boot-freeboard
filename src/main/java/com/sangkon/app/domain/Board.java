package com.sangkon.app.domain;

import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString(exclude = "user")
public class Board {
    @Id
    @GeneratedValue
    private Long idx;

    private String title;
    private String subTitle;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
