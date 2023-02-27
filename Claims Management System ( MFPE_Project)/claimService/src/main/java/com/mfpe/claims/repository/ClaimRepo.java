package com.mfpe.claims.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.claims.model.Claim;

@Repository
@Transactional
public interface ClaimRepo extends JpaRepository<Claim,String> {

	Claim findByClaimId(String id);

}
