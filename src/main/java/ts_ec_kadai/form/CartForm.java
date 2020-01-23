package ts_ec_kadai.form;

import java.util.List;

import lombok.Data;
import ts_ec_kadai.model.CartItemDto;

@Data
public class CartForm {
	private List<CartItemDto>cartList;
	


}
