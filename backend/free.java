/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author acer
 */
package backend ; 

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class free {
	
	
	
public static void tryfreeing(User u,Booking b) throws Exception {
	
	
	
	FileInputStream fis  = new FileInputStream("D:\\project\\cities\\"+b.city+"\\"+b.hName+"\\"+b.hroom+".txt");	
	ObjectInputStream ois = new ObjectInputStream(fis);
	HotelRoom hr = (HotelRoom)ois.readObject();
	List <LocalDate> og = new ArrayList<LocalDate>();
	og = hr.full;
	List <LocalDate> fin = new ArrayList<LocalDate>();	
	List <LocalDate> tbrm = HotelRoom.getDates(b.checkIn.toString(), b.checkOut.toString());
	int g;
	
	for (LocalDate i : og) 
	{
		g = 0;
		
		for (LocalDate j : tbrm) 
		{
			if(i.equals(j)) {
				g++;
			}
		}
			if(g == 0)
			{
				fin.add(i);
			}
		
		
		
		
		
		
	}
		FileOutputStream fo  = new FileOutputStream("D:\\project\\user\\"+u.username+".txt");		
		ObjectOutputStream oo = new ObjectOutputStream(fo);
		oo.writeObject(u);
		oo.close();
		
	System.out.println("the modified list is");
	for(LocalDate k :fin) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String f = k.format(formatter);
		System.out.println(f);
		
	}
     hr.full = fin;
     hr.flushWL();
     FileOutputStream fos  = new FileOutputStream("D:\\project\\cities\\"+b.city+"\\"+b.hName+"\\"+b.hroom+".txt");
	
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(hr);
	oos.close();
	
	for(WLmem i : hr.Wlist) {
		System.out.println(i.bookingname+" and "+i.username);
	}
}	
	
	

}
