package ts_ec_kadai.model;

import java.util.Map;

import lombok.Data;

@Data
public class OrderDto {

	private Integer customerId=99;
	
	private Map<Integer,CartItemDto>itemMap;
	
	private String name;
	
	private String postalCode;
	
	private String address;
	
	private String tel;
	
	private String paymentMethod;
	
	private Integer total;
	
	private Integer couponId;
}
