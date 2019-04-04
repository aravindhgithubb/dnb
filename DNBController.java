package com.dnb.controller;

import javax.ws.rs.FormParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dnb.model.CustomerDetails;
import com.dnb.serviceimpl.CustomerDetailsImpl;

@Controller
//@RequestMapping("/customer")
//@SessionAttributes("name")
public class DNBController {
	@Autowired
	private CustomerDetailsImpl customerDetailsImpl;	
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	
	public  String getCustomerDetails(Model model,@FormParam("name") String name,@FormParam("phoneNo") String phoneNo){
		System.out.println("inside customer details");	
		CustomerDetails cusPo=new CustomerDetails();
		try {
			cusPo= customerDetailsImpl.getCustomerDetails(name,phoneNo);
			if(cusPo !=null) {
             model.addAttribute("fname",cusPo.getName());
             model.addAttribute("address",cusPo.getAddress());
             model.addAttribute("creation",cusPo.getCreationdate());
             model.addAttribute("phone",cusPo.getPhone());
             model.addAttribute("post",cusPo.getAccom().getPost());
             model.addAttribute("source",cusPo.getOwns().getSource());
             
           /*  model.addAttribute("fname",cusPo.getName());
             model.addAttribute("address",cusPo.getAddress());
             model.addAttribute("creation",cusPo.getCreationdate());
             model.addAttribute("phone",cusPo.getPhone());
             model.addAttribute("post",cusPo.getAccom().getPost());
             model.addAttribute("source",cusPo.getOwns().getSource());*/
				
				/*model.addAttribute("Name",cusPo.getCustDet().getName());
			model.addAttribute("Phone",cusPo.getCustDet().getPhone());
			model.addAttribute("Address",cusPo.getCustDet().getAddress());
			model.addAttribute("creationdate",cusPo.getCustDet().getCreationdate());
		return "model";*/
		/*	model.setViewName("search");
				model.addObject("search",cusPo);
				return model;*/
				return "newResult";
          
 }} catch (Exception e){
			System.out.println("Exception"+e);
		}
		return "search";
	}
	
	
	@RequestMapping(value="/customerAddDetails", method = RequestMethod.GET)
	public  @ResponseBody String getCustomerAddDetails(@FormParam("custid") Integer custid, @FormParam("name") String name,@FormParam("phoneNo") String phoneNo,@FormParam("address") String address){
		System.out.println("inside customer details"+custid);
		System.out.println("inside customer details"+name);
		System.out.println("inside customer details"+phoneNo);
		System.out.println("inside customer details"+address);
        CustomerDetails customerDetailsModel = new CustomerDetails();
		customerDetailsModel.setId(custid);
		customerDetailsModel.setName(name);
		customerDetailsModel.setPhone(phoneNo);
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
