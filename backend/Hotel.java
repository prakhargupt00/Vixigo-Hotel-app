package backend ; 

import java.io.*;
import java.util.*;

enum Amenity{
	FREE_BREAKFAST,SWIMMING_POOL,WIFI,AC,SPA
	}

public class Hotel {
ArrayList <HotelRoom> hRoomList;
ArrayList <Amenity> amenities;
String city;
String name;
int noOfRooms;
int  price ; 
       public  Hotel(String city,String name) 
        {
                this.city=city;
                this.name=name;

                Random rand = new Random()     ;
                noOfRooms=3*(rand.nextInt(10)+1);
                
                File f2=new File("D:\\project\\cities\\"+city+"\\"+name);
                f2.mkdirs();
                
                price= rand.nextInt(500) +100 ; 
                

                try {
                        for(int i=1;i<=noOfRooms;i++)
                        {
                                if(i<=noOfRooms/3)
                                {
                                          HotelRoom temp =   new HotelRoom(i,RoomType.SINGLE,price);

                                          File file = new File("D:\\project\\cities\\"+city+"\\"+name+"\\room"+i+".txt");
                                          FileOutputStream fos = new FileOutputStream(file);
                                          ObjectOutputStream oos = new ObjectOutputStream(fos);
                                          oos.writeObject(temp);
                                          fos.close();

                                }
                                else if(i<=2*noOfRooms/3)
                                {
                                          HotelRoom temp =   new HotelRoom(i,RoomType.DOUBLE,price*2);
                                          File file = new File("D:\\project\\cities\\"+city+"\\"+name+"\\room"+i+".txt");
                                          FileOutputStream fos = new FileOutputStream(file);
                                          ObjectOutputStream oos = new ObjectOutputStream(fos);
                                          oos.writeObject(temp);
                                          fos.close();
                                }
                                else 
                                {
                                          HotelRoom temp =   new HotelRoom(i,RoomType.LUXURY,price*3);
                                          File  file = new File("D:\\project\\cities\\"+city+"\\"+name+"\\room"+i+".txt");
                                          FileOutputStream fos = new FileOutputStream(file);
                                          ObjectOutputStream oos = new ObjectOutputStream(fos);
                                          oos.writeObject(temp);
                                          fos.close();

                                }

                        }	

                }
                    catch(IOException e)
                    {
                        System.out.println("Error in making file");
                        e.printStackTrace();
                    }
                }	
        
        public  int tell_me_price() {
            						
           return price ; 
        }

}

