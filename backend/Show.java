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
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.List;
public class Show {

	
	
	public static String[] findHotels(String city ,String type)throws Exception  {
		
			File og = new File("D:\\project\\cities\\"+city);
			File[] in  = og.listFiles();
		        String b;
                        String[] h = new String[10];  
                        int i= 0 ; // h is for hotelnames 
                        
				
			//FileInputStream fis = new FileInputStream("D:\\project\\Cities\\" + city );
				
			//ObjectInputStream ois = new ObjectInputStream(fis);
			// HotelRoom hr = (HotelRoom)ois.readObject();
		
		        
			
                        for(File a : in) {
					
					b = a.getName();
					h[i]=b ;
                                        i++ ; 
		
		
				}
				
				//ois.close();
				//fis.close() 
        
        return h  ; 
        }
	
        
        public static int findRooms(String city,String hotel,String type,String s,String e) throws Exception {
		
            File og = new File("D:\\project\\cities\\"+city+"\\"+hotel); // s is check in date and e is check out date yyyy-mm-dd 
		
            File[] in  = og.listFiles();
            
		List<LocalDate> buff = HotelRoom.getDates(s, e);
		int q = 0;
		
		
		
		int p = 0;  // no of vacant rooms between those dates .. 
		for(File c : in) {
			q = 0;
			
			FileInputStream fis = new FileInputStream("D:\\project\\cities\\"+city+"\\"+hotel+"\\"+c.getName());
			
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			HotelRoom a = (HotelRoom)ois.readObject();
			ois.close();
			fis.close();
			for(LocalDate j : buff) {for(LocalDate b : a.full) {if(j.equals(b)) {q++;break;}}}
			
			if(type.equals("SINGLE")) {if(a.type == RoomType.SINGLE && q == 0) {p++;}}
			else if(type.equals("DOUBLE")) {if(a.type == RoomType.DOUBLE && q == 0) {p++;}} 
			else if(type.equals("LUXURY")) {if(a.type == RoomType.LUXURY &&q == 0) {p++;}}
			
			
	
	}
	
	return p;
					
	}
        
        
        
	public static File findRooms2(String city,String hotel,String type,String s,String e) throws Exception 
	{
		File og = new File("D:\\project\\cities\\"+city+"\\"+hotel);
		File[] in  = og.listFiles();
		int len=in.length;
		
		for(int i=0;i<len;i++)
		{
			for(int j=i;j<len;j++)
			{
				String a1 = in[i].getName();
				String a2 = in[j].getName();
				String a3=  a1.replace("room", "");
				String a4 = a2.replace("room", "");
				String a5 = a3.replace(".txt","");
				String a6 = a4.replace(".txt", "");
				
				int r= Integer.parseInt(a5);
				int t= Integer.parseInt(a6);
				
				
				if(r>t)
				{
					File temp=in[j];
					in[j]=in[i];
					in[i]=temp;
					
				}
			}
		}

		for(File b :in )
		{
			System.out.println("hii "+b.getName());
			
		}
		File in2=null;
		List<LocalDate> buff = HotelRoom.getDates(s, e);
		
		int q;
	
		for(File c  : in) 
		{
			q = 0;
			
			FileInputStream fis = new FileInputStream("D:\\project\\cities\\"+city+"\\"+hotel+"\\"+c.getName());
			
			
			ObjectInputStream ois = new ObjectInputStream(fis);
			HotelRoom a = (HotelRoom)ois.readObject();
			
			for(LocalDate j : buff) 
			{
				for(LocalDate b : a.full) 
				{
					if(j.equals(b))
					{
						q++;
						break;
					}
				}
				if(q!=0)
					break;
			}
			
			if(type.equals("SINGLE")) 
			{
				if(a.type == RoomType.SINGLE && q == 0)
				{
					
					in2=c;
					return in2;
				}
			}
			
			else if(type.equals("DOUBLE")) 
			{
				if(a.type == RoomType.DOUBLE && q == 0)
				{
					
					in2=c;
					return in2;
				}
			} 
			else if(type.equals("LUXURY")) 
			{
				if(a.type == RoomType.LUXURY &&q == 0) 
				{
					in2=c;
					return in2;
				}
			}
			
			ois.close();
			fis.close();
			
		
		}
	
	return in2;
					
	}	
        
}
	
	

