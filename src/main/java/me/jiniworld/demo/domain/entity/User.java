package me.jiniworld.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter
@Entity
@Table(name = "user", indexes = {@Index(name = "UK_USER_EMAIL", columnList = "email", unique = true)})
@Where(clause = "active = true")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;
	
	@Column(nullable = false, length = 10)
	@ColumnDefault("'BASIC'")
	@Setter private String type;
	
	@Column(nullable = false, length = 100)
	@Setter private String email;
	
	@Column(nullable = false, length = 50)
	@Setter private String name;
	
	@Column(nullable = false, length = 1)
	@ColumnDefault("'M'")
	@Setter private String sex;

	@Setter private LocalDate birthDate;
	
	@Column(nullable = false, length = 20)
	@Setter private String phoneNumber;
	
	@JsonIgnore
	@Column(nullable = false, length = 150)
	@Setter private String password;
	
	@Column(nullable = false)
	@ColumnDefault("true")
	@Setter private boolean active;
	
	@Column(nullable = false, updatable = false, columnDefinition = "DATETIME")
	@ColumnDefault("CURRENT_TIMESTAMP()")
	private LocalDateTime createdAt;

	@Column(columnDefinition = "DATETIME")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "FK_USER_DEPARTMENT"))
	private Department department;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@Fetch(FetchMode.SUBSELECT)
	@Setter private List<Store> stores = new ArrayList<>();

	@Version
	private Long version;

	@Builder
	public User(String type, String email, String name, String sex, LocalDate birthDate, String phoneNumber,
			String password) {
		super();
		this.type = type;
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		active = true;
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
}