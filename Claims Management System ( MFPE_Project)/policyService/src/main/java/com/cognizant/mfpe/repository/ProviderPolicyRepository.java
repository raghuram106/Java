package com.cognizant.mfpe.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognizant.mfpe.model.*;


public interface ProviderPolicyRepository extends JpaRepository<ProviderPolicy,String>{
	

	@Query(value="select * from PROVIDERPOLICY p where p.PID=:pId order by p.location",nativeQuery = true)
	public List<ProviderPolicy> find(@Param("pId") int pId);


}
