package com.knowit.projectService.entity;

import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	long id;
	String Username;
	String name;
	String gmail;
	Set<Role> roles = new HashSet<>();
}