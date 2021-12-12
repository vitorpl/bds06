package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
		MovieDTO dto = service.getById(id);
		return ResponseEntity.ok().body( dto );
	}
	
	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> getReviewsByMovieId(@PathVariable Long movieId) {
		List<ReviewDTO> reviews =  service.getReviewsByMovieId(movieId);
		return ResponseEntity.ok().body(reviews);
	}
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> listByGenre(
			@RequestParam(name = "genreId", defaultValue = "0") Long genreId
			) {
		List<MovieDTO> movies =  service.listByGenre(genreId);
		return ResponseEntity.ok().body(movies);
	}
	
	

}
