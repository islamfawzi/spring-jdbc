package com.spring.jdbc.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.jdbc.entities.Person;

@Repository
public class PersonDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getLong("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirth_date(rs.getTimestamp("birth_date"));
			return person;
		}
		
	}

	public List<Person> findAll() {
//		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<Person>(Person.class));
		return jdbcTemplate.query("select * from person", new PersonRowMapper());
	}
	
	public Person findById(long id) {
		return jdbcTemplate.queryForObject("select * from person where id=?", new Object[] {id},
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int insertPerson(Person person) {
		return jdbcTemplate.update("insert into person (id, name, location, birth_date) values(?,?,?,?)",
				new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirth_date() });
	}

	public int updatePerson(Person person) {
		return jdbcTemplate.update("update person set name=?, location=?, birth_date=? where id=?",
				new Object[] { person.getName(), person.getLocation(), person.getBirth_date(), person.getId() });
	}
	
	public int deletePerson(long id) {
//		return jdbcTemplate.update("delete from person where id=?", new Object[] {id});
		return jdbcTemplate.update("delete from person where id=?", id);
	}
}