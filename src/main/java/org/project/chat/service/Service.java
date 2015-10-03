package org.project.chat.service;

import java.util.ArrayList;
import java.util.List;

import org.project.chat.database.Database;
import org.project.chat.models.Accounts;
import org.project.chat.models.Messages;

public class Service {
	
	public String login(String username, String password){
		String message = null;
		
		try {
			message = Database.login(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String sendMessage(String message, String sender_id, String receiver_id){
		try{
			Database.insert(message, Database.getId(sender_id), Database.getId(receiver_id));
			return "Sent!";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Messages> getMessage(String receiver_id){
		try {
			return Database.getMessage(Database.getId(receiver_id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Messages getLatestMessage(String time_received, String receiver_id){
		Messages message = new Messages();
		try {
			message.setMessage(Database.getLatestMessage(time_received, Database.getId(receiver_id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*if(message.getMessage() == null){
			message.setMessage(null);
		}*/
		
		System.out.println("message: " + message.getMessage() + " message");
		
		return message;
	}
	
	public List<Accounts> getReceiverService(){
		
		List<Accounts> account = new ArrayList<Accounts>();
		
		try {
			account = Database.getReceiver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	public void setOnlineService(String username){
		try {
			Database.setOnline(username);
			System.out.println("inserted to online!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Accounts isOnlineService(String username){
		Accounts account = new Accounts();
		
		try {
			account.setIsOnline(Database.isOnline(username));
			System.out.println(account.getIsOnline() + " is");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	public Accounts logoutService(String username){
		
		Accounts account = new Accounts();
		
		try {
			Database.logout(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	/*private String toJson(Object object) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(object);
	}*/
	
}
