package com.gnsofttr.todoproject.service;

import java.util.List;

import com.gnsofttr.todoproject.model.Todo;


public interface TodoService {

	List<Todo> getAllTodos();

	void saveTodo(Todo todo);

	void deleteById(Long id);

	Todo getTodoById(long id);

	
}
