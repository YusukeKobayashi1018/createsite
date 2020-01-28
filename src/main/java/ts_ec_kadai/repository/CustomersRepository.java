package ts_ec_kadai.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ts_ec_kadai.entity.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Integer> {
	

//	public Iterable<Customers> findAll();
	
	public Optional<Customers> findByMailAddress(String mailaddress);

//	public Optional<Customer> findBypre_customer_id(Integer preCustomerId);

}
