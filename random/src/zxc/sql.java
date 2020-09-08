package zxc;

import java.io.Console;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.text.*;

public class sql {  

	Connection conn;
	public sql () throws Exception {
		try {
				Class.forName("org.postgresql.Driver");
		}
		catch (java.lang.ClassNotFoundException e) {
			java.lang.System.err.print("ClassNotFoundException: Postgres Server JDBC");
			java.lang.System.err.println(e.getMessage());
			throw new Exception("No JDBC Driver found in Server");
		}
		try {
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotel","postgres","a");
			conn.setCatalog("jdbcTest");
		}
		catch (SQLException E) { 
			java.lang.System.out.println("SQLException: " + E.getMessage());
			java.lang.System.out.println("SQLState: " + E.getSQLState());
			java.lang.System.out.println("VendorError: " + E.getErrorCode());
			throw E;
		}
	}

	public static void main(String[] args) throws ParseException{
		try {
			sql  db= new sql();
			ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
			
			/** --HOTEL-- **/
			int r;
			int r2;
			String[][] loc = {
					{"Chania","Greece"},
					{"Edessa","Greece"},
					{"Larissa","Greece",},
					{"Volos","Greece"},
					{"Thessaloniki","Greece"},
					{"Hrakleio","Greece",},
					{"Rome","Italy"},
					{"Paris","France"},
					{"Milan","Italy",},
					{"Napoli","Italy"}
				 };
			
			/*for(int i=1;i<=100;i++) {
				String[] stars = {"1 Star","2 Stars","3 Stars","4 Stars","5 Stars"};
			
				CallableStatement cs=db.conn.prepareCall("{call add_hotel(?,?,?,?,?,?,?,?)}");
				cs.clearParameters();
				cs.setInt(1, i);
				cs.setString(2, "hotelname_"+Integer.toString(i));
				r=p.zipf(5);
				cs.setString(3, stars[r]);
				cs.setString(4, "hoteladdress_"+Integer.toString(i));
				r2=p.zipf(10);
				
				cs.setString(5, loc[r2][0]);
				cs.setString(6, loc[r2][1]);
				cs.setString(7, "hotelphone_"+Integer.toString(i));
				cs.setString(8, "hotelfax_"+Integer.toString(i));
				cs.executeUpdate();
			}*/
			
			
			
			
			// --ROOMTYPE-- 
			/*
			for(int i=1;i<=10;i++) {
				CallableStatement cs=db.conn.prepareCall("{call add_roomtype(?,?)}");
				cs.clearParameters();
				cs.setString(1, "typename_"+Integer.toString(i));
				cs.setNull(2, Types.BLOB);
				cs.executeUpdate();
			}
			*/
			// --ROOMRATE-- 
			/*
			CallableStatement cs=db.conn.prepareCall("{call add_roomrate(?,?,?,?)}");
			float rate[]=new float[10];
			int typenumber=0;
			for(int hotelid=1;hotelid<=100;hotelid++) {
				for(typenumber=1;typenumber<=10;typenumber++) {	//rate gia ka8e tupo dwmatiou
					rate[typenumber-1]=50+p.zipf(151);	//tuxaia timh ~50-200
					cs.clearParameters();
					cs.setInt(1, hotelid);
					cs.setString(2, "typename_"+Integer.toString(typenumber));
					cs.setFloat(3, rate[typenumber-1]);
					cs.setFloat(4, (float)p.zipf(5)/10);
					cs.executeUpdate();
				}
			}
			*/
			//--ROOM-- 
			/*
			int id=0;
			for(int i=1;i<=100;i++) {	//Gia kathe hotel...
				int floors=4+p.zipf(5);
				for(int j=1;j<=floors;j++) {	//Gia kathe orofo...
					int roomsperfloor=10+p.zipf(20);
					int num=1;
					for(int k=10;k<=roomsperfloor;k++) {	//Gia kathe dwmatio...
						id++;
						float area=10+p.zipf(11) + p.zipf(10)/10;	//akeraio + dekadiko meros
						int roomtype=1+p.zipf(10);
						boolean vacant;
						r=p.zipf(100);
						if(1+r>40) vacant=false; else vacant=true;
						CallableStatement cs=db.conn.prepareCall("{call add_room(?,?,?,?,?,?)}");
						cs.clearParameters();
						cs.setInt(1, id);
						cs.setInt(2, id*1000+j*100+num);  //to balame etsi giati to number einai uniquw key , gia na mhn exoume duplicates
						num++;
						cs.setBoolean(6, vacant);
						cs.setFloat(3, area);
						cs.setInt(4, i);
						cs.setString(5, "typename_"+Integer.toString(roomtype));
						
						cs.executeUpdate();
					}
				}
			}
		*/
			/*
			// --TRAVELAGENT--
			CallableStatement cs2=db.conn.prepareCall("{call add_travelagent(?,?,?)}");
			for(int i=1;i<=50;i++) {
				cs2.clearParameters();
				cs2.setInt(1, i);
				cs2.setString(2, "travel_agent_name_"+Integer.toString(i));
				cs2.setString(3, "travel_agent_email_"+Integer.toString(i));
				cs2.executeUpdate();
			}*/
			
			
			
			// --EMPLOYEE-PERSON-- 
			/*
			CallableStatement cs3=db.conn.prepareCall("{call add_person_emp(?,?,?,?,?,?,?,?,?,?,?)}");
			
			int j=1;
			int count=1;	//Metrame posous exoume valei gia na allazoume ta idperson (j)
			for(int hotelid=1;hotelid<=100;hotelid++) {
				int employeecount=10+p.zipf(10);
				int managercount=2+p.zipf(4);
				for(j=count;j<=employeecount+count;j++) {
					cs3.clearParameters();
					cs3.setInt(1, j);
					if(j-count+1<=managercount) {	//Oi prwtoi pou mpainoun einai oi manager
						cs3.setString(9, "Manager");
						cs3.setNull(10, Types.INTEGER);
					}
					else {	//Kai akolou8oun oi receptionists
						cs3.setString(9, "Receptionist");
						cs3.setInt(10, count+p.zipf(managercount));
					}
					
					cs3.setString(2, "Name_"+Integer.toString(1+p.zipf(50)));
					cs3.setString(3, "Surname_"+Integer.toString(1+p.zipf(200)));
					cs3.setInt(4, p.zipf(2));
					int month=1+p.zipf(12);
					int day;
					if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
						day=1+p.zipf(31);
					}
					else if(month==2) {
						day=1+p.zipf(28);
					}
					else {
						day=1+p.zipf(30);
					}
					Date dateofbirth=Date.valueOf(Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(day));
					cs3.setDate(5, dateofbirth);
					cs3.setString(6, "Person_Address_"+Integer.toString(j));
					r=p.zipf(10);
					
					cs3.setString(7, loc[r][0]);
					cs3.setString(8, loc[r][1]);
					cs3.setInt(11, hotelid);
					cs3.executeUpdate();
				}
				count=j;
			}*/
			/*
			// --CLIENT-PERSON-- 
			
				int clientcount=150*(4+p.zipf(8));
				for(int j=100000000;j<=100000000+clientcount;j++) {
					CallableStatement cs4=db.conn.prepareCall("{call add_person_client(?,?,?,?,?,?,?,?,?)}");
					cs4.clearParameters();
					cs4.setInt(1, j);
					cs4.setString(9, "ID_"+Integer.toString(j));
					String name=new String("Name_"+Integer.toString(1+p.zipf(50)));
					String surname=new String("Surname_"+Integer.toString(1+p.zipf(200)));
					cs4.setString(2, name);
					cs4.setString(3, surname);
					cs4.setInt(4, p.zipf(2));
					int month=1+p.zipf(12);
					int day;
					if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
						day=1+p.zipf(31);
					}
					else if(month==2) {
						day=1+p.zipf(28);
					}
					else {
						day=1+p.zipf(30);
					}
					Date dateofbirth=Date.valueOf(Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(day));
					cs4.setDate(5, dateofbirth);
					cs4.setString(6, "Person_Address_"+Integer.toString(2));
					r=p.zipf(10);
					cs4.setString(7, loc[r][0]);
					cs4.setString(8, loc[r][1]);
					cs4.executeUpdate();
					
					
					if(p.zipf(100)<80) {
						cs4=db.conn.prepareCall("{call add_credit(?,?,?,?,?)}");
						cs4.clearParameters();
					    cs4.setInt(5, j);
					    
						cs4.setString(1, "cardtype_"+Integer.toString(1+p.zipf(5)));
						Integer CN1,CN2,CN3,CN4;
						CN1=p.zipf(1001);
						CN2=p.zipf(1001);
						CN3=p.zipf(1001);
						CN4=p.zipf(1001);
					    StringBuffer CN1s = new StringBuffer(CN1.toString());  
					    StringBuffer CN2s = new StringBuffer(CN2.toString());  
					    StringBuffer CN3s = new StringBuffer(CN3.toString());  
					    StringBuffer CN4s = new StringBuffer(CN4.toString());  
					    while (CN1s.length() <4) {  
					    	CN1s.insert(0, '0');  
					    }
					    while (CN2s.length() <4) {  
					    	CN2s.insert(0, '0');  
					    }
					    while (CN3s.length() <4) {  
					    	CN3s.insert(0, '0');  
					    }
					    while (CN4s.length() <4) {  
					    	CN4s.insert(0, '0');  
					    }
					    cs4.setString(2, CN1s+"-"+CN2s+"-"+CN3s+"-"+CN4s);
					    Date expiration=Date.valueOf(Integer.toString(2015+p.zipf(3))+"-"+Integer.toString(1+p.zipf(12))+"-"+Integer.toString(30));
					    cs4.setDate(3, expiration);
					    cs4.setString(4, surname+" "+name);
					    cs4.executeUpdate();
					}
					cs4.close();
			}*/
			//facilities
			/*int i=0;
			Statement sc;
			sc=db.conn.createStatement();
			for(int N0=1;N0<=5;N0++) {
				i++;
				sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description) " +
								   "VALUES('hotel_facility_"+Integer.toString(N0)+"',NULL " +
								   ",'description_"+Integer.toString(N0)+"')");
			}
			for(int N0=1;N0<=5;N0++) {
				for(int N1=1;N1<=3;N1++) {
					i++;
					sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description) " +
												   "VALUES('hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+
												   "','hotel_facility_"+Integer.toString(N0) +
												   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"')");
				}
			}
			for(int N0=1;N0<=5;N0++) {
				for(int N1=1;N1<=3;N1++) {
					for(int N2=1;N2<=2;N2++) {
						i++;
						sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   " VALUES('hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+							
													   "','hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1) +
													   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"')");
					}
				}
			}
			for(int N0=1;N0<=5;N0++) {
				for(int N1=1;N1<=3;N1++) {
					for(int N2=1;N2<=2;N2++) {
						for(int N3=1;N3<=1;N3++) {
							i++;
							sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   	   " VALUES('hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+							
													   	   "','hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2) +
													   	   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"')");
						}
					}
				}
			}
			for(int N0=1;N0<=5;N0++) {
				for(int N1=1;N1<=3;N1++) {
					for(int N2=1;N2<=2;N2++) {
						for(int N3=1;N3<=1;N3++) {
							for(int N4=1;N4<=1;N4++) {
								i++;
								if(i>100) break;
								sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   	   	  " VALUES('hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"_"+Integer.toString(N4)+							
													   	   	  "','hotel_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2) +"_"+Integer.toString(N3) +
													   	   	  "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"_"+Integer.toString(N4)+"')");
							}
						}
					}
				}
			}
			i=0;
			for(int N0=6;N0<=9;N0++) {
				i++;
				sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description) " +
								   "VALUES('room_facility_"+Integer.toString(N0)+"',NULL " +
								   ",'description_"+Integer.toString(N0)+"')");
			}
			for(int N0=6;N0<=9;N0++) {
				for(int N1=6;N1<=7;N1++) {
					i++;
					sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description) " +
												   "VALUES('room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+
												   "','room_facility_"+Integer.toString(N0) +
												   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"')");
				}
			}
			for(int N0=6;N0<=9;N0++) {
				for(int N1=6;N1<=7;N1++) {
					for(int N2=6;N2<=7;N2++) {
						i++;
						sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   " VALUES('room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+							
													   "','room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1) +
													   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"')");
					}
				}
			}
			for(int N0=6;N0<=9;N0++) {
				for(int N1=6;N1<=7;N1++) {
					for(int N2=6;N2<=7;N2++) {
						for(int N3=6;N3<=6;N3++) {
							i++;
							sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   	   " VALUES('room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+							
													   	   "','room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2) +
													   	   "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"')");
						}
					}
				}
			}
			for(int N0=6;N0<=9;N0++) {
				for(int N1=6;N1<=7;N1++) {
					for(int N2=6;N2<=7;N2++) {
						for(int N3=6;N3<=6;N3++) {
							for(int N4=6;N4<=6;N4++) {
								i++;
								if(i>60) break;
								sc.executeUpdate("INSERT INTO facilities(namefacility,parentnamefacility,description)" +
													   	   	  " VALUES('room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"_"+Integer.toString(N4)+							
													   	   	  "','room_facility_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2) +"_"+Integer.toString(N3) +
													   	   	  "','description_"+Integer.toString(N0)+"_"+Integer.toString(N1)+"_"+Integer.toString(N2)+"_"+Integer.toString(N3)+"_"+Integer.toString(N4)+"')");
							}
						}
					}
				}
			}*/
			
			/** --ROOMFACILITIES-- **/
			/*
			Statement sc;
			sc=db.conn.createStatement();
			//Euresh ari8mou dwmatiwn
			ResultSet rs=sc.executeQuery("SELECT COUNT(idRoom) FROM rooms");
			rs.next();
			int roomcount=rs.getInt(1);

			String[] roomfacilities = {"NULL","NULL","NULL","NULL","NULL"};
			String[] rfdescriptions = {"NULL","NULL","NULL","NULL","NULL"};
			CallableStatement cs=db.conn.prepareCall("{call add_roomfacilities(?,?,?)}");
			for(int i=1;i<=roomcount;i++) {
				int rfcount=p.zipf(6);	//plh8os dieukolunsewn
				rs=sc.executeQuery("SELECT namefacility,description " +
									 "FROM (SELECT * FROM facilities ORDER BY namefacility DESC LIMIT 60) AS F " +
									 "ORDER BY RANDOM() " +
									 "LIMIT "+Integer.toString(rfcount));
				int j=0;
				while(rs.next()) {
					roomfacilities[j]=rs.getString("namefacility");
					rfdescriptions[j]=rs.getString("description");
					j++;
				}
				for(int k=0;k<j;k++) {
					cs.clearParameters();
					cs.setInt(1, i);
					cs.setString(2, roomfacilities[k]);
					cs.setString(3, rfdescriptions[k]);
					cs.executeUpdate();
				}

			}

		
			*/
			//roombooking
			
			
			
			 
			
			/** --HOTELBOOKING-- **/
		 
			int kratiseis=500+p.zipf(200); //bazoume ena tyxaio arithmo krathsewn 
			System.out.println(kratiseis);
			 int total_amount=0,payed=0;
			CallableStatement sc=db.conn.prepareCall("{call add_hotelbooking(?,?,?,?,?,?,?)}");
			CallableStatement cs=db.conn.prepareCall("{call add_roombooking(?,?,?,?,?,?,?)}");
			for(int i=1;i<=kratiseis;i++){
				int status=1;
				sc.clearParameters();
				sc.setInt(1, i);
			     int rate=0;
				Date cancellation;
				Date reservation,check_in,check_out=null;
				//edw ftaixnoume to reservation day tixaia....
				int month=1+p.zipf(8);
				int day;
				if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
					day=1+p.zipf(31);
				}
				else if(month==2) {
					day=1+p.zipf(28);
				}
				else {
					day=1+p.zipf(30);
				}
				
				cancellation=null;
				if(p.zipf(100)<0.1)//10 % na ginei akirwthei mia kratish kai to cancelation date den tha einai null
				{    status=0;
					int day_cancel=0;
					int month_cancellation;
					month_cancellation=month-p.zipf(month); //mporei na kanei akirwsh ton idio h kapoion apo to prohgoumeno mhna
					if (month==month_cancellation)
						{day_cancel=day-p.zipf(day);} //tha einai mia apo tiw prohgoumenes meres
					else{ //opoiadhpote mera
						if(month_cancellation==1 || month_cancellation==3 || month_cancellation==5 || month_cancellation==7 || month_cancellation==8 || month_cancellation==10 || month_cancellation==12) {
							day_cancel=1+p.zipf(31);
						}
						else if(month_cancellation==2) {
							day_cancel=1+p.zipf(28);
						}
						else {
							day_cancel=1+p.zipf(30);
						}
					}
						cancellation=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(month_cancellation)+"-"+Integer.toString(day_cancel));
						System.out.println("ok");
				}
				 
					
					
					    rate=50+p.zipf(151);
					  // cancellation=null;
					   int day_ckeckout=0,month_ckeckout=0,day_ckeckin;
					   int a=0;
					  int  month_ckeckin=month+p.zipf(2);
					   if(month_ckeckin>=12)
					   {
						   month_ckeckin=12;
						   
					   }
					   int x1;
					   if(month_ckeckin==1 || month_ckeckin==3 || month_ckeckin==5 || month_ckeckin==7 ||month_ckeckin==8 || month_ckeckin==10 || month_ckeckin==12) {
						   day_ckeckin=day+p.zipf(31-day);
						    a=31;
						}
						else if(month_ckeckin==2) {
							if(28-day<0)
								  x1=1;
							else
								x1=28-day;
							
							day_ckeckin=day+p.zipf(x1);
							 a=28;
						}
						else {
							
							day_ckeckin=day+p.zipf(30-day);
							 a=30;
						}
					   
					   
					   if(day_ckeckin>31)
						   day_ckeckin=31;
					   check_in=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(month_ckeckin)+"-"+Integer.toString(day_ckeckin));
					   
					   if(month<12){
					   month_ckeckout=month_ckeckin+p.zipf(2);}
					   else
						   month_ckeckout=12;
					   if(month_ckeckin==month_ckeckout)
					   {   
						    if(a==31){
						        day_ckeckout=day_ckeckin+p.zipf(31-day);}
						    else if(a==28){
						    	day_ckeckout=day_ckeckin+p.zipf(28-day);}
						    else 
						    	day_ckeckout=day_ckeckin+p.zipf(30-day);
						    	
					   }
					   else{
						   if(month_ckeckout==1 || month_ckeckout==3 || month_ckeckout==5 || month_ckeckout==7 ||month_ckeckout==8 || month_ckeckout==10 || month_ckeckout==12) {
							   day_ckeckout=day+p.zipf(31-day);
							    a=31;
							}
							else if(month_ckeckout==2) {
								
								
								day_ckeckout=day+p.zipf(28-day);
								 a=28;
							}
							else {
								
								day_ckeckout=day+p.zipf(30-day);
								 a=30;
							}
		          	}
					   if(day_ckeckout>31)
						   day_ckeckout=31;
					  System.out.println(Integer.toString(2014)+"-"+Integer.toString(month_ckeckout)+"-"+Integer.toString(day_ckeckout));
					   check_out=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(month_ckeckout)+"-"+Integer.toString(day_ckeckout));
					   
					   
					  /* if(month_ckeckout>=12)
					   {
						   month_ckeckout=12;
						   
					   }
					   
					   if(month_ckeckout==1 || month_ckeckout==3 || month_ckeckout==5 || month_ckeckout==7 ||month_ckeckout==8 || month_ckeckout==10 || month_ckeckout==12) {
						   day_ckeckout=day+p.zipf(31-day);
						    a=31;
						}
						else if(month_ckeckout==2) {
							if(28-day<0)
								System.out.println("arnhtikos");
							day_ckeckout=day+p.zipf(28-day);
							 a=28;
						}
						else {
							if(28-day<0)
								System.out.println("arnhtikos");
							day_ckeckout=day+p.zipf(30-day);
							 a=30;
						}*/
					   
					   
					   if(month==month_ckeckout)
					   {
						   payed=a*rate+p.zipf(100);
						   total_amount=a*rate;
						   
						   
					   }
					   else
					   {
						   payed=a*rate*(month_ckeckout-month)+p.zipf(100);
						   total_amount=a*rate;
					   }
					 
					   
					
					
					
				
					
				
				
				
				 reservation=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(month)+"-"+Integer.toString(day));
				
					//hotelbooking
				sc.setDate(2, reservation);
				reservation=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(month)+"-"+Integer.toString(day));
				sc.setDate(3, cancellation);
				sc.setFloat(4, total_amount);
				sc.setFloat(5, payed);
				sc.setInt(6, status);
				int j=100000000+i;
				sc.setInt(7, j);
				sc.executeUpdate();
				
				
				//roombooking
				cs.clearParameters();
				cs.setInt(1, i);
				int idroom=1+p.zipf(2786);
				cs.setInt(2,idroom);
				cs.setInt(3, j);
				cs.setDate(4, check_in);//checkin
				cs.setDate(5, check_out);
				cs.setDate(6, cancellation);
				cs.setFloat(7,rate);
				cs.executeUpdate();
				
				
				
				
				
				
			}
			
			
			/** --HOTELFACILITIES-- **/
			/*
			int i;
			String nfac,descr;
			CallableStatement cs=db.conn.prepareCall("{call add_hotelfacilities(?,?,?)}");
			Statement sc;
			sc=db.conn.createStatement();
			//Euresh ari8mou dwmatiwn
			
				
		for(i=1;i<=100;i++){
			
			cs.clearParameters();
			cs.setInt(1,i);
			
			

			String hotelfacilities, rfdescriptions;
			
		
				int rfcount=10+p.zipf(40);	//plh8os dieukolunsewn
				ResultSet rs=sc.executeQuery("SELECT namefacility,description " +
									 "FROM (SELECT * FROM facilities ORDER BY namefacility ASC LIMIT 100) AS F " +
									 "ORDER BY RANDOM() " +
									 "LIMIT "+Integer.toString(rfcount));
				while(rs.next()){
					hotelfacilities=rs.getString("namefacility");
					rfdescriptions=rs.getString("description");
					cs.setString(2,hotelfacilities);
					cs.setString(3,rfdescriptions);
					cs.executeUpdate();
				}
				
		}
		*/
		 /*
			Scanner keyboard= new Scanner(System.in);
			System.out.println("Insert disarable hotel facilities: ");
			String hfacility=keyboard.next();
			System.out.println("Insert disarable room facilities: ");
			String rfacility=keyboard.next();
			Statement sc;
			sc=db.conn.createStatement();
			ResultSet rs=sc.executeQuery("SELECT DISTINCT hotelfacilities.idhotel "+
											"FROM  hotelfacilities,hotel,room,roomfacilities "+
											"WHERE (hotelfacilities.namefacility="+hfacility+" AND hotelfacilities.idhotel=hotel.hotelid AND room.vacant=TRUE AND roomfacilities.namefacility="+rfacility+")");
			ResultSet rs=sc.executeQuery("SELECT S1.idhotel " +
										   "FROM (SELECT * FROM hotelfacilities HF WHERE HF.namefacility = '"+hfacility+"') AS S1, hotel H " +
										   "WHERE S1.idhotel = H.idhotel " +
										   "INTERSECT " +
										   "SELECT S2.hotelid " +
										   "FROM (SELECT * FROM room R WHERE R.vacant = TRUE) AS S2, roomfacilities RF " +
										   "WHERE (S2.idroom = RF.idroom AND RF.namefacility = '"+rfacility+"')");
			while(rs.next()) {
				System.out.println(rs.getInt(1));
			}
			*/
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
