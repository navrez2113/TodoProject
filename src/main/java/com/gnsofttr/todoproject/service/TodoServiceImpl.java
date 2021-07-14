package com.gnsofttr.todoproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gnsofttr.todoproject.dao.TodoRepository;
import com.gnsofttr.todoproject.dto.TodoDto;
import com.gnsofttr.todoproject.model.Todo;
import com.gnsofttr.todoproject.model.User;




@Service
public class TodoServiceImpl implements TodoService {
	
	   private TodoRepository todoRepository;

	    private UserService userService;

	    @Autowired
	    public TodoServiceImpl(TodoRepository todoRepository, UserService userService) {
	        this.todoRepository = todoRepository;
	        this.userService = userService;
	    }

	    
	    public List<Todo> getAllTodos() {
			return todoRepository.findAll();
		}
	    
	    
		public Todo getTodoById(long id) {
			Optional<Todo> optional = todoRepository.findById(id);
			Todo todo = null;
			if (optional.isPresent()) {
				todo = optional.get();
			} else {
				throw new RuntimeException(" Todo not found for id :: " + id);
			}
			return todo;
		}
		
		
		public void saveTodo(TodoDto todo) {
			this.todoRepository.save(todo);
		}
		
	    public void save(TodoDto todoDto) {
	        Optional<String> currentUserOptional = userService.getCurrentUser();
	        if (currentUserOptional.isPresent()) {
	            Optional<User> userOptional = userService.getByEmail(currentUserOptional.get());
	            if (userOptional.isPresent()) {
	                Todo toDo = new Todo();
	                toDo.setId(todoDto.getId());
	                toDo.setDescription(todoDto.getDescription());
	                toDo.setTargetDate(todoDto.getTargetDate());
	                toDo.setDone(todoDto.isDone());
	                toDo.setUser(userOptional.get());
	                todoRepository.save(toDo);
	            }
	        }
	    }

	 
		


		@Override
		public void deleteById(Long id) {

			this.todoRepository.deleteById(id);
		}


		@Override
		public void saveTodo(Todo todo) {

			this.todoRepository.save(todo);
			
		}

		
}
