package kr.texturized.muus.infrastructure.repository.converter.type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import kr.texturized.muus.domain.entity.UserTypeEnum;

@Converter
public class UserTypeConverter implements AttributeConverter<UserTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserTypeEnum attribute) {
        return attribute.getValue();
    }

    @Override
    public UserTypeEnum convertToEntityAttribute(Integer dbData) {
        return UserTypeEnum.fromKey(dbData);
    }
}
