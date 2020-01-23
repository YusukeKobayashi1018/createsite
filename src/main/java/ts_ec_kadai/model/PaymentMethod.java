package ts_ec_kadai.model;

public enum PaymentMethod {

	CREDIT("0","クレジットカード"),
	
	DAIBIKI("1","代金引換");
	
	private final String code;
	
	private final String name;
	
	PaymentMethod(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
}
