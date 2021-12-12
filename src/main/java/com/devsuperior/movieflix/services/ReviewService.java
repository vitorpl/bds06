package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.aspectj.apache.bcel.Repository;
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
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Transactional(readOnly = true)
	public ReviewDTO getById(Long id) {
		Optional<Review> opt = repository.findById(id);
		Review review = opt.orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
		return new ReviewDTO(review);
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> getReviewsByMovieId(Long movieId) {
		List<Review> reviews = repository.findByMovieId(movieId);
		List<ReviewDTO> reviewsDTO = reviews.stream().map(rev -> new ReviewDTO(rev)).collect(Collectors.toList());
		return reviewsDTO;
	}

	@Transactional(readOnly = true)
	public ReviewDTO save(ReviewDTO dto) {
		return new ReviewDTO(repository.save(new Review(dto)));
	}
}
