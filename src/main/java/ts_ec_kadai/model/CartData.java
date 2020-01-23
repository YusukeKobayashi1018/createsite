package ts_ec_kadai.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Integer, CartItemDto> cartMap;

	private Integer orderId;

	public Map<Integer, CartItemDto> getCartMap() {
		if (cartMap == null)
			cartMap = new HashMap<>();
		return cartMap;
	}
	
	public void addItem(CartItemDto dto) {
		if(cartMap == null)
			cartMap = new HashMap<>();
		
		if(cartMap.containsKey(dto.getId())) {
			cartMap.get(dto.getId()).addQty(dto.getQty());
		}else {
			cartMap.put(dto.getItem().getId(),dto);
		}
	}

	public Integer getTotalAmount() {
		Integer totalAmount = 0;
		if(cartMap == null)
			return  totalAmount;
		for(CartItemDto cartItemDto : cartMap.values())
			totalAmount += (cartItemDto.getQty() * cartItemDto.getItem().getPrice());
		return totalAmount;
	}

	public void deleteItem(Integer id) {
		if(cartMap == null)
			cartMap = new HashMap<>();
		
		if(cartMap.containsKey(id))
			cartMap.remove(id);
	}

	public void clearCart() {
		if(cartMap == null)
			cartMap = new HashMap<>();
		else
			cartMap.clear();
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderID(Integer orderId) {
		this.orderId = orderId;
	}

	public void clearOrderId() {
		orderId = null;
	}
}
