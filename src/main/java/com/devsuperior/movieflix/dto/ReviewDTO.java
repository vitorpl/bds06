package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.entities.Review;

public class ReviewDTO implements Serializable {
	private static final long serialVersionUID = 6677737594049558824L;

	private Long id;
	private Long movieId;
	
	@NotBlank
	private String text;
	
	private MovieDTO movie;
	private UserDTO user;
	
	public ReviewDTO() {}

	public ReviewDTO(Long id, String text, Long movieId) {
		super();
		this.id = id;
		this.text = text;
		this.movieId = movieId;
	}
	
	public ReviewDTO(Review entity) {
		this.id = entity.getId();
		this.text = entity.getText();
		this.movieId = entity.getMovie().getId();
		this.user = new UserDTO(entity.getUser());
		this.movie = new MovieDTO(entity.getMovie());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReviewDTO other = (ReviewDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
