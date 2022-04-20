package me.jiniworld.demo.domain.dto.response.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.jiniworld.demo.util.SchemaDescriptionUtils;

import java.util.Optional;

@Data
@NoArgsConstructor
public class StoreData {

    @Data
    @NoArgsConstructor
    public static class StoreSimple {

        private Long id;

        @Schema(description = SchemaDescriptionUtils.name)
        private String name;

        @Schema(description = SchemaDescriptionUtils.Store.business)
        private String business;

        public StoreSimple(me.jiniworld.demo.domain.entity.Store s) {
            this.id = s.getId();
            this.name = s.getName();
            this.business = s.getBusiness();
        }
    }

    @Data
    public static class Store extends StoreSimple {

        @Schema(description = SchemaDescriptionUtils.Store.user)
        private UserData.UserSimple user;

        public Store(me.jiniworld.demo.domain.entity.Store s) {
            super(s);
            this.user = new UserData.UserSimple(Optional.ofNullable(s.getUser()).orElse(null));
        }

    }
}