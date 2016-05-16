package hu.csdivad.xy.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1080067837587603564L;
	
	@Id
	@Column(name = "username", length=45)
	private String userName;
	
	@Column(name = "password", length=45, nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", enabled=" + enabled + "]";
	}

	
	
}
