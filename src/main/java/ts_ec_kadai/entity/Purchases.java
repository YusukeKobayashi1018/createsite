package ts_ec_kadai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import ts_ec_kadai.model.PaymentMethod;

@Entity
@Table(name="PURCHASES")
@Data
public class Purchases {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	private int order_id;
	
	private String name;
	
	private String postal_code;
	
	private String address;
	
	private String tel;
	
	private String payment_method = PaymentMethod.CREDIT.getCode();
	
	private Integer total;
	
	private Integer coupon_id;
}
