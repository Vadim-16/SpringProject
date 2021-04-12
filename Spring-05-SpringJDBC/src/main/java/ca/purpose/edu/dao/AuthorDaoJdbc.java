package ca.purpose.edu.dao;

import ca.purpose.edu.domain.Author;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class AuthorDaoJdbc implements AuthorDao {
    private final String SQL_GET_BY_ID = "select * from Authors where authorId = :authorId";
    private final String SQL_COUNT = "select count(*) from Authors";
    private final String SQL_INSERT = "insert into Authors (authorName) values (?)";
    private final String SQL_GET_ALL = "select * from Authors";
    private final String SQL_DELETE_BY_ID = "delete from Authors where authorId = :authorId";
    private final String SQL_UPDATE = "update Authors set authorName = :authorName where authorId = :authorId";

    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Author getById(long authorId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("authorId", authorId);
        return namedJdbc.queryForObject(SQL_GET_BY_ID, map, new AuthorMapper());
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject(SQL_COUNT, (Map<String, ?>) null, Integer.class);
    }

    @Override
    public long insert(Author author) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"authorId"});
            ps.setString(1, author.getAuthorName());
            return ps;
        };
        namedJdbc.getJdbcOperations().update(preparedStatementCreator, keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public List<Author> getAll() {
        return namedJdbc.query(SQL_GET_ALL, new AuthorMapper());
    }

    @Override
    public boolean deleteById(long authorId) {
        int update = namedJdbc.update(SQL_DELETE_BY_ID, new MapSqlParameterSource("authorId", authorId));
        return update == 1;
    }

    @Override
    public boolean update(Author author) {
        int update = namedJdbc.update(SQL_UPDATE, new BeanPropertySqlParameterSource(author));
        return update == 1;
    }

    private class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            return new Author(authorId, authorName);
        }
    }
}
