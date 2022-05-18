package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.CitiesMasterEntity;
import in.ashokit.entities.CountryMasterEntity;

public interface CityRepository extends JpaRepository<CitiesMasterEntity, Integer> {
	
	public List<CitiesMasterEntity> findBycityId(Integer stateId);
	

}
