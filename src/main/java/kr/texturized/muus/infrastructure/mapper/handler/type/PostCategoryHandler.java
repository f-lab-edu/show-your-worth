package kr.texturized.muus.infrastructure.mapper.handler.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.texturized.muus.domain.entity.PostCategoryEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * PostCategory type handler for myBatis.
 * ref: <a href="https://goodteacher.tistory.com/653">06. Enum 타입의 활용 by 은서파</a>
 */
@MappedTypes(PostCategoryEnum.class)
public class PostCategoryHandler implements TypeHandler<PostCategoryEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, PostCategoryEnum parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public PostCategoryEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return getType(rs.getString(columnName));
    }

    @Override
    public PostCategoryEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getType(rs.getString(columnIndex));
    }

    @Override
    public PostCategoryEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getType(cs.getString(columnIndex));
    }

    private PostCategoryEnum getType(String key) {
        return PostCategoryEnum.fromKey(key);
    }
}
