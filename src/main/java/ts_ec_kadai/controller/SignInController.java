package ts_ec_kadai.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ts_ec_kadai.config.LoginUser;

@Controller
@RequestMapping(value="/signin")
public class SignInController {
	
	@Autowired
	MessageSource message;
	
	@GetMapping(value="")
	public String index(@AuthenticationPrincipal LoginUser principal,Model model) {
		
		
		if(principal == null) {
			return "signin";
		}else {
			return "redirect:/";
		}
	}
	
	@PostMapping(value="/fail")
	public String loginFailed(@ModelAttribute("username")String user,
			@RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION,required = false)Exception exception,
			Model model) {

		model.addAttribute("username",user);
		
		if(exception != null) {
			model.addAttribute("message",message.getMessage("message.login.failed",null,Locale.JAPAN));
		}
		return "signin";
	}
	
	

}
