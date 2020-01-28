package ts_ec_kadai.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CUSTOMERS")
@Data
public class Customers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column(name = "MAIL_ADDRESS")
	private String mailAddress;
	
	@Column
	private String password;
	
	@Column
	private int status;
	
	@Column 
	private int pre_customers_id;
	

}
