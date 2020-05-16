package p3.jdbctemplate.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import p3.jdbctemplate.model.Cat;

@Repository
public class CatDAOImpl implements CatDAO
{
	private static final Logger logger = LoggerFactory.getLogger(CatDAOImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Cat> findAll()
	{
		String sql = "SELECT c.id, c.name, c.type FROM cat c";
		RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<Cat>(Cat.class);
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Optional<List<Cat>> findByType(String type)
	{
		Optional<List<Cat>> oCat = Optional.ofNullable(null);
		String sql = "SELECT c.type, c.name, c.id FROM cat c WHERE c.type = ?";
		if (type != null)
		{
			RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<Cat>(Cat.class);
			List<Cat> cat = jdbcTemplate.query(sql, rowMapper, type);
			oCat = Optional.ofNullable(cat);
		} else
		{
			logger.warn("findByType unexpected null input, will do nothing, will return nulled Optional");
		}
		return oCat;
	}

	@Override
	public Optional<Cat> findById(Long id)
	{
		Optional<Cat> oCat = Optional.ofNullable(null);
		String sql = "SELECT c.id, c.name, c.type FROM cat c WHERE c.id = ?";
		if (id != null) {
			RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<Cat>(Cat.class);
			Cat cat = jdbcTemplate.queryForObject(sql, rowMapper, id);
			oCat = Optional.ofNullable(cat);
		} else {
			logger.warn("findById unExpected null input, will do nothing, will return nulled Optional");			
		}
		return oCat;
	}
	
	
}
