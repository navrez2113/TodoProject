package com.gnsofttr.todoproject.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.gnsofttr.todoproject.model.Todo;

public class TodoDto {

	private long id;

	@NotEmpty
	private String description;

	private String email;

	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate targetDate;
	
	private boolean done;

	public TodoDto() {
	}

	public TodoDto(Todo todo) {
		this.id = todo.getId();
		this.description = todo.getDescription();
		this.setEmail(todo.getUser().getEmail());
		this.targetDate = todo.getTargetDate();
		this.setDone(todo.isDone());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
