package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
 
         @Autowired private IAddressService addressService;	   
	     
          @RequestMapping("addnew")
          public JsonResult<Void> addnew(Address address ,HttpSession session){
        	  
        	  //Session 中获取uid和username
        	  Integer uid = getUidFromSession(session);
        	  String username = getUsernameFromSession(session);
        	  //调用业务层对象执行增加
        	  addressService.addNew(address, uid, username);
        	  //响应成功
        	  return new JsonResult<Void>(SUCCESS);
          }
	
}
