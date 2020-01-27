package ts_ec_kadai.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ts_ec_kadai.entity.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {

	public Optional<Customers> findBymail_address(String mail_address);

//	public Optional<Customer> findBypre_customer_id(Integer preCustomerId);

}
