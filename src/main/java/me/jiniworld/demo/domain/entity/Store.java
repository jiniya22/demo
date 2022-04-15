package me.jiniworld.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER_STORE"))
    private User user;

    @Column(nullable=false, length = 100)
    private String name;

    @Column(length = 30)
    private String industry;

    @Builder
    public Store(User user, String name, String industry) {
        this.user = user;
        this.name = name;
        this.industry = industry;
    }
}
