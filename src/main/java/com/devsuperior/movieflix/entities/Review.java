package com.devsuperior.movieflix.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.devsuperior.movieflix.dto.ReviewDTO;

@Entity
@Table(name = "tb_review")
public class Review implements Serializable {

	private static final long serialVersionUID = 8335344437434352590L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	public Review() {}
	
	public Review(ReviewDTO dto) {
		super();
		this.id = dto.getId();
		this.text = dto.getText();
		
		User user = new User(dto.getUser());
		//user.setId(dto.getUser().getId());
		this.user = user;
		
		Movie movie = new Movie();
		movie.setId(dto.getMovie().getId());		
		this.movie = movie;
	}
	
	public Review(Long id, String text, User user, Movie movie) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.movie = movie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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
		Review other = (Review) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
