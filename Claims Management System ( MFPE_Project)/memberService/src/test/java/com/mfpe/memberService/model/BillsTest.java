package com.mfpe.memberService.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BillsTest {

 
    Bills billsObj=new Bills();

    @Test
    //Checking if Bills class is loading or not
     void processingRequestIsLoadedOrNot() {
        assertThat(billsObj).isNotNull();
    }

    //Checking if Bills class is responding correctly or not
    @Test
     void testingBills() throws ParseException{
    	
        billsObj= new Bills("221",1, "2011-07-02",65600,1200,"2020-06-04");
        
        billsObj.setMemberId("121");
        billsObj.setPolicyId(1);
        billsObj.setLastPaidDate("2021-05-04");
        billsObj.setDueAmount(65600);
        billsObj.setLateCharge(1200);
        billsObj.setDueDate("2020-06-04");
        
        
        assertEquals("121",billsObj.getMemberId());
        assertEquals(1,billsObj.getPolicyId());
        assertEquals("2021-05-04",billsObj.getLastPaidDate());
        assertEquals(65600,billsObj.getDueAmount());
        assertEquals(1200,billsObj.getLateCharge());
        assertEquals("2020-06-04",billsObj.getDueDate());
     
    }
}
