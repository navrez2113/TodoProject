package com.gnsofttr.todoproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gnsofttr.todoproject.dao.TodoRepository;
import com.gnsofttr.todoproject.dao.UserRepository;
import com.gnsofttr.todoproject.dto.TodoDto;
import com.gnsofttr.todoproject.model.Todo;
import com.gnsofttr.todoproject.model.User;
import com.gnsofttr.todoproject.service.TodoService;



@Controller
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoRepository todoRepository;
	

	@RequestMapping("/show")
	public String viewHomePage(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		List<TodoDto> todo = todoRepository.findToDosByUserId(user.getId());
		model.addAttribute("listtodos", todo);
		return "todo";
	}
	
	
	@GetMapping("/newtodo")
	public String newTodo(Model model) {

		model.addAttribute("todos", new TodoDto());
		return "newtodo";
		
	     
	     
	}

	@PostMapping("/newtodo")
	public String newTodoForm(Model model) {

		Todo todo = new Todo();
		model.addAttribute("todos", todo);
		return "newtodo";
	}
	

	@GetMapping("/save")
	public String saveUsers(@ModelAttribute("todos") Todo todo) {

		todoService.saveTodo(todo);
		return "redirect:/todo";

	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute("todos") Todo todo) {
	
		todoService.saveTodo(todo);
		return "redirect:/todo";
	}
	
	@GetMapping("/updateTodo/{id}")
	public String updateTodo(@PathVariable(value = "id") long id, Model model) {

		Todo todo = todoService.getTodoById(id);

		model.addAttribute("todos", todo);
		return "todoupdate";
	}

	@GetMapping("/deleteTodo/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {

		// call delete user method
		this.todoService.deleteById(id);
		return "redirect:/todo";
	}

}
