package ts_ec_kadai.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ts_ec_kadai.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    
	@Query(value = "select * from ITEMS item1 where item1.id in (select max(item2.id) from ITEMS item2 group by item2.product_id)", nativeQuery = true)
    Page<Item> productsAll(Pageable page);

	@Query(value = "select max(id) from ITEMS where product_id = (select product_id from ITEMS where id = :id ) group by product_id;", nativeQuery = true)
    Optional<Integer> findItemById(@Param("id") int id);

}
