package com.devsuperior.movieflix.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {

	Optional<Movie> findById(Long id);
	
	@Query("SELECT m FROM Movie m WHERE m.genre.id = :genreId OR :genreId = 0  order by m.title")
	List<Movie> findByGenreId(Long genreId);
	
}
