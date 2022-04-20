package me.jiniworld.demo.domain.dto.response.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jiniworld.demo.util.SchemaDescriptionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserData {

    @Data
    @NoArgsConstructor
    @Schema(description = "사용자 간략정보")
    public static class UserSimple {
        private Long id;

        @Schema(description = SchemaDescriptionUtils.User.type)
        private String type;

        @Schema(description = SchemaDescriptionUtils.User.email)
        private String email;

        @Schema(description = SchemaDescriptionUtils.name)
        private String name;

        public UserSimple(me.jiniworld.demo.domain.entity.User u) {
            this.id = u.getId();
            this.type = u.getType();
            this.email = u.getEmail();
            this.name = u.getName();
        }
    }

    @Data
    @Schema(description = "사용자 상세정보")
    public static class User extends UserSimple {
        @Schema(description = SchemaDescriptionUtils.User.sex)
        private String sex;

        @Schema(description = SchemaDescriptionUtils.User.birthDate)
        private LocalDate birthDate;

        @Schema(description = SchemaDescriptionUtils.User.phoneNumber)
        private String phoneNumber;

        @Schema(description = SchemaDescriptionUtils.User.stores)
        private List<StoreData.StoreSimple> stores;

        public User(me.jiniworld.demo.domain.entity.User u) {
            super(u);
            this.sex = u.getSex();
            this.birthDate = u.getBirthDate();
            this.phoneNumber = u.getPhoneNumber();
            this.stores = u.getStores().stream().map(StoreData.StoreSimple::new).collect(Collectors.toList());
        }
    }
}