package ts_ec_kadai.model;

import java.io.Serializable;

import lombok.Data;
import ts_ec_kadai.entity.Item;

@Data
public class CartItemDto implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	public CartItemDto() {
	}

	public CartItemDto(Item item,Integer qty) {
		this.item = item;
		this.qty = qty;
		this.title = item.getTitle();
	}

	public void addQty(Integer qty) {
		this.qty += qty;
	}
	
	public void substractQty(Integer qty) {
		this.qty -= qty;
	}
	
	public Integer getId() {
		return item.getId();
	}
	
	private Integer qty;
	private Item item;
	private String title;
}
