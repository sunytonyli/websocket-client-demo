package com.tyrus.clientdemo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.apache.commons.codec.binary.Base64;
import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		 ClientManager client = ClientManager.createClient();
	        try {
	        	
	        	String remoteUri = "ws://ws.yeepbank.xyz/notice/1231?userId=XaOOz9/5MyE=";
	        	String localUri = "ws://localhost:8080/mservice/notice/357143043265997?userId=";
	        	
	        	System.out.println(Base64.encodeBase64("XaOOz9/xxx5MyE=".getBytes()));
	        	URI uriOne = new URI("ws", null, "//ws.yeepbank.xyz/notice/357143043265997", null, null);
	        	URI remoteKUri = new URI("ws://172.31.50.133:8080/notice/357143043265997?userId=&wb=1");
	        	//URI uriTwo = new URI("ws://localhost:8080/tyrusdemo/users?userId=XaOOz9/5MyE=");
	        	Session session = client.connectToServer(TyrusClientEndpoint.class, remoteKUri);
	            
	   		 	session.getBasicRemote().sendText("ssss");
	          //  session.close();
	  
	        } catch (DeploymentException | URISyntaxException | IOException e) {
	            throw new RuntimeException(e);
	        }
		
		
		return "home";
	}
	
}
