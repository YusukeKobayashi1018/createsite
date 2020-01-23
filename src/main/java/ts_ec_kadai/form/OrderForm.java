package ts_ec_kadai.form;

import lombok.Data;

@Data
public class OrderForm {

	private String name;
	
	private String postalCode;
	
	private String address;
	
	private String tel;
	
	private String fradio;
	
	private Integer couponDiscountTotal = 0 ;
	
	private Integer subTotal;
	
	private Integer couponId;
	
	public Integer getTotal() {
		return subTotal - couponDiscountTotal;
	}
	
}
