package kr.texturized.muus.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity for posts(busking, feed, notice, etc).<br>
 * <br>
 * There are entities(keyword, image, etc) mapping with several entity(busking, feed, notice, etc).
 * This entity is super class for entity kinda busking, feed, etc, so keyword or image may be mapped
 * several entity.<br>
 * <br>
 * Inheritance strategy is TABLE_PER_CLASS so only extended entity create tables.
 * At this moment, notice that {@code @GeneratedValue}'s strategy is AUTO
 * (It throws exception when trying with IDENTITY<br>
 * <br>
 * see:
 * <a href="https://ict-nroo.tistory.com/128">
 *     [JPA] 상속관계 매핑 전략(@Inheritance, @DiscriminatorColumn)
 * </a>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false, updatable = false)
    protected User host;

    protected Post(User host) {
        this.host = host;
    }
}
