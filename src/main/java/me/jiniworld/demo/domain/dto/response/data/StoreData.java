package me.jiniworld.demo.domain.dto.response.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class StoreData {

    @Data
    @NoArgsConstructor
    public static class StoreSimple {
        private Long id;
        private String name;
        private String industry;

        public StoreSimple(me.jiniworld.demo.domain.entity.Store s) {
            this.id = s.getId();
            this.name = s.getName();
            this.industry = s.getIndustry();
        }
    }

    @Data
    public static class Store extends StoreSimple {
        private UserData.UserSimple user;
        public Store(me.jiniworld.demo.domain.entity.Store s) {
            super(s);
            this.user = new UserData.UserSimple(Optional.ofNullable(s.getUser()).orElse(null));
        }

    }
}