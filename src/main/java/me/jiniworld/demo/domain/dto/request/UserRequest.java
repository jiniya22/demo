package me.jiniworld.demo.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import me.jiniworld.demo.util.SchemaDescriptionUtils;

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
	@Schema(description = SchemaDescriptionUtils.User.type, defaultValue = "BASIC", allowableValues = {"BASIC", "OWNER"})
	private String type;

	@NotBlank
	@Email
	@Schema(description = SchemaDescriptionUtils.User.email, example = "abc@jiniworld.me")
	private String email;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z가-힣]{2,}$")
	@Schema(description = SchemaDescriptionUtils.name)
	private String name;

	@Pattern(regexp = "^(M|F)$")
	@Schema(description = SchemaDescriptionUtils.User.sex, defaultValue = "M", allowableValues = {"M", "F"})
	private String sex;

	@PastOrPresent
	@Schema(description = SchemaDescriptionUtils.User.birthDate)
	private LocalDate birthDate;

	@Pattern(regexp = "^01[0179][0-9]{7,8}$")
	@Schema(description = SchemaDescriptionUtils.User.phoneNumber)
	private String phoneNumber;

	@NotBlank
	@Schema(description = SchemaDescriptionUtils.User.password)
	private String password;
}
