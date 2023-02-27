package com.cognizant.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.mfpe.model.*;


public interface MemberPolicyRepository extends JpaRepository<MemberPolicy,String> {
	
}
