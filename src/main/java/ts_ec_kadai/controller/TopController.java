package ts_ec_kadai.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ts_ec_kadai.entity.Item;
import ts_ec_kadai.repository.ItemRepository;
import ts_ec_kadai.service.cartService;

@Controller
public class TopController {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	cartService itemService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getTop(ModelAndView model, Pageable pageable) {

		Page<Item> item = itemRepository.productsAll(pageable);

		model.addObject("itemlist", item);
		model.setViewName("top");
		return model;
	}
	
	@GetMapping(value = "itemdetail/{id}")
	public String itemDetail(@PathVariable int id,Model model) {
		
//		Optional<Integer> item = itemRepository.findItemById(num);
		
		System.out.println(itemRepository.findById(id));
		
		model.addAttribute("item", itemRepository.findById(id).get());
		return "itemdetail";
	}
	
}
