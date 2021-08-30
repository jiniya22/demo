package me.jiniworld.demo.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Getter
@Entity
@Table(name = "users", indexes = {@Index(name = "UK_USERS_EMAIL", columnList = "email", unique = true)})
public class User implements Serializable {
	
	private static final long serialVersionUID = -4253749884585192245L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false, columnDefinition = "INT UNSIGNED")
	private Long id;
	
	@ColumnDefault(value = "0")
	@Column(nullable = false, length = 1, columnDefinition = "CHAR(1)")
	@Setter private String type;
	
	@Column(nullable = false, length = 100)
	@Setter private String email;
	
	@Column(nullable = false, length = 50)
	@Setter private String name;
	
	@ColumnDefault(value = "1")
	@Column(nullable = false, length = 1, columnDefinition = "CHAR(1)")
	@Setter private String sex;
	
	@Column(nullable = false, length = 6)
	@Setter private String birthDate;
	
	@Column(nullable = false, length = 20)
	@Setter private String phoneNumber;
	
	@JsonIgnore
	@Column(nullable = false, length = 150)
	@Setter private String password;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
	@Setter private boolean active;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, columnDefinition = "TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
	private Date updatedAt;	
	
	@Builder
	public User(String type, String email, String name, String sex, String birthDate, String phoneNumber,
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
		createdAt = Timestamp.valueOf(LocalDateTime.now());
		active = true;
	}
	
	@PreUpdate
	protected void onUpdate() {
		updatedAt = Timestamp.valueOf(LocalDateTime.now());
	}
	
}