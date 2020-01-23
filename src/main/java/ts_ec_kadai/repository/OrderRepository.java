package ts_ec_kadai.repository;

import org.springframework.data.repository.CrudRepository;

import ts_ec_kadai.entity.Order;

public interface OrderRepository extends CrudRepository<Order,Integer>{

}
