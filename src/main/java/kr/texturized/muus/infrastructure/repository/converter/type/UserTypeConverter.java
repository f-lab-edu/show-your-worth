package kr.texturized.muus.infrastructure.repository.converter.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import kr.texturized.muus.domain.entity.UserType;

@Converter
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return attribute.getValue();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return UserType.fromKey(dbData);
    }
}
