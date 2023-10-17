package kr.texturized.muus.infrastructure.repository.converter.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import kr.texturized.muus.domain.entity.PostCategoryEnum;

/**
 * JPA Converter for PostCategory.
 */
@Converter
public class PostCategoryConverter implements AttributeConverter<PostCategoryEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PostCategoryEnum attribute) {
        return attribute.getValue();
    }

    @Override
    public PostCategoryEnum convertToEntityAttribute(Integer dbData) {
        return PostCategoryEnum.fromKey(dbData);
    }
}
