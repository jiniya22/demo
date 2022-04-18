package me.jiniworld.demo.domain.dto.response.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.jiniworld.demo.domain.entity.Store;

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

        public StoreSimple(Store s) {
            this.id = s.getId();
            this.name = s.getName();
            this.industry = s.getIndustry();
        }
    }

    @Data
    public static class StoreDetail extends StoreSimple {
        private UserData.UserSimple user;
        public StoreDetail(Store s) {
            super(s);
            this.user = new UserData.UserSimple(Optional.ofNullable(s.getUser()).orElse(null));
        }

    }
}