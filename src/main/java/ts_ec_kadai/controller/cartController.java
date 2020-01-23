package ts_ec_kadai.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ts_ec_kadai.form.CartForm;
import ts_ec_kadai.model.CartData;
import ts_ec_kadai.repository.ItemRepository;
import ts_ec_kadai.service.cartService;


@Controller
@RequestMapping(value="/cart")
public class cartController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	cartService cartService;
	
	@Autowired
	CartData cart;
	
	@ModelAttribute
	public CartForm setUpCartForm() {
		return new CartForm();
	}
	
	@GetMapping(value="")
	public String listAll(Model model,CartForm form) {
		form.setCartList(new ArrayList<>(cart.getCartMap().values()));
		model.addAttribute("totalAmount",cart.getTotalAmount());
		model.addAttribute("isListEmpty",cart.getCartMap().keySet().size()==0);
		return "cart";
	}
	
	@PostMapping(value="/add/{id}")
	public String addCart(@PathVariable int id,ModelAndView model) {
		cart.addItem(cartService.createCartItemDto(id));
		return "redirect:/cart";
	}
	
	@GetMapping(value="delete")
	public String deleteItem(@RequestParam int id) {
		cart.deleteItem(id);
		return "redirect:/cart";
	}
	
	@PostMapping(value="recalc")
	public String recalc(@ModelAttribute CartForm form,Model model) {
		form.getCartList()
		.forEach(formstream -> cart.getCartMap().values().stream()
		.filter(sessionDtoFilter -> formstream.getId().equals(sessionDtoFilter.getId()))
		.forEach(session -> session.setQty(formstream.getQty())));
		
		List<Integer>quantityZeroItemList = cart.getCartMap().values().stream().filter(cart -> cart.getQty() == 0)
				.map(deleteItem -> deleteItem.getId()).collect(Collectors.toList());
		for(Integer id : quantityZeroItemList)
			cart.deleteItem(id);
		return "redirect:/cart";
	}
}
