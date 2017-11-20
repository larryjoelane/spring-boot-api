package com.larryjoelane.api.repository;

import com.larryjoelane.api.model.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(
		value = "SELECT * FROM articles WHERE title LIKE %:searchTerm%",
		nativeQuery = true
	)
	public List<Article> searchWithNativeQuery(@Param("searchTerm") String searchTerm);

	@Query("SELECT a FROM Article a WHERE a.title LIKE %:searchTerm%")
	public List<Article> searchWithJPQLQuery(@Param("searchTerm") String searchTerm);
}