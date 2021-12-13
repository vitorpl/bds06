package com.devsuperior.movieflix.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {
	
	@Autowired
	private ReviewService service;
	
	@Autowired
	private AuthService authService;
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<ReviewDTO> getById(@PathVariable Long id) {
		ReviewDTO dto = service.getById(id);
		return ResponseEntity.ok().body( dto );
	}
	
	@GetMapping(value = "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> getReviewsByMovieId(@PathVariable Long movieId) {
		List<ReviewDTO> reviews =  service.getReviewsByMovieId(movieId);
		return ResponseEntity.ok().body(reviews);
	}
	
	/** O @Valid faz as validações do bean validation definidas no dto */
	@PreAuthorize("hasAnyRole('MEMBER')")
	@PostMapping
	public ResponseEntity<ReviewDTO> salvar(@Valid @RequestBody ReviewDTO dto) {
		UserDTO userAuthDto = authService.authenticatedUserDTO();
		dto.setUser(userAuthDto);
		
		MovieDTO movieDto = new MovieDTO();
		movieDto.setId(dto.getMovieId());
		dto.setMovie(movieDto);
		
		dto = service.save(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
}
