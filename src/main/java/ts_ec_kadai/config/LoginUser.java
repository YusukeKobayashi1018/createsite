package ts_ec_kadai.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import ts_ec_kadai.entity.Customers;

public class LoginUser extends User {
	
	private static final long serialVersionUID = 1L;
	private final Customers user;
	
	public LoginUser(Customers user) {
		super(user.getMail_address(),user.getPassword(),AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}
	
	public Customers getUser() {
		return user;
	}
	
}




	