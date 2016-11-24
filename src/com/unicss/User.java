package com.unicss;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("user")
public class User {
		private String userName;  
	    private String passWord;  
	    private String registeredTime;  
	  
	    public User() {  
	    }  
	    public User(String userName, String passWord, String registeredTime) {  
	        this.userName = userName;  
	        this.passWord = passWord;  
	        this.registeredTime = registeredTime;  
	    }  
	  
	    public String getUserName() {  
	        return userName;  
	    }  
	  
	    public void setUserName(String userName) {  
	        this.userName = userName;  
	    }  
	  
	    public String getPassWord() {  
	        return passWord;  
	    }  
	  
	    public void setPassWord(String passWord) {  
	        this.passWord = passWord;  
	    }  
	  
	    public String getRegisteredTime() {  
	        return registeredTime;  
	    }  
	  
	    public void setRegisteredTime(String registeredTime) {  
	        this.registeredTime = registeredTime;  
	    }  
}
