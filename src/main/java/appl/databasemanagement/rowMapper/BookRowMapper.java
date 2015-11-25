package appl.databasemanagement.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import appl.items.Book;

public class BookRowMapper implements RowMapper {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Object mapRow(ResultSet rs, int rownumber) throws SQLException {
		Book book = new Book();
		book.setIsbn(rs.getString(1));
		book.setTitle(rs.getString(2));
		book.setDescription(rs.getString(3));
		book.setPrice(rs.getDouble(4));
		book.setPublisher(rs.getString(5));
		book.setPubdate(rs.getString(6));
		book.setEdition(rs.getString(7));
		book.setPages(rs.getString(8));
		return book;
	}

}
