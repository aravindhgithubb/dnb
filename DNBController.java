package com.dnb.controller;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dnb.model.CustomerDetails;
import com.dnb.serviceimpl.CustomerDetailsImpl;

@Controller
//@RequestMapping("/customer")
//@SessionAttributes("name")
public class DNBController {
	@Autowired
	private CustomerDetailsImpl customerDetailsImpl;	
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public  ModelAndView getCustomerDetail(ModelMap mod)
	{
		CustomerDetails cusPo=new CustomerDetails();
		mod.addAttribute(cusPo);
		return new ModelAndView("search","command",cusPo);
	}
	@RequestMapping(value="/newResult", method = RequestMethod.GET)
	public  ModelAndView getCustomerDetails(@ModelAttribute("cusPo") CustomerDetails cusPo,@FormParam("name") String name,@FormParam("phoneNo") String phoneNo,Model model,ModelMap m){
		System.out.println("inside customer details");	
		//CustomerDetails cusPo=new CustomerDetails();
		try {
			cusPo= customerDetailsImpl.getCustomerDetails(name,phoneNo);
			if(cusPo !=null) {
             model.addAttribute("fname",cusPo.getName());
             model.addAttribute("address",cusPo.getAddress());
             model.addAttribute("creation",cusPo.getCreationdate());
             model.addAttribute("phone",cusPo.getPhone());
             model.addAttribute("post",cusPo.getAccom().getPost());
             model.addAttribute("source",cusPo.getOwns().getSource());
             ModelAndView m1=new ModelAndView();
            // m.addAttribute("model", model);
             m1.setViewName("newResult");
             m1.addObject("model", model);
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
				return m1;
          
 }} catch (Exception e){
			System.out.println("Exception"+e);
		}
		return new ModelAndView("search");
	}
	 @RequestMapping(value="/searchKey", method = RequestMethod.GET)
     public CustomerDetails getCustomerDetail(@FormParam("name") String name) throws Exception
		{
		CustomerDetails cusPo=new CustomerDetails();
      try
		{
        cusPo = customerDetailsImpl.getCustomerDetailsKeySearch(name);
        if(cusPo !=null)
        {
		//String cus=customerDetailsImpl.getCustomerDetailsKeySearch(name);
		//m.addAttribute("name1",cusPo.getName());
	//	m.addAttribute(attributeName, attributeValue)
		return cusPo;
        }
		}
      catch(Exception e)
      {
    	  System.out.println(e);
      }
		return cusPo;
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
