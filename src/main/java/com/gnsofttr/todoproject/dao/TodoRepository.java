package com.gnsofttr.todoproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gnsofttr.todoproject.dto.TodoDto;
import com.gnsofttr.todoproject.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<TodoDto> findToDosByUserId(@Param("userId") Long userId);

	void save(TodoDto todo);
	
	

}
