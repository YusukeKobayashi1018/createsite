package ts_ec_kadai.form;

import lombok.Data;

@Data
public class ItemForm {
	
	private int id;

	private int product_id;

	private String title;

	private int price;

	private String description;

	private String picture_url;
	
	private int count;

}
