package com.anubhav.mgtc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anubhav.mgtc.entity.User;
import com.anubhav.mgtc.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired UserService UserService;
	@RequestMapping("/")
	public @ResponseBody JsonNode getUsers() throws JsonProcessingException {
		return UserService.getAllUsers();
	}
	
	@RequestMapping(path="/add", method= {RequestMethod.POST})
	public @ResponseBody JsonNode addUser(@RequestBody User user  ) throws JsonProcessingException {
		return UserService.addUser(user);
	}
}
