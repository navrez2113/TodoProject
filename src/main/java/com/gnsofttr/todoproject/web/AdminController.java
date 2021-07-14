package com.gnsofttr.todoproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gnsofttr.todoproject.model.User;
import com.gnsofttr.todoproject.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String viewHomePage(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());
		return "admin";
	}

	@GetMapping("/newuser")
	public String newuser(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "newuser";
	}

	@PostMapping("/newuser")
	public String showNewUserForm(Model model) {
		// create model attribute to bind form data
		User user = new User();
		model.addAttribute("user", user);
		return "newuser";
	}

	@GetMapping("/saveUser")
	public String saveUsers(@ModelAttribute("user") User user) {

		userService.saveUser(user);
		return "redirect:/admin";

	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		// save user to database
		userService.saveUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/updateUser/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// get user from the service
		User user = userService.getUserById(id);

		// set user as a model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {

		// call delete user method
		this.userService.deleteUserById(id);
		return "redirect:/admin";
	}

}
