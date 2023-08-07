package kr.texturized.muus.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"value"})
public class ProfileImage {

    @Column(name = "profile_image_path", length = 250)
    private String value;

    public String[] getImagePathList() {
        return value.split("[\\\\/]");
    }

    public ProfileImage(String value) {
        this.value = value;
    }
}
