package com.tyrus.clientdemo;


/**
 * @author songyu.li@yeepay.com
 * @createDatetime 2016年5月29日 下午8:59:38
 */

public class WebSocketServerTest {

	 public static void main(String[] args) {
		 String ss = null;
		 if(ss != null && ss.equals("")){
			 System.out.println("=============");
		 }
	 }
	       
	/*  
	 public static void runServer() {
		 Server server = new Server("localhost", 8025, "/websockets", null, TyrusServerEndpoint.class);
		 try {
	            server.start();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	            System.out.print("Please press a key to stop the server.");
	            reader.readLine();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } finally {
	            server.stop();
	        }
	    }*/
	    
}
