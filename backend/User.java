/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.ZoneId;

 

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6215571436206042927L;
	String username;
	String password;
	String name;
	Date dateOfBirth;
	String address;
	String emailId;
	public  int count =0 ; 
	ArrayList<Booking> bList=new ArrayList<Booking>() ;
        ArrayList<Booking> bList2=new ArrayList<Booking>() ;
    
	public User(String username , String password)
	{	
		
		this.username=username;
		this.password=password;
		File f=null;
		
		try {
			f= new File("D:\\project\\user");
			f.mkdirs();
		}catch(SecurityException e)
		{
			e.printStackTrace();
		}		
			
		try {
			File f2= new File("D:\\project\\user\\"+username+".txt");
			FileOutputStream fos= new FileOutputStream(f2);	
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(this);
			fos.close();
			oos.close();
		}catch(FileNotFoundException e)
			{	
				e.printStackTrace();
				System.out.println("Error opening file:"+"D:\\project\\user\\"+username+".txt");
			}
                
                catch(IOException e){
                    e.printStackTrace(); 
                }

	}
	
	public void fillDetails(String name,Date dateOfBirth,String address,String emailId)
	{
		this.name=name;
		this.dateOfBirth=dateOfBirth;
		this.address=address;
		this.emailId=emailId;
		
		try {
			File f2= new File("D:\\project\\user\\"+username+".txt");
			FileOutputStream fos= new FileOutputStream(f2);	
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(this);
			fos.close();
			oos.close();
		}catch(FileNotFoundException e)
			{	
				e.printStackTrace();
				System.out.println("Error opening file:"+"D:\\project\\user\\"+username+".txt");
			}
		catch(IOException e)
		{
			System.out.println("Error in making file");
		}
		
	}
	
	public static boolean validateUser(String username,String password) 
	{
		
		File f2= new File("D:\\project\\user\\"+username+".txt");
		
		try {
			if(f2.exists())
			{
				FileInputStream fin=new FileInputStream(f2);
				ObjectInputStream oos= new ObjectInputStream(fin);
				User u=(User)oos.readObject();
				fin.close();
				oos.close();
				if(u.password.equals(password))
				{
					return true;
				}
				else 
				{
					System.out.println("Incorrect password");
					return false;
				}

			}
			else 
			{
				System.out.println("Invalid username");
				return false;
			}
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static User sendObject(String username)
	{
		try {
		File f= new File("D:\\project\\user\\"+username+".txt");
		FileInputStream fout=new FileInputStream(f);
		ObjectInputStream oos=new ObjectInputStream(fout);
		User temp = (User)oos.readObject();
		oos.close();
		fout.close();
		return temp;
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	
      public  void makeBook(int refId,String bookingName,int noOfPeople,String city,String hName,RoomType rType,int noOfRooms,Date checkIn,Date checkOut,String aadharNo,String panNo)
	{	
		Booking b=new Booking(refId, bookingName, noOfPeople, city, hName, rType,noOfRooms,checkIn,checkOut,aadharNo,panNo);
		bList2.add(b);
		
		try {
			File f2= new File("D:\\project\\user\\"+username+".txt");
			FileOutputStream fos= new FileOutputStream(f2);	
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(this);
			fos.close();
			oos.close();
		}catch(FileNotFoundException e)
			{	
				e.printStackTrace();
				System.out.println("Error opening file:"+"D:\\project\\user\\"+username+".txt");
			}
		catch(IOException e)
		{
			System.out.println("Error in making file");
			e.printStackTrace();
		}
		

	}
	
	Object[][] PBookings()
	{    Object obj[][]=new Object[bList2.size()][]; 
 		for(int i=0;i<bList2.size();i++)
		{
			Booking temp =(Booking)bList2.get(i);
			obj[i] = temp.printBooking();
		}
                
		return obj;
	}
        
    public   Booking makeBooking(int refId,String bookingName,int noOfPeople,String city,String hName,RoomType rType,int noOfRooms,LocalDate checkIn,LocalDate checkOut,String aadharNo,String panNo)
	{	
		Booking b=new Booking(refId, bookingName, noOfPeople, city, hName, rType,noOfRooms, Date.from(checkIn.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), Date.from(checkOut.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),aadharNo,null);
	       bList.add(b);
		
		try {
			File f2= new File("D:\\project\\user\\"+username+".txt");
			FileOutputStream fos= new FileOutputStream(f2);	
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(this);
			fos.close();
			oos.close();
		}catch(FileNotFoundException e)
			{	
				e.printStackTrace();
				System.out.println("Error opening file:"+"D:\\project\\user\\"+username+".txt");
			}
		catch(IOException e)
		{
			System.out.println("Error in making file");
			e.printStackTrace();
		}
		
		String j=null;
		try {
			
			if(rType==RoomType.SINGLE)
			{
				j="SINGLE";
			}
			
			if(rType==RoomType.DOUBLE)
			{
				j="DOUBLE";
			}
			
			if(rType==RoomType.LUXURY)
			{
				j="LUXURY";
			}
			
			DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 		
			String s = checkIn.format(f2);
			String e = checkOut.format(f2);
			System.out.println("printing s:"+s);
			System.out.println("printing e:"+e);
		
		File f3=Show.findRooms2(city,hName,j,s,e);
                
                if(f3==null)
                {
                    File og = new File("D:\\project\\cities\\"+city+"\\"+hName); // s is check in date and e is check out date yyyy-mm-dd 
		    File[] in  = og.listFiles();
                     HotelRoom.p=0 ;  HotelRoom.q=in.length/3  ; HotelRoom.r=2*in.length/3 ;
                      if(j.equals("SINGLE"))
                      {  
                        HotelRoom.p=HotelRoom.p%(in.length/3)+1 ; 
                      
                    File file = new File("D:\\project\\cities\\"+city+"\\"+hName+"\\room"+HotelRoom.p+".txt") ;
                    FileInputStream fos =new FileInputStream(file) ; 
                    ObjectInputStream oos =new ObjectInputStream(fos) ; 
                    HotelRoom temp = (HotelRoom)oos.readObject() ;
                    
                       temp.tryFilling(checkIn.toString(), checkOut.toString(), b);
                    
              	FileOutputStream fin2 = new FileOutputStream(file);
		ObjectOutputStream oos2 =  new ObjectOutputStream(fin2);
		oos2.writeObject(temp);
                HotelRoom.p++ ; 
                
                    return b ; 
                    
                      }
                      else if(j.equals("SINGLE"))
                      {
                       HotelRoom.q=(HotelRoom.q%(in.length/3))+1 + in.length/3 ; 
                      
                    File file = new File("D:\\project\\cities\\"+city+"\\"+hName+"\\room"+HotelRoom.q+".txt") ;
                    FileInputStream fos =new FileInputStream(file) ; 
                    ObjectInputStream oos =new ObjectInputStream(fos) ; 
                    HotelRoom temp = (HotelRoom)oos.readObject() ; 
                    temp.tryFilling(checkIn.toString(), checkOut.toString(), b);
                     
               	FileOutputStream fin2 = new FileOutputStream(file);
		ObjectOutputStream oos2 =  new ObjectOutputStream(fin2);
		oos2.writeObject(temp);                     
                HotelRoom.q++ ;
                    
                    
                    return b ; 
                      }
                      
                       else if(j.equals("SINGLE"))
                      {
                      
                      
                       HotelRoom.r=HotelRoom.r%(in.length/3)+1 + 2*in.length/3 ; 
                      
                    File file = new File("D:\\project\\cities\\"+city+"\\"+hName+"\\room"+HotelRoom.r+".txt") ;
                    FileInputStream fos =new FileInputStream(file) ; 
                    ObjectInputStream oos =new ObjectInputStream(fos) ; 
                    HotelRoom temp = (HotelRoom)oos.readObject() ; 
                    temp.tryFilling(checkIn.toString(), checkOut.toString(), b);
                     
                        FileOutputStream fin2 = new FileOutputStream(file);
                        ObjectOutputStream oos2 =  new ObjectOutputStream(fin2);
                        oos2.writeObject(temp);      
                         HotelRoom.r++ ;
                    
                    return b ; 
                      }
                                   
                }
                
		FileInputStream fin = new FileInputStream(f3);
		ObjectInputStream oos = new ObjectInputStream(fin);
		HotelRoom r=(HotelRoom)oos.readObject();
		

		r.tryFilling(s,e,b);
		FileOutputStream fin2 = new FileOutputStream(f3);
		ObjectOutputStream oos2 =  new ObjectOutputStream(fin2);
		oos2.writeObject(r);	
		return b;
		
		}catch(FileNotFoundException f)
		{
			f.printStackTrace();
			return b;
		}
		catch(ClassNotFoundException f)
		{
			f.printStackTrace();
			return b;
		}
		catch(IOException f)
		{
			f.printStackTrace();
			return b;
		}catch(Exception e) 
		{
			e.printStackTrace();
			return b;
		}
	}  
}

