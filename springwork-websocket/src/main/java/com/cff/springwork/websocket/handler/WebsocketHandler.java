package com.cff.springwork.websocket.handler;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;  
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.cff.springwork.websocket.memory.WebSocketUser;
  
public class WebsocketHandler extends TextWebSocketHandler {  
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public static WebSocketUser users = new WebSocketUser();
	public WebsocketHandler(){
		
	}
	
	
    @Override  
    protected void handleTextMessage(WebSocketSession session,  
            TextMessage message) throws Exception {  
    	logger.info("websocketmsg:{}",message.toString()); 
        super.handleTextMessage(session, message);  
        String msg = message.getPayload();
        logger.info("websocketmsg1:{}",msg); 
        if(StringUtils.isNotEmpty(msg)){
        	String param[] = msg.split("\\|");
        	String userName = (String)session.getAttributes().get("userName");
        	sendMessageToUser(param[1], new TextMessage(param[0]+"|"+userName+"|"+param[2]));
        }
//        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
//        session.sendMessage(returnMessage);  
    }

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userName = (String)session.getAttributes().get("userName");
		String msgType="0000";
		if(userName == null || "".equals(userName)){
			long id = users.getWebsocketId();
			logger.info("random UserId:{}",id); 
			users.putUserNameAndWebSocketSession(id+"", session);
			
			session.sendMessage(new TextMessage(msgType+"|"+ id));
		}else{
			logger.info("UserName:{}",userName); 
			//users.putNickNameAndWebSocketSession(userInfo.getNickName(), session);
			users.putUserNameAndWebSocketSession(userName, session);
			session.sendMessage(new TextMessage(msgType+"|"+userName));
		}
		

		List<TextMessage> message = WebsocketHandler.getOfflineMsg(userName);
	    if( message !=null && message.size()!=0){
	    	for(int i = 0; i < message.size(); i++){
	    		WebsocketHandler.sendMessageToUser(userName, message.get(i));
	    	}
	    }
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		
//		super.afterConnectionClosed(session, status);
		users.removeWebSocketSession(session);
		
	}
    
	/**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public static void sendMessageToUser(String userName, TextMessage message) {
    	List<WebSocketSession> webUsers = users.getWebSocketSessionWithUserName(userName);
    	System.out.println(webUsers);
    	System.out.println(userName);
    	if (webUsers == null || webUsers.size() == 0){
    		users.putOfflineMsg(userName, message);
    		return;
    	}
    	for(int i =0; i < webUsers.size(); i++){
	    	WebSocketSession user = webUsers.get(i);
	    	System.out.println(user);
	    	
	        try {
	            if (user.isOpen()) {
	            	user.sendMessage(message);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
    	}
    }
    
    
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public static void sendBinaryMessageToUser(String userName, BinaryMessage message) {
    	List<WebSocketSession> webUsers = users.getWebSocketSessionWithUserName(userName);
    	System.out.println(webUsers);
    	System.out.println(userName);
    	if (webUsers == null || webUsers.size() == 0){
    		//users.putOfflineMsg(userName, message);
    		return;
    	}
    	for(int i =0; i < webUsers.size(); i++){
	    	WebSocketSession user = webUsers.get(i);
	    	System.out.println(user);
	    	
	        try {
	            if (user.isOpen()) {
	            	user.sendMessage(message);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
    	}
    }
    
    public static List<TextMessage> getOfflineMsg(String userName){
    	return users.getOfflineMsgWithUserName(userName);
    }
}  
