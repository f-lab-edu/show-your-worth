package kr.texturized.muus.infrastructure.repository.converter.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import kr.texturized.muus.domain.entity.PostCategory;

/**
 * JPA Converter for PostCategory.
 */
@Converter
public class PostCategoryConverter implements AttributeConverter<PostCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PostCategory attribute) {
        return attribute.getValue();
    }

    @Override
    public PostCategory convertToEntityAttribute(Integer dbData) {
        return PostCategory.fromKey(dbData);
    }
}
