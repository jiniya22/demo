package me.jiniworld.demo.domain.dto.request;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
	@NotNull private String type, email, name, 
		sex, birthDate, phoneNumber, password;
}
