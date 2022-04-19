package me.jiniworld.demo.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter @Setter
@Schema(description = "사용자")
public class UserRequest {

	@NotBlank
	@Pattern(regexp = "^(BASIC|OWNER)$")
	@Schema(description = "타입", defaultValue = "BASIC", allowableValues = {"BASIC", "OWNER"})
	private String type;

	@NotBlank
	@Email
	@Schema(description = "이메일", example = "abc@jiniworld.me")
	private String email;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z가-힣]{2,}$")
	@Schema(description = "이름")
	private String name;

	@Pattern(regexp = "^(M|F)$")
	@Schema(description = "성별", defaultValue = "M", allowableValues = {"M", "F"})
	private String sex;

	@PastOrPresent
	@Schema(description = "생년월일")
	private LocalDate birthDate;

	@Pattern(regexp = "^01[0179][0-9]{7,8}$")
	@Schema(description = "전화번호")
	private String phoneNumber;

	@NotBlank
	@Schema(description = "비밀번호")
	private String password;
}
