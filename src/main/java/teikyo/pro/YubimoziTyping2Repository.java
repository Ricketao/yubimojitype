package teikyo.pro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YubimoziTyping2Repository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Entry_table> findAll(){
		 return jdbcTemplate.query(
	                "SELECT * FROM entry_table",
	                new BeanPropertyRowMapper<Entry_table>(Entry_table.class));
	}
	
	public List<Letter_table> findLettersAll(){
		 return jdbcTemplate.query(
	                "SELECT * FROM letter_table",
	                new BeanPropertyRowMapper<Letter_table>(Letter_table.class));
	}
}
