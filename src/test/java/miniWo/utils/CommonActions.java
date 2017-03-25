package miniWo.utils;

import java.util.Random;

public class CommonActions {
	

	public int getsubstring(String Value, String LeftB, String RightB){
		int IndexLeftB=Value.indexOf(LeftB);
		int IndexRightB=Value.indexOf(RightB);
		String Subvalue =Value.substring(IndexLeftB+1, IndexRightB);
		int finalvalue =Integer.parseInt(Subvalue.trim());
		return finalvalue;
		}
	
	public boolean CheckResultValue(int Value){
		boolean flag = false;
		if( Value > 0 ) {      
	     flag=true;
	      }else {       
	     flag=false;
	      }
		return flag;
        }
	
	public String getathentication(String username, String password, String url){
		String baseUrl="https://"+username+":"+password+"@"+url;
		return baseUrl;
		}
	
	public String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
	
	


