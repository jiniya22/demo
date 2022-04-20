package me.jiniworld.demo.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import me.jiniworld.demo.util.SchemaDescriptionUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@Schema(description = "사용자")
public class UserRequest {

	@NotBlank
	@Pattern(regexp = "^(BASIC|OWNER)$")
	@Schema(description = SchemaDescriptionUtils.User.type, defaultValue = "BASIC", allowableValues = {"BASIC", "OWNER"})
	private String type;

	@Length(min = 1, max = 50)
	@NotBlank
	@Email
	@Schema(description = SchemaDescriptionUtils.User.email, example = "abc@jiniworld.me")
	private String email;

	@NotBlank
	@Pattern(regexp = "^[a-zA-Z가-힣]{2,10}$")
	@Schema(description = SchemaDescriptionUtils.name)
	private String name;

	@Pattern(regexp = "^[MF]$")
	@Schema(description = SchemaDescriptionUtils.User.sex, defaultValue = "M", allowableValues = {"M", "F"})
	private String sex;

	@Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$")
	@Schema(description = SchemaDescriptionUtils.User.birthDate)
	private String birthDate;

	@Pattern(regexp = "^01[0179][0-9]{7,8}$")
	@Schema(description = SchemaDescriptionUtils.User.phoneNumber)
	private String phoneNumber;

	@Length(min = 6, max = 150)
	@NotBlank
	@Schema(description = SchemaDescriptionUtils.User.password)
	private String password;
}
