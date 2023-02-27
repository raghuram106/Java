package com.mfpe.memberService.service;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.mfpe.memberService.model.Bills;
import com.mfpe.memberService.repository.BillsRepo;

@SpringBootTest
public class ClaimStatusAndDetailsTest {

	@MockBean
    private BillsRepo billsRepo;
	
	@InjectMocks
	ClaimStatusAndDetails claimStatusAndDetails;

    	
    @Test
    //Checking if [ClaimStatusAndDetailsTest] class is loading or not
     void processingRequestIsLoadedOrNot() {
        assertThat(claimStatusAndDetails).isNotNull();
    }

    //Checking if Get Bills Method is working or not
    @Test
    void testGetBillsMethod() {
    	   	
    	Bills bill = new Bills("221",1,"2021-05-04",65600,1200,"2020-06-04");
    	billsRepo.save(bill);
        assertThat(billsRepo.findById("221")).isNotNull();
    }
    
  
   
    
    
}
    