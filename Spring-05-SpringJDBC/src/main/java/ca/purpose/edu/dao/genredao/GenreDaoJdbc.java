package ca.purpose.edu.dao.genredao;

import ca.purpose.edu.domain.Genre;
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
public class GenreDaoJdbc implements GenreDao {
    private final String SQL_GET_BY_ID = "select * from Genres where genreId = :genreId";
    private final String SQL_COUNT = "select count(*) from Genres";
    private final String SQL_INSERT = "insert into Genres (genre) values (?)";
    private final String SQL_GET_ALL = "select * from Genres";
    private final String SQL_DELETE_BY_ID = "delete from Genres where genreId = :genreId";
    private final String SQL_UPDATE = "update Genres set genre = :genre where genreId = :genreId";

    private NamedParameterJdbcOperations namedJdbc;


    @Override
    public Genre getById(long genreId) {
        MapSqlParameterSource map = new MapSqlParameterSource("genreId", genreId);
        return namedJdbc.queryForObject(SQL_GET_BY_ID, map, new GenreMapper());
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject(SQL_COUNT, (Map<String, ?>) null, Integer.class);
    }

    @Override
    public long insert(Genre genre) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[]{"genreId"});
            ps.setString(1, genre.getGenre());
            return ps;
        };
        namedJdbc.getJdbcOperations().update(preparedStatementCreator, keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public List<Genre> getAll() {
        return namedJdbc.query(SQL_GET_ALL, (Map<String, ?>) null, new GenreMapper());
    }

    @Override
    public boolean deleteById(long genreId) {
        int update = namedJdbc.update(SQL_DELETE_BY_ID, new MapSqlParameterSource("genreId", genreId));
        return update == 1;
    }

    @Override
    public boolean update(Genre genre) {
        int update = namedJdbc.update(SQL_UPDATE, new BeanPropertySqlParameterSource(genre));
        return update == 1;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long genreId = resultSet.getLong("genreId");
            String genre = resultSet.getString("genre");
            return new Genre(genreId, genre);
        }
    }
}
