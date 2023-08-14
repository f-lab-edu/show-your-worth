package kr.texturized.muus.infrastructure.mapper.handler.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kr.texturized.muus.domain.entity.UserType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * UserType type handler for myBatis.
 * ref: <a href="https://goodteacher.tistory.com/653">06. Enum 타입의 활용 by 은서파</a>
 */
@MappedTypes(UserType.class)
public class UserTypeHandler implements TypeHandler<UserType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, UserType parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public UserType getResult(ResultSet rs, String columnName) throws SQLException {
        return getType(rs.getString(columnName));
    }

    @Override
    public UserType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getType(rs.getString(columnIndex));
    }

    @Override
    public UserType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getType(cs.getString(columnIndex));
    }

    private UserType getType(String key) {
        return UserType.fromKey(key);
    }
}
