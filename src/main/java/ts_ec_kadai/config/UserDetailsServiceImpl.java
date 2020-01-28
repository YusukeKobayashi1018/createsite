package ts_ec_kadai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ts_ec_kadai.entity.Customers;
import ts_ec_kadai.repository.CustomersRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomersRepository customersRepository;
	
	
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		
 		Customers customer = customersRepository.findByMailAddress(username)
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが取得できません。mail="+username));
		
		return new LoginUser(customer);
	}

}
