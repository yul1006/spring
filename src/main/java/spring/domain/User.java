package spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //자바 객체를 entity로 mapping
public class User {
	@Id @GeneratedValue
	private long id;
	
	@Column(length = 15, nullable = false, unique = true)
	private String userId;

	@Column(length = 30, nullable = false)
	private String password;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column(length = 30, nullable = true)
	private String email;
	
	public User(){//기본 생성자
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

	public boolean matchPassword(String password){
		return this.password.equals(password);
		
	}

	public void update(User user) {
		if(matchPassword(user.password)){
			this.name = user.name;
			this.email = user.email;
			
		}
	}

	public boolean matchId(long id) {
		
		return this.id == id;
	}
	
	
}
