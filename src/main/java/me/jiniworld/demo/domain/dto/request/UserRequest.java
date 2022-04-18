package me.jiniworld.demo.domain.dto.request;


import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
	@NotNull private String type;
	private String email;
	private String name;
	private String sex;
	private String birthDate;
	private String phoneNumber;
	private String password;
}
