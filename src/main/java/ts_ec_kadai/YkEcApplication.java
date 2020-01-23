package ts_ec_kadai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("ts_ec_kadai.entity")
public class YkEcApplication {

	public static void main(String[] args) {
		SpringApplication.run(YkEcApplication.class, args);
	}

}
