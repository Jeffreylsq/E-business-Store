package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIoException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.ex.InsertException;
import cn.tedu.store.ex.PasswordNotMatchException;
import cn.tedu.store.ex.Service;
import cn.tedu.store.ex.UpdateException;
import cn.tedu.store.ex.UserNotFoundException;
import cn.tedu.store.ex.UsernameDuplicateException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.util.JsonResult;

/**
 * 控制器类的基类
 * @author soft01
 *
 */
public abstract class BaseController {

	public static final Integer SUCCESS = 2000;
	  @ExceptionHandler({Service.class, FileUploadException.class})
	  public JsonResult<Void> handleException(Throwable e){
		  
		  JsonResult<Void> jr = new JsonResult<Void>();
		  jr.setMessage(e.getMessage());
		  
		  if(e instanceof InsertException ) {
			  jr.setStatus(5000);
		  }else if(e instanceof UsernameDuplicateException){
			  jr.setStatus(4000);
		  }else if(e instanceof UserNotFoundException) {
			  jr.setStatus(4001);
		  }else if(e instanceof PasswordNotMatchException) {
		     jr.setStatus(4002);
		  }else if(e instanceof AddressCountLimitException) {
			  jr.setStatus(4003);
		  }else if(e instanceof UpdateException) {
		     jr.setStatus(5001);   
		  }else if(e instanceof FileEmptyException ) {
			  jr.setStatus(5002);
		  }else if(e instanceof FileSizeException) {
			  jr.setStatus(5003);
		  }else if(e instanceof FileTypeException) {
			  jr.setStatus(5004);
		  }else if(e instanceof FileIoException) {
			  jr.setStatus(5005);
		  }else if(e instanceof FileUploadStateException) {
			  jr.setStatus(5006);
		  }
		  return jr;
	  }
	  
	  /**
	   * 从Session中获取当前登录的用户的id
	   * @param session
	   * @return 当前登录的用户的id
	   */
	  
	  protected final Integer getUidFromSession(HttpSession session) {
		  
		  return Integer.valueOf(session.getAttribute("uid").toString());
	  }
	  
	  /**
	   * 从Session中获取当前登录的用户名
	   * @param session
	   * @return 当前登录的用户名
	   */
	  protected final String getUsernameFromSession(HttpSession session) {
	      return session.getAttribute("username").toString();
	  }
	  
}
