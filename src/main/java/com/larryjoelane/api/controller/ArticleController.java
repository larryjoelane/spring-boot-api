package com.larryjoelane.api.controller;

import com.larryjoelane.api.model.Article;
import com.larryjoelane.api.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired
	ArticleRepository articleRepository;

	// Get All articles
	@GetMapping("/articles")
	@Transactional(readOnly = true)
	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	// Get a Single Article
	@GetMapping("/articles/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long articleId) {
		Article article = articleRepository.findOne(articleId);
		if (article == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(article);
	}

	// Search all articles by title name
	@GetMapping("/articles/search")
	@Transactional(readOnly = true)
	public List<Article> search(String searchTerm) {
		return articleRepository.searchWithNativeQuery(searchTerm);
	}

	// Create a new Article
	@PostMapping("/articles")
	public Article createArticle(@Valid @RequestBody Article article) {
		return articleRepository.save(article);
	}

	// Update an article
	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable(value = "id") Long articleId,
																							 @Valid @RequestBody Article articleDetails) {
		Article article = articleRepository.findOne(articleId);
		if (article == null) {
			return ResponseEntity.notFound().build();
		}
		article.setTitle(articleDetails.getTitle());
		article.setContent(articleDetails.getContent());

		Article updatedArticle = articleRepository.save(article);
		return ResponseEntity.ok(updatedArticle);
	}

	// Delete an article
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Article> deleteArticle(@PathVariable(value = "id") Long articleId) {
		Article article = articleRepository.findOne(articleId);
		if (article == null) {
			return ResponseEntity.notFound().build();
		}

		articleRepository.delete(article);
		return ResponseEntity.ok().build();
	}
}
