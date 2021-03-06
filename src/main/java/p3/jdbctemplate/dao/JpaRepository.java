package p3.jdbctemplate.dao;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<T,ID>
{
	public List<T> findAll();
	public Optional<List<T>> findByType(String type);
	public Optional<T> findById(Long id);
}
