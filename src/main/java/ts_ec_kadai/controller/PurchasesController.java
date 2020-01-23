package ts_ec_kadai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ts_ec_kadai.form.OrderForm;
import ts_ec_kadai.model.CartData;
import ts_ec_kadai.model.OrderDto;
import ts_ec_kadai.model.PaymentMethod;
import ts_ec_kadai.service.PurchaseService;

@Controller
@RequestMapping(value = "purchase")
public class PurchasesController {

	@Autowired
	private CartData cart;
	@Autowired
	private PurchaseService purchaseService;

	@ModelAttribute
	public OrderForm setUpOrderForm() {
		OrderForm form = new OrderForm();
		form.setFradio(PaymentMethod.CREDIT.getCode());
		form.setSubTotal(cart.getTotalAmount());
		return form;
	}

	@ModelAttribute
	public Model addDefaults(Model model) {
		model.addAttribute("radioItems", PaymentMethod.values());
		return model;
	}

	@PostMapping(value = "orderConfirm", params = "confirm")
	public String showOrderConfirm(OrderForm form) {

//		if(infoDto.isPresent()) {
//			form.setName(infoDto.get().getName());
//			form.setPostalCode(infoDto.get().getPostalCode());
//			form.setAddress(infoDto.get().getAddress());
//			form.setTel(infoDto.get().getTel());

		return "purchase/orderConfirm";
	}
	
	@PostMapping(value="orderConfirm",params="compleate")
	public String conplete(@ModelAttribute OrderForm form) {
		
		Optional<OrderDto>dto = convertDto(form);
		Integer orderId = purchaseService.complete(dto);
		cart.clearCart();
		cart.setOrderID(orderId);
		return "redirect:/purchase/complete";
	}
	
	@GetMapping(value="complete")
	public String showComplete(Model model) {
		if(cart.getOrderId()==null)
			return "redirect:/cart";
		
		model.addAttribute("orderId",cart.getOrderId());
		cart.clearOrderId();
		return "purchase/complete";
	}
	
	
	private Optional<OrderDto>convertDto(OrderForm form){
		OrderDto dto = new OrderDto();
		
		dto.setName(form.getName());
		dto.setPostalCode(form.getPostalCode());
		dto.setAddress(form.getAddress());
		dto.setTel(form.getTel());
		dto.setPaymentMethod(form.getFradio());
		System.out.println(form.getTotal());
		dto.setTotal(form.getTotal());
		dto.setCouponId(form.getCouponId());
		dto.setItemMap(cart.getCartMap());
		return Optional.of(dto);
	}
	
	
}
