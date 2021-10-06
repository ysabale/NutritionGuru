package com.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutrition.model.LoginDetails;

<<<<<<< HEAD

=======
>>>>>>> b9d8f793d11dfa9cce97cf702482c5b929659409
@Repository
public interface LoginRepository extends JpaRepository<LoginDetails, Long>{
	LoginDetails findByEmailId(String emailId);
}
