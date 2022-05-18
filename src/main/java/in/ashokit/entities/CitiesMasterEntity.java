package in.ashokit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "CITIES_MASTER")
@Data
public class CitiesMasterEntity {
	
	@Id
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	

}
