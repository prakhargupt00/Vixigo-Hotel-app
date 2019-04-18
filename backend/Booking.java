/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author acer
 */
public class Booking implements Serializable
{
	/**
	 * 
	 */
         private static final long serialVersionUID = 187294921134069847L ; 
	int refId;
	String bookingName;
	int noOfPeople;
	String city;
	String hName;
	RoomType rType;
	int noOfRooms;
	Date checkIn;
	Date checkOut;
	String aadharNo;
        String panNo;
        String username ; 
        String hroom ; 
    
    
    
    Booking(int refId,String bookingName,int noOfPeople,String city,String hName,RoomType rType,int noOfRooms,Date checkIn,Date checkOut,String aadharNo,String panNo)
    {		
		this.refId=refId;
		this.bookingName=bookingName;
		this.noOfPeople=noOfPeople;
		this.city=city;
		this.hName=hName;
		this.rType=rType;
		this.noOfRooms=noOfRooms;
		this.checkIn=checkIn;
		this.checkOut=checkOut;
		this.aadharNo=aadharNo;
	    this.panNo=panNo;
	   	    
    }
    
    Object[] printBooking()
    {
    	Object obj[] = new Object[]{ refId, bookingName, noOfPeople, city, hName, rType, noOfRooms, checkIn, checkOut, aadharNo, panNo};
    	
    	/**
    	System.out.println(refId);
    	System.out.println(bookingName);
    	System.out.println(noOfPeople);
    	System.out.println(city);
    	System.out.println(hName);
    	System.out.println(rType);
    	System.out.println(noOfRooms);
    	System.out.println(checkIn);
    	System.out.println(checkOut);
    	System.out.println(aadharNo);
    	System.out.println(panNo);
    	*/
        
        return obj ; 
    }
}
