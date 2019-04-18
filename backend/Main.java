/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author acer
 */
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception{
			
		Hotel h0 = new Hotel("Lucknow" ,"Clarks Avadh");
                Hotel h1 = new Hotel("Lucknow" ,"La Place Portico");
                Hotel h2 = new Hotel("Lucknow" ,"Vivanta");
                Hotel h3 = new Hotel("Lucknow" ,"Comfort Inn");
                Hotel h4 = new Hotel("Lucknow" ,"Manglam");
                Hotel h5 = new Hotel("Lucknow" ,"Golden Tulip");
                Hotel h6 = new Hotel("Lucknow" ,"Hotel Deep Palace");
                Hotel h7 = new Hotel("Lucknow" ,"India Awadh");
                Hotel h8 = new Hotel("Lucknow" ,"Levana");
                Hotel h9 = new Hotel("Lucknow" ,"Sapna Clarks Inn");
                
                
                
		User u= new User("Satvik","top");
			
		if(User.validateUser("Satvik","top"))
		{	User uObj ;
			uObj= User.sendObject("Satvik");
			uObj.makeBook(1,"Prak", 3, "Hyderabad", "abcHotel", RoomType.SINGLE, 3, new Date(2018,10,01), new Date(2018,10,05),"123", null);
			// System.out.println("hooray!!") ; 
		}
                
                Object obj[][] = returnBookings("Satvik") ; 
                for(int i=0 ;i<obj.length ;i++){
                    for(int j=0;j<obj[i].length;j++){
                    
                         System.out.println(obj[i][j]);
                    
                    }
                    
                }
			
	}
	
	public static boolean validate(String a,String b)
	{
		User uObj=null;
		System.out.println("Strings are:"+a+","+b);
		if(User.validateUser(a,b))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public static Object[][] returnBookings(String usrName) 
	{
		User usr=User.sendObject(usrName);
		
		return usr.PBookings();
	}
	
}

