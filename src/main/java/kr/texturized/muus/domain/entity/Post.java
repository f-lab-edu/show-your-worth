package kr.texturized.muus.domain.entity;

import javax.persistence.DiscriminatorColumn;
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
 * Inheritance strategy is JOINED. It's truly normalized strategy. It seems using a lot of join, so
 * It looks like not a good performance, but it's not that so in real. It's more effective because
 * of the lack of wasting disk.
 * <br>
 * see:
 * <a href="https://ict-nroo.tistory.com/128">
 *     [JPA] 상속관계 매핑 전략(@Inheritance, @DiscriminatorColumn)
 * </a>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false, updatable = false)
    protected User host;

    protected Post(User host) {
        this.host = host;
    }
}
