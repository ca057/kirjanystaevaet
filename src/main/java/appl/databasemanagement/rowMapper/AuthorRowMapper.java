package appl.databasemanagement.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import appl.items.Author;

public class AuthorRowMapper implements RowMapper {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownumber) throws SQLException {
		Author author = new Author();
		author.setAll(rs.getInt(1), rs.getString(2), rs.getString(3));
		return author;
	}

}
