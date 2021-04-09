package ca.purpose.edu.dao.authordao;

import ca.purpose.edu.domain.Author;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedJdbc;

    @Override
    public Author getById(long authorId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("authorId", authorId);
        return namedJdbc.queryForObject("select * from Authors where authorId = :authorId", map, new AuthorMapper());
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from Authors", (Map<String, ?>) null, Integer.class);
    }

    @Override
    public void insert(Author author) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("authorId", author.getAuthorId());
        map.addValue("authorName", author.getAuthorName());
        namedJdbc.update("insert into Authors (authorId, authorName) values (:authorId, :authorName)", map);
    }

    @Override
    public List<Author> getAll() {
        return namedJdbc.query("select * from Authors", new AuthorMapper());
    }

    @Override
    public void deleteById(long authorId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("authorId", authorId);
        namedJdbc.update("delete from Authors where authorId = :authorId", map);
    }

    @Override
    public void update(Author author) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("authorId", author.getAuthorId());
        map.addValue("authorName", author.getAuthorName());
        namedJdbc.update("update Authors set authorName = :authorName where authorId = :authorId", map);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long authorId = resultSet.getLong("authorId");
            String authorName = resultSet.getString("authorName");
            return new Author(authorId, authorName);
        }
    }
}
