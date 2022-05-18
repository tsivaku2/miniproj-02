package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entities.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {
	
	public UserAccountEntity findByEmailAndPassword(String email, String pwd);
	
	public UserAccountEntity findByEmail(String emailId);

}
