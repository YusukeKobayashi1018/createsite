package ts_ec_kadai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ts_ec_kadai.entity.Order;
import ts_ec_kadai.entity.OrderDetail;
import ts_ec_kadai.entity.Purchases;
import ts_ec_kadai.exception.NotSetParameterException;
import ts_ec_kadai.model.CartItemDto;
import ts_ec_kadai.model.OrderDto;
import ts_ec_kadai.repository.OrderDetailRepository;
import ts_ec_kadai.repository.OrderRepository;
import ts_ec_kadai.repository.PurchasesRepository;

@Component
public class PurchaseService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	
	public Integer complete(Optional<OrderDto>opt) {
		OrderDto dto = opt.orElseThrow(() -> new NotSetParameterException("DTOが未定義です"));
		
		Order order  = new Order();
		order.setCustomer_id(dto.getCustomerId());
		orderRepository.save(order);
		
		List<OrderDetail>detailList = new ArrayList<>();
		for(CartItemDto item : dto.getItemMap().values()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder_id(order.getId());
			detail.setItem_id(item.getId());
			detail.setQty(item.getQty());
			detailList.add(detail);
		}
		orderDetailRepository.saveAll(detailList);
		
		Purchases purchase = createPurchase(dto);
		purchase.setOrder_id(order.getId());
		return purchasesRepository.save(purchase).getId();
	}


	private Purchases createPurchase(OrderDto dto) {
		Purchases purchase = new Purchases();
		purchase.setName(dto.getName());
		purchase.setPostal_code(dto.getPostalCode());
		purchase.setAddress(dto.getAddress());
		purchase.setTel(dto.getTel());
		purchase.setCoupon_id(1);
		purchase.setTotal(dto.getTotal());
		
		return purchase;
	}
	
	
	
}
