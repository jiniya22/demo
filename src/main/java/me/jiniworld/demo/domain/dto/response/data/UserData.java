package me.jiniworld.demo.domain.dto.response.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserData {

    @Data
    @NoArgsConstructor
    public static class UserSimple {
        private Long id;
        private String type;
        private String email;
        private String name;

        public UserSimple(me.jiniworld.demo.domain.entity.User u) {
            this.id = u.getId();
            this.type = u.getType();
            this.email = u.getEmail();
            this.name = u.getName();
        }
    }

    @Data
    public static class User extends UserSimple {
        private String sex;
        private String birthDate;
        private String phoneNumber;
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