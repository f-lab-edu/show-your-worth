package kr.texturized.muus.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity for Busking.
 */
@Entity
@Table(name = "busking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"host", "title", "createTime"})
public class Busking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false, updatable = false)
    private User host;

    private Double latitude;
    private Double longitude;

    @Column(nullable = false, length = 20)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @FutureOrPresent
    @Column(name = "managed_start_time", nullable = false)
    private LocalDateTime managedStartTime;

    @Future
    @Column(name = "managed_end_time", nullable = false)
    private LocalDateTime managedEndTime;

    @CreationTimestamp
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Future
    @Column(name = "end_time")
    private LocalDateTime endTime;

    /**
     * Constructor.
     */
    @Builder
    public Busking(
        final User host,
        final Double latitude,
        final Double longitude,
        final String title,
        final String description,
        final LocalDateTime managedStartTime,
        final LocalDateTime managedEndTime,
        final LocalDateTime endTime
    ) {
        this.host = host;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.managedStartTime = managedStartTime;
        this.managedEndTime = managedEndTime;
        this.endTime = endTime;
    }
}
