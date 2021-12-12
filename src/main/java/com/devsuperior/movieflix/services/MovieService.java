package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional(readOnly = true)
	public MovieDTO getById(Long id) {
		Optional<Movie> movieOpt = repository.findById(id);
		Movie movie = movieOpt.orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
		return new MovieDTO(movie);
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> getReviewsByMovieId(Long movieId) {
		List<Review> reviews = reviewRepository.findByMovieId(movieId);
		List<ReviewDTO> reviewsDTO = reviews.stream().map(rev -> new ReviewDTO(rev)).collect(Collectors.toList());
		return reviewsDTO;
	}

	@Transactional(readOnly = true)
	public List<MovieDTO> listByGenre(Long genreId) {
		List<Movie> movies = repository.findByGenreId(genreId);
		List<MovieDTO> moviesDTO = movies.stream().map(mov -> new MovieDTO(mov)).collect(Collectors.toList());
		
		return moviesDTO;
	}
}
