package ts_ec_kadai.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "ITEMS")
@Data
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private int product_id;

	@Column
	private String title;

	@Column
	private int price;

	@Column
	private String description;

	@Column
	private String picture_url;

}
