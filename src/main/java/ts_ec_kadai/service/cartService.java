package ts_ec_kadai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ts_ec_kadai.entity.Item;
import ts_ec_kadai.model.CartItemDto;
import ts_ec_kadai.repository.ItemRepository;

@Service
public class cartService {
	
	@Autowired
	private ItemRepository itemRepository;

	public  CartItemDto createCartItemDto(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return createCartItemDto(id,1);
	}

	public CartItemDto createCartItemDto(int id, int qty) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<Item>itemOpt = itemRepository.findById(id);
		itemOpt.orElseThrow(() -> new RuntimeException());
		return new CartItemDto(itemOpt.get(),qty);
	}
}
