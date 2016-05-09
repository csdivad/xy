package hu.csdivad.xy.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1080067837587603564L;
	
	@Id
	private Long id;

}
