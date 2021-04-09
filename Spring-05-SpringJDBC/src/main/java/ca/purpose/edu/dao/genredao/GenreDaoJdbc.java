package ca.purpose.edu.dao.genredao;

import ca.purpose.edu.domain.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
public class GenreDaoJdbc implements GenreDao {
    private NamedParameterJdbcOperations namedJdbc;

    @Override
    public Genre getById(long genreId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("genreId", genreId);
        return namedJdbc.queryForObject("select * from Genres where genreId = :genreId", map, new GenreMapper());
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from Genres", (Map<String, ?>) null, Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("genreId", genre.getGenreId());
        map.addValue("genre", genre.getGenre());
        namedJdbc.update("insert into Genres (genreId, genre) values (:genreId, :genre)", map);
    }

    @Override
    public List<Genre> getAll() {
        return namedJdbc.query("select * from Genres", (Map<String, ?>) null, new GenreMapper());
    }

    @Override
    public void deleteById(long genreId) {
        final MapSqlParameterSource map = new MapSqlParameterSource("genreId", genreId);
        namedJdbc.update("delete from Genres where genreId = :genreId", map);
    }

    @Override
    public void update(Genre genre) {
        final MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("genreId", genre.getGenreId());
        map.addValue("genre", genre.getGenre());
        namedJdbc.update("update Genres set genre = :genre where genreId = :genreId", map);
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
