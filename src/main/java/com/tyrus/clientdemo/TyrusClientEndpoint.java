package com.tyrus.clientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.glassfish.tyrus.client.ClientManager;



/**
 * @author songyu.li@yeepay.com
 * @createDatetime 2016年5月29日 下午8:57:43
 */

@ClientEndpoint
public class TyrusClientEndpoint {

	private Logger logger = Logger.getLogger(TyrusClientEndpoint.class);
	
	
	private static CountDownLatch latch;
	
	@OnOpen
    public void onOpen(Session session) {
        logger.info("Connected ... " + session.getId());
        try {
            session.getBasicRemote().sendText("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    @OnMessage
    public String onMessage(String message, Session session) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            logger.info("Received ...." + message);
            String userInput = bufferRead.readLine();
            return userInput;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }
    
    public static void main(String[] args) {
    	
       latch = new CountDownLatch(1);
  
       ClientManager client = ClientManager.createClient();
        try {
        	
        	String remoteUri = "ws://ws.yeepbank.xyz/notice/1231?userId=XaOOz9/5MyE=";
        	String localUri = "ws://localhost:8080/mservice/notice/357143043265997?userId=";
        	
        	System.out.println(Base64.encodeBase64("XaOOz9/xxx5MyE=".getBytes()));
        	URI uriOne = new URI("ws", null, "//ws.yeepbank.xyz/notice/357143043265997", null, null);
        	URI remoteKUri = new URI("ws://ypns.yeepbank.com/notice/357143043265997?userId=&wb=1");
        	//URI uriTwo = new URI("ws://localhost:8080/tyrusdemo/users?userId=XaOOz9/5MyE=");
        	Session session = client.connectToServer(TyrusClientEndpoint.class, new URI(remoteUri));
            
   		 	//session.getBasicRemote().sendText("ssss");
          //  session.close();
            latch.await();
  
        } catch (DeploymentException | URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
