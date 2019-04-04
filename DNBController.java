package com.dnb.controller;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dnb.model.CustomerDetails;
import com.dnb.serviceimpl.CustomerDetailsImpl;

@Controller
@RequestMapping("/customer")
public class DNBController {
	@Autowired
	private CustomerDetailsImpl customerDetailsImpl;	
	
	@RequestMapping(value="/customerDetails", method = RequestMethod.GET)
	public @ResponseBody String getCustomerDetails(@FormParam("name") String name,@FormParam("phoneNo") String phoneNo){
		System.out.println("inside customer details");	
		//String results = " Spring boot application";
		CustomerDetails simpleModel1 = null;
		try {
		simpleModel1= customerDetailsImpl.getCustomerDetails(name,phoneNo);
		} catch (Exception e){
			System.out.println("Exception"+e);
		}
		
		String results = null;
		if(simpleModel1 !=null) {		
			results = "ID = "+simpleModel1.getId();
			results = results + " Name = "+simpleModel1.getName();
			results = results + " Phone = "+simpleModel1.getPhoneNumber();
			results = results + " Address = "+simpleModel1.getAddress();
			System.out.println("Results"+results);	
				
		}else {
			results = "Customer name "+name +" not found in DNB ";
		}
		
	return results;
	}
	@RequestMapping(value="/customerAddDetails", method = RequestMethod.GET)
	public  @ResponseBody String getCustomerAddDetails(@FormParam("custid") Integer custid, @FormParam("name") String name,@FormParam("phoneNo") String phoneNo,@FormParam("address") String address){
		System.out.println("inside customer details"+custid);
		System.out.println("inside customer details"+name);
		System.out.println("inside customer details"+phoneNo);
		System.out.println("inside customer details"+address);
		//String name = "Senthil";
		CustomerDetails customerDetailsModel = new CustomerDetails();
		customerDetailsModel.setId(custid);
		customerDetailsModel.setName(name);
		customerDetailsModel.setPhoneNumber(phoneNo);
		customerDetailsModel.setAddress(address);
		
		String message = customerDetailsImpl.addCustomerDetails(customerDetailsModel);
		
	return message;
	}
	@RequestMapping(value="/customerDeleteDetails", method = RequestMethod.GET)
	public  @ResponseBody String getCustomerAddDetails(@FormParam("custid") Integer custid){
		System.out.println("inside customer details"+custid);	
	
		
		String message = customerDetailsImpl.deleteCustomerDetails(custid);
		
	return message;
	}

}
