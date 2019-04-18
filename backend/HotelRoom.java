package backend;

import  java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class HotelRoom  implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8164658239400455114L ;
	int roomId;
	boolean vacant;
	RoomType type;
       public  int price; 
       
       static public int  p,q,r  ; 
        
	HotelRoom(int roomId,RoomType type,int p)
	{
		vacant=true       ;
		this.type=type     ;
		this.roomId=roomId ;
               
                 price=p ; 
                
		
	}
        
        
      List<LocalDate> full = new ArrayList<LocalDate>();
      List<WLmem> Wlist = new ArrayList<WLmem>();
        
    public static List <LocalDate> getDates(String s , String e) {
		
		
		LocalDate start =  LocalDate.parse(s);
		LocalDate end =  LocalDate.parse(e);
		
		List <LocalDate> dates = new ArrayList<LocalDate>();
		while(!start.isAfter(end)) {			
			dates.add(start);
			start = start.plusDays(1);
					                }				
		return dates;
       }



    public void tryFilling(String s,String e,Booking c)
	{
		
		int p = 0;
		try { 
				if(LocalDate.parse(s).isAfter(LocalDate.parse(e)))				
					p=10;				
		}catch(DateTimeParseException k){
				System.out.println("enter a valid date");
				return;	
		}
		List<LocalDate> buff = HotelRoom.getDates(s, e);		
		for(LocalDate a : buff) 
		{
			if(p==-1) 
				break;		
			if(p==1) 
				break;
			p=0;
			                           
			for(LocalDate b : this.full) 
			{
				if(a.isBefore(LocalDate.now())) 
				{
					System.out.println("one of the days has passed");
					p--;
					break;
				}
			
				if(a.equals(b))
				{
					System.out.println("you,re on the waiting list");
					this.addtoWL(c);
					p++;
					break;
				}
			}
		}
			if(p==0) 
			{
				
				this.full.addAll(buff);				
				System.out.println("your booking was successful");
				System.out.println("the new list of days without vacancies is:");
			}
	
			
			//finally the list of days when hotelRoom isn't vacant will be printed
			for(LocalDate a : this.full) 
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
				String f = a.format(formatter);
				System.out.println(f);			
			}
		
	
	}
        
      
    void addtoWL(Booking c)
	{		
		WLmem temp = new WLmem();
		temp.start = Instant.ofEpochMilli(c.checkIn.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		temp.end   = Instant.ofEpochMilli(c.checkOut.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		temp.username = c.username;
		temp.bookingname = c.bookingName;
		temp.aadharNo = c.aadharNo;
		temp.panNo = c.panNo;
		temp.noOfPeople = c.noOfPeople;
		temp.hName = c.hName;
		temp.city = c.city;
		temp.hroom = c.hroom;
		temp.refId =c.refId;
		Wlist.add(temp);
		
	}
    
    	void flushWL() 
	{
		
		int k;	
		for(int i = 0 ;i <  Wlist.size();i++)
		{
			WLmem a = Wlist.get(i);
			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String s = a.start.format(f);
			String e = a.end.format(f);
			k = 0;
			for(LocalDate b: this.full)
			{
				for(LocalDate o : getDates(s,e))
				{
					if (o.equals(b))
					{
						k++;
						break;
					}
					
					if(k !=0) 
					{
						break;
				    }		
				
				}
				
				
			}	
				
				
			if(k==0)
			{	
				
				WLmem wlmem=Wlist.get(i);
				User u1=User.sendObject(wlmem.username);
				Wlist.remove(i);
				if(u1.bList.size()!=0)
					u1.bList.remove(wlmem.refId-1);
				//need to remove old booking from bList as well								
				try{
					
					Booking b=u1.makeBooking(1, a.bookingname, a.noOfPeople,a.city, a.hName, this.type, 1, a.start, a.end, a.aadharNo, a.panNo);					
					tryFilling(s,e,b);
					FileOutputStream finn =new FileOutputStream("D:\\project\\user\\"+a.username+".txt");
					ObjectOutputStream oooo =new ObjectOutputStream(finn);
					oooo.writeObject(u1);									
				}catch(IOException z)
				{
					z.printStackTrace();				
				}
				//make a booking for the user who has been removed from the waiting list and use the try filling function to allot him the room
			}
		}
	}
    
    
    
}
