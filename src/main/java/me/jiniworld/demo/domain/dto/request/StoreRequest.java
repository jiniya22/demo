package me.jiniworld.demo.domain.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import me.jiniworld.demo.util.SchemaDescriptionUtils;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@Schema(description = "가게")
public class StoreRequest {

	@NotBlank
	@Schema(description = SchemaDescriptionUtils.name)
	private String name;

	@NotBlank
	@Schema(description = SchemaDescriptionUtils.Store.business)
	private String business;

	private long userId;
}
