package com.mfpe.memberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.mfpe.memberService.model.Bills;
import com.mfpe.memberService.repository.BillsRepo;


@SpringBootApplication
@EnableFeignClients
public class MemberServiceApplication implements CommandLineRunner{
	
	@Autowired
	BillsRepo brepo;


	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}
public void run(String... args) throws Exception{
		
	Bills b1=new Bills("m1",1,"2020-06-09", 10000, 800, "2021-12-01");
	Bills b2=new Bills("m2",2, "2019-12-10", 50500, 6500, "2021-12-10");
	Bills b3=new Bills("m3", 4,"2021-07-20", 12000, 0, "2022-07-30");
	Bills b4=new Bills("m4", 2,"2021-01-01", 8000, 0, "2021-11-29");
	Bills b5=new Bills("m5", 1,"2020-01-05", 25000, 4000, "2021-12-12");
	Bills b6=new Bills("m6", 3,"2020-01-05", 25000, 4000, "2021-4-12");

	brepo.save(b1);
	brepo.save(b2);
	brepo.save(b3);
	brepo.save(b4);
	brepo.save(b5);
	brepo.save(b6);
	

}
}
