package zxc;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.io.Console;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.text.*;



 

 



public class newdtb4 {
	protected static Connection conn;
	public Statement statement;
	 
	
	//newdtb4 db= new newdtb4();
	//static Statement st ;
	
	//kanoyme thn sindesh
	
	public newdtb4 () throws Exception {
		
		try { 
				Class.forName("org.postgresql.Driver");
		}
		catch (java.lang.ClassNotFoundException e) {
			java.lang.System.err.print("ClassNotFoundException: Postgres Server JDBC");
			java.lang.System.err.println(e.getMessage());
			throw new Exception("No JDBC Driver found in Server");
		}
		try {
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hotel_xwris","postgres","a");
			conn.setCatalog("jdbcTest");
		}
		catch (SQLException E) { 
			 
			java.lang.System.out.println("SQLException: " + E.getMessage());
			java.lang.System.out.println("SQLState: " + E.getSQLState());
			java.lang.System.out.println("VendorError: " + E.getErrorCode());
			throw E;
		}
	}
	
	
	ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));

	//U-1 eisagwgh employee-person-credit card
	
    
	//Main programm//
    public static void main(String[] args) {
    	 
		try { 
			 int num_client=0;
			  
			newdtb4 db= new newdtb4(); 
			Statement st;
		st=(Statement) conn.createStatement();
		//insert_person_one_day(0); //u1  gia mia mera 
		//insert_person_many_day(); //-----u1 gia polles meres
		
		//ins_person();//-----u2
		 //hotelbooking(); //---U3
		//updates();        //-----U4
		  //q1();
		//q2();
		
	
		}catch (Exception ex) {
			
		ex.printStackTrace();
		}

    }
  //gia to U1-INSERT CLIENT-PERSON-CREDIT CARD-----------------------------------------------------------------------------------------------------------------------------
    public static long ins_client(int counter) throws Exception{
    	try {
    		newdtb4 db= new newdtb4();
			ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));	
			//pws pairnw ton xrono ton queries??? magkika...elegxw to timestamp prin kai meta to query kai to pros8etw th diafora sto total.....
			
			
			
			//U1
			
			    long timeStartControlU1=System.currentTimeMillis();
				long timeEndControlU1=System.currentTimeMillis();
				long timeEndControlU1_2=System.currentTimeMillis();
				long totalTime=0L;
			for(int j=1;j<=100;j++) {
				CallableStatement cs4=db.conn.prepareCall("{call add_person_client1(?,?,?,?,?,?,?,?,?)}");
				cs4.clearParameters();
				cs4.setInt(1, j+counter);
				cs4.setString(9, "ID_"+Integer.toString(j+counter));
				String name=new String("Name_"+Integer.toString(1+p.zipf(50)));
				String surname=new String("Surname_"+Integer.toString(1+p.zipf(200)));
				cs4.setString(2, name);
				cs4.setString(3, surname);
				cs4.setInt(4, p.zipf(2));
				int month=1+p.zipf(12);
				int day,r;
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
				if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
					day=1+p.zipf(31);
				}
				else if(month==2) {
					day=01+p.zipf(28);
				}
				else {
					day=1+p.zipf(30);
				}
				
				String strdate;
				if(month>9){
					if(day>9){
						strdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(day);
					}
					else{
						strdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(0)+Integer.toString(day);
					}
				}
				else{
					if(day>9){
						strdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(0)+Integer.toString(month)+"-"+Integer.toString(day);
					}
					else{
						strdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(0)+Integer.toString(month)+"-"+Integer.toString(0)+Integer.toString(day);
					}				}
				
				//System.out.println(strdate);
				Date dateofbirth=Date.valueOf( strdate);
				cs4.setDate(5,dateofbirth );
				cs4.setString(6, "Person_Address_"+Integer.toString(2));
				r=p.zipf(10);
				cs4.setString(7, loc[r][0]);
				cs4.setString(8, loc[r][1]);
				timeStartControlU1=System.currentTimeMillis();
				cs4.executeUpdate();
				timeEndControlU1=System.currentTimeMillis();
				totalTime = totalTime + (timeEndControlU1-timeStartControlU1);
				
				if(p.zipf(100)<80) {
					cs4=db.conn.prepareCall("{call add_credit(?,?,?,?,?)}");
					cs4.clearParameters();
				    cs4.setInt(5, j+counter);
				    
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
				    String expdate;
					if(month>=10){
						if(day>=10){
							expdate=Integer.toString(2014+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(day);
						}
						else{
							expdate=Integer.toString(2014+p.zipf(40))+"-"+Integer.toString(month)+"-"+Integer.toString(0)+Integer.toString(day);
						}
					}
					else{
						if(day>9){
							expdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(0)+Integer.toString(month)+"-"+Integer.toString(day);
						}
						else{
							expdate=Integer.toString(1950+p.zipf(40))+"-"+Integer.toString(0)+Integer.toString(month)+"-"+Integer.toString(0)+Integer.toString(day);
						}					}
				    //System.out.println(expdate);
				    Date expiration=Date.valueOf(expdate);
				    cs4.setDate(3,expiration);
				    cs4.setString(4, surname+" "+name);
				    cs4.executeUpdate();
				    timeEndControlU1_2=System.currentTimeMillis();
				    totalTime = totalTime +(timeEndControlU1_2-timeEndControlU1);
				}
				
				cs4.close();
			} return totalTime;
	    }catch(SQLException ex){
			ex.printStackTrace();
			return 0;
	    }
	   
    }
   //END U1-INSERT CLIENT-PERSON-CREDIT CARD---------------------------------------------------------------------------------------------------------------------------------------   
    
    
    
    
    //gia to U2-INSERT  PERSON-----------------------------------------------------------------------------------------------------------------------------   
    public static void ins_person() throws Exception  {
    	long timeStartControlU2=System.currentTimeMillis();
		long timeEndControlU2=System.currentTimeMillis();
		
		long totalTime=0L;
      
	   	 newdtb4 db = new newdtb4(); 
	   	
	   	ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
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
	   	try{
	   		
	   		
              
     		//edw kanoume query gia na doume posa columns iparxoyn gia na kanoyme prosthiki apo ekei kai pera....
	   		
	   		//-- from clients--//
	   		
	   	for(int days=1;days<=50;days++){
	   			timeStartControlU2=System.currentTimeMillis();
             Statement st = conn.createStatement(); //sindesi gia na parw stoixeia
             ResultSet rs = st.executeQuery("select count(documentclient) from client");
             int number_columns=0;
             while (rs.next())
             {
                System.out.print("Number of clients ");
                System.out.println(rs.getString(1));
                 number_columns = Integer.parseInt(rs.getString(1));
             } rs.close();
             st.close();
           
             //-- from persons--//
             Statement st1 = conn.createStatement();
             ResultSet rs_person = st1.executeQuery("select  count('idPerson') from person");
             int number_person = 0;
             while (rs_person.next())
             {
                System.out.print("Number of person ");
                System.out.println(rs_person.getString(1));
                number_person = Integer.parseInt(rs_person.getString(1));
             } rs_person.close();
             st1.close();
             
             
             
             
             PreparedStatement cs3=db.conn.prepareCall("{call add_person(?,?,?,?,?,?,?,?)}");
     		 
			int j=1;
			int count=1;
			int c=1;//Metrame posous exoume valei gia na allazoume ta idperson (j)
			for(int i=1;i<=0.05*number_columns;i++) {
				
				
					cs3.clearParameters();
					cs3.setInt(1,c+number_person);
					c=c+1;
					
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
					
					cs3.executeUpdate();
					
					
				count=j;}
			timeEndControlU2=System.currentTimeMillis();
			totalTime=totalTime+(timeEndControlU2-timeStartControlU2);
			
			}
	   	System.out.println("U2:sinolikos xronos gia" + 100 + "meres:" + totalTime + " msec.");
	   	}catch(SQLException ex){
			ex.printStackTrace();
			
			
	    }
    	
    }
  //telos to U2-INSERT  PERSON----------------------------------------------------------------------------------------------------------------------------- 
    
   
    
 //----------------------------------------------------------U3-------------------------------------------------------------------------------
    public static void hotelbooking() throws Exception{
		  newdtb4 db = new newdtb4(); 
		  	long timeStartControlU2=System.currentTimeMillis();
			long timeEndControlU2=System.currentTimeMillis();
			
			long totalTime=0L;
		    
			  ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
			  
			//-- from clients--//
		      Statement st = conn.createStatement(); //sindesi gia na parw stoixeia
		      ResultSet rs = st.executeQuery("select count(documentclient) from client");
		      int number_columns=0;
		      while (rs.next())
		      {
		        // System.out.print("Number of clients ");
		        // System.out.println(rs.getString(1));
		          number_columns = Integer.parseInt(rs.getString(1));
		      } rs.close();
		      st.close();
		      double pososto=0;
		      if (number_columns>1){
		      	  pososto=0.2*number_columns;
		      }
		      //Vector(Int) userPrefCat = new Vector<Int>();
		      timeStartControlU2=System.currentTimeMillis();
	for(int days=1;days<=50;days++){
		      for(int i=1;i<= pososto;i++)
		      {     int[] res;

		        // allocates memory for 10 integers
		      		res = new int[1000];
		        	int client=p.zipf(number_columns-1)+1; //dialegoume idclient=personid,enas pelaths mporei na kanei polles krathseis..
		    	 	int hotel=p.zipf(99)+1;
		    	 	Statement st1 = conn.createStatement();
		            ResultSet result = st1.executeQuery("select idroom from roomss where hotelID='"+hotel+"' "); // '"+tmpUserId+"'
		            int number_person = 0;
		            //System.out.println(rs_person);
		            int c=0;
		           while (result.next()) {
			    		res[c]= result.getInt(1) ;
			    		System.out.println(res[c]);
			    		c++;
					}
		           st1.close();
		           
		           int ar_dwmatiwn=1,pithanothta1=p.zipf(100),dwmatio1 = 1,dwmatio2=1,dwmatio3=1;
		           if(pithanothta1<50){
		        	   ar_dwmatiwn=1;
		               dwmatio1=p.zipf(c-1);
		               dwmatio1=res[dwmatio1];}
		           else if(pithanothta1>80)
		           {
		        	   ar_dwmatiwn=3;
			           dwmatio1=p.zipf(c-1);
		               dwmatio1=res[dwmatio1];
		               dwmatio2=p.zipf(c-1);
		               dwmatio2=res[dwmatio2];
		               dwmatio3=p.zipf(c-1);
		               dwmatio3=res[dwmatio3];
		           }   
		           else if(pithanothta1>70)
		           {   	dwmatio1=p.zipf(c-1);
	               		dwmatio1=res[dwmatio1];
			            dwmatio2=p.zipf(c-1);
			            dwmatio2=res[dwmatio2];
		    	      ar_dwmatiwn=2;}
		        //   System.out.println("ar_dwmatiwn  " + ar_dwmatiwn + " " + dwmatio1);
		          
		           //dianikterefseis
		           int dian=1;
		           int pith2=p.zipf(100);
		           if(pith2<=40)
		           {
		        	   dian=1;
		           }
		           else if(pith2>=40 && pith2<=70)
		           {
		        	   dian=2;
		           }
		           else if(pith2>=70 && pith2<=85)
		           {
		        	   dian=3;
		           }
		           else if(pith2>=85 && pith2<=95)
		           {
		        	   dian=3;
		           }
		           else
		        	   dian=3;
		           boolean va = false;
		            Statement st2 = conn.createStatement();//'"+tmp+"'
		            ResultSet result1 = st2.executeQuery("select vacant from roomss where idroom='"+dwmatio1+"' ");//blepw ean to dwmatio einai diathesimo
		            while (result1.next()) {
			    		va=result1.getBoolean(1);
			    		
					}
		           st2.close();
		           
		           if(va==true) //ama to dwmatio einai elefthero den exoume thema me thn hmeromhnia...
		           {
		        	  
		        	   Date check_in = Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
		        	   Date ckeck_out=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+dian));
		               
		        	   
		        	   System.out.println(check_in+ "   " + ckeck_out);
		        	   
		        	   
		        	   double roomrate=0;
		        	   String roomtype = null;
		        	   Statement st3 = conn.createStatement();//'"+tmp+"'
			            ResultSet result2 = st3.executeQuery("select roomtype from roomss where hotelID='"+hotel+"'");//blepw ean to dwmatio einai diathesimo
			            while (result2.next()) {
				    		roomtype=result2.getString(1);
				    		
				    		
						}
			           st3.close();
			         //  System.out.println(roomtype);
			           int roomrate1=0;
			           Statement st4 = conn.createStatement();//'"+tmp+"'
			            ResultSet result3 = st4.executeQuery("select rate from roomrate1 where roomtype='"+roomtype+"' and hotelid='"+hotel+"' ");//blepw ean to dwmatio einai diathesimo
			            while (result3.next()) {
				    		roomrate1=result3.getInt(1);
				    		
						}
			           st4.close();
			           
			           String query = "UPDATE roomss SET  vacant=false WHERE idroom='"+dwmatio1+"' ";
						
						Statement st5 = conn.createStatement();
						st5.executeUpdate(query);
						st5.close();
			           
						Statement st6 = conn.createStatement(); //sindesi gia na parw stoixeia
					      ResultSet rs6 = st6.executeQuery("select count(idhotelbooking) from hotalbooking1");
					      int numbers=0;
					      while (rs6.next())
					      {
					         System.out.print("Number of hotelbooking ");
					         System.out.println(rs6.getString(1));
					          numbers = Integer.parseInt(rs6.getString(1));
					      } rs6.close();
					      st6.close();
			           
			           
			             CallableStatement sc=db.conn.prepareCall("{call add_hotelbooking(?,?,?,?,?,?,?)}");
			             CallableStatement cs=db.conn.prepareCall("{call add_roombooking(?,?,?,?,?,?,?)}");
			             sc.setInt(1, numbers);
			             sc.setDate(2, check_in);
			             sc.setDate(3,null);
			             sc.setDouble(4,1.0);
			            
			             sc.setInt(5, 1);
			             sc.setInt(6,1);
			             sc.setInt(7, client);
			             sc.executeUpdate();
			             
			             cs.setInt(1,numbers);
			             cs.setInt(2,dwmatio1);  //dwmatio1=roomid
			             
			             cs.setInt(3,client);
			             cs.setDate(4,check_in );
			             cs.setDate(5,ckeck_out );
			             cs.setDate(6, null);
			             cs.setInt(7, roomrate1);
			             cs.executeUpdate();
			             
			             
		           
		           }
if(ar_dwmatiwn>=2 )
		           {
		        	  
			            Statement st7 = conn.createStatement();//'"+tmp+"'
			            ResultSet result3 = st7.executeQuery("select vacant from roomss where idroom='"+dwmatio2+"' ");//blepw ean to dwmatio einai diathesimo
			            while (result3.next()) {
				    		va=result3.getBoolean(1);
				    		
						}
			           st7.close();
			           
		        	   String roomtype = null;
		        	   Statement st8 = conn.createStatement();//'"+tmp+"'
			            ResultSet result4 = st8.executeQuery("select roomtype from roomss where hotelID='"+hotel+"'");//blepw ean to dwmatio einai diathesimo
			            while (result4.next()) {
				    		roomtype=result4.getString(1);
				    		
				    		
						}
			           st8.close();
			         //  System.out.println(roomtype);
			           int roomrate1=0;
			           Statement st9 = conn.createStatement();//'"+tmp+"'
			            ResultSet result5 = st9.executeQuery("select rate from roomrate1 where roomtype='"+roomtype+"' and hotelid='"+hotel+"' ");//blepw ean to dwmatio einai diathesimo
			            while (result5.next()) {
				    		roomrate1=result5.getInt(1);
				    		
						}
			           st9.close();
			       
			           if(va==true) //ama to dwmatio einai elefthero den exoume thema me thn hmeromhnia...
			           {
			        	  
			        	    Date check_in = Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
			        	  Date ckeck_out=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+dian));
			           
			           
			        	   
			        	    
			        	   
			        	   
			        	   double roomrate=0;
			        	   String roomtype1 = null;
			        	   Statement st31 = conn.createStatement();//'"+tmp+"'
				            ResultSet result21 = st31.executeQuery("select roomtype from roomss where hotelID='"+hotel+"'");//blepw ean to dwmatio einai diathesimo
				            while (result21.next()) {
					    		roomtype1=result21.getString(1);
					    		
					    		
							}
				           st31.close();
				           System.out.println(roomtype1);
				           int roomrate11=0;
				           Statement st41 = conn.createStatement();//'"+tmp+"'
				            ResultSet result31 = st41.executeQuery("select rate from roomrate1 where roomtype='"+roomtype1+"' and hotelid='"+hotel+"' ");//blepw ean to dwmatio einai diathesimo
				            while (result31.next()) {
					    		roomrate11=result31.getInt(1);
					    		
							}
				           st41.close();
				           
				           String query = "UPDATE roomss SET  vacant=false WHERE idroom='"+dwmatio2+"' ";
							
							Statement st51 = conn.createStatement();
							st51.executeUpdate(query);
							st51.close();
				           
							Statement st6 = conn.createStatement(); //sindesi gia na parw stoixeia
						      ResultSet rs6 = st6.executeQuery("select count(idhotelbooking) from hotalbooking1");
						      int numbers=0;
						      while (rs6.next())
						      {
						         //System.out.print("Number of hotelbooking ");
						        // System.out.println(rs6.getString(1));
						          numbers = Integer.parseInt(rs6.getString(1));
						      } rs6.close();
						      st6.close();
				           
				           
				             CallableStatement sc=db.conn.prepareCall("{call add_hotelbooking(?,?,?,?,?,?,?)}");
				             CallableStatement cs=db.conn.prepareCall("{call add_roombooking(?,?,?,?,?,?,?)}");
				             sc.setInt(1, numbers);
				             sc.setDate(2, check_in);
				             sc.setDate(3,null);
				             sc.setDouble(4,1.0);
				            
				             sc.setInt(5, 1);
				             sc.setInt(6,1);
				             sc.setInt(7, client);
				             sc.executeUpdate();
				             
				             cs.setInt(1,numbers);
				             cs.setInt(2,dwmatio1);  //dwmatio1=roomid
				             
				             cs.setInt(3,client);
				             cs.setDate(4,check_in );
				             cs.setDate(5,ckeck_out );
				             cs.setDate(6, null);
				             cs.setInt(7, roomrate11);
				             cs.executeUpdate();
				             
				             
			           }
			           
		           
			           
			           if (ar_dwmatiwn==3)
			           {
				            Statement st511 = conn.createStatement();//'"+tmp+"'
				            ResultSet result211 = st511.executeQuery("select vacant from roomss where idroom='"+dwmatio2+"' ");//blepw ean to dwmatio einai diathesimo
				            while (result211.next()) {
					    		va=result211.getBoolean(1);
					    		
							}
				           st2.close();
				           
			        	   String roomtype11 = null;
			        	   Statement st311 = conn.createStatement();//'"+tmp+"'
				            ResultSet result311 = st311.executeQuery("select roomtype from roomss where hotelID='"+hotel+"'");//blepw ean to dwmatio einai diathesimo
				            while (result311.next()) {
					    		roomtype11=result311.getString(1);
					    		
					    		
							}
				           st311.close();
				          // System.out.println(roomtype11);
				           int roomrate111=0;
				           Statement st411 = conn.createStatement();//'"+tmp+"'
				            ResultSet result41 = st411.executeQuery("select rate from roomrate1 where roomtype='"+roomtype11+"' and hotelid='"+hotel+"' ");//blepw ean to dwmatio einai diathesimo
				            while (result41.next()) {
					    		roomrate111=result41.getInt(1);
					    		
							}
				           st411.close();
				           if(va==true) //ama to dwmatio einai elefthero den exoume thema me thn hmeromhnia...
				           {
				        	  
				        	   Date  check_in = Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
				        	     Date ckeck_out=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+dian));
				               
				        	   
				        	   System.out.println(check_in+ "   " + ckeck_out);
				        	   
				        	   
				        	    roomrate111=0;
				        	   String roomtype111 = null;
				        	   Statement st3111 = conn.createStatement();//'"+tmp+"'
					            ResultSet result2111 = st3111.executeQuery("select roomtype from roomss where hotelID='"+hotel+"'");//blepw ean to dwmatio einai diathesimo
					            while (result2111.next()) {
						    		roomtype111=result2111.getString(1);
						    		
						    		
								}
					           st3111.close();
					         //  System.out.println(roomtype111);
					           int roomrate1111=0;
					           Statement st4111 = conn.createStatement();//'"+tmp+"'
					            ResultSet result3111 = st4111.executeQuery("select rate from roomrate1 where roomtype='"+roomtype111+"' and hotelid='"+hotel+"' ");//blepw ean to dwmatio einai diathesimo
					            while (result3111.next()) {
						    		roomrate1111=result3111.getInt(1);
						    		
								}
					           st4111.close();
					           
					           String query1 = "UPDATE roomss SET  vacant=false WHERE idroom='"+dwmatio2+"' ";
								
								Statement st5111 = conn.createStatement();
								st5111.executeUpdate(query1);
								st5111.close();
					           
								Statement st61 = conn.createStatement(); //sindesi gia na parw stoixeia
							      ResultSet rs61 = st61.executeQuery("select count(idhotelbooking) from hotalbooking1");
							      int numbers1=0;
							      while (rs61.next())
							      {
							         System.out.print("Number of hotelbooking ");
							         System.out.println(rs61.getString(1));
							          numbers1 = Integer.parseInt(rs61.getString(1));
							      } rs61.close();
							      st61.close();
					           
					           
					             CallableStatement sc1=db.conn.prepareCall("{call add_hotelbooking(?,?,?,?,?,?,?)}");
					             CallableStatement cs1=db.conn.prepareCall("{call add_roombooking(?,?,?,?,?,?,?)}");
					             sc1.setInt(1, numbers1);
					             sc1.setDate(2, check_in);
					             sc1.setDate(3,null);
					             sc1.setDouble(4,1.0);
					            
					             sc1.setInt(5, 1);
					             sc1.setInt(6,1);
					             sc1.setInt(7, client);
					             sc1.executeUpdate();
					             
					             cs1.setInt(1,numbers1);
					             cs1.setInt(2,dwmatio1);  //dwmatio1=roomid
					             
					             cs1.setInt(3,client);
					             cs1.setDate(4,check_in );
					             cs1.setDate(5,ckeck_out );
					             cs1.setDate(6, null);
					             cs1.setInt(7, roomrate1111);
					             cs1.executeUpdate();}   
		           }
		         
		           }
		    	 
		         }
		   }   //telos for
		      

    
	timeEndControlU2=System.currentTimeMillis();
	totalTime=timeEndControlU2-timeStartControlU2;
	System.out.println("sinolikos xronos gia" + 100 + "meres:" + totalTime + " msec.");
		    	  
			  
}   //telos sinarthshs-telos---------------       U3      -----------------

    
    
//----------------------------------- U4 -----------------------------------------------

    public static void updates() throws Exception{
    	int hotelbooking=0;
    	long timeStartControlU2=System.currentTimeMillis();
		long timeEndControlU2=System.currentTimeMillis();
		
		long totalTime=0L;
    	ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
    	Statement stat = conn.createStatement();//'"+tmp+"'
        ResultSet res = stat.executeQuery("select count(idhotelbooking) from hotalbooking");//blepw ean to dwmatio einai diathesimo
        while (res.next()) {
        	hotelbooking=res.getInt(1);
    		
		}
       stat.close();
       
       double pososto=0.02*hotelbooking;
       if(pososto<1)
    	   pososto=1;
       timeStartControlU2=System.currentTimeMillis();
    for(int days=1;days<=50;days++)
      {
       for(int i=1;i<=pososto;i++)
       {    
    	    Date  date1=Date.valueOf(Integer.toString(2014)+"-"+Integer.toString(Calendar.getInstance().get(Calendar.MONTH))+"-"+Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
    	    String dat=date1.toString();
    	    int hotelbook=p.zipf(hotelbooking); 
    	    System.out.println("string " +dat);
    	    String query = "UPDATE hotalbooking SET  cancellationdate='"+dat+"'  WHERE idhotelbooking='"+hotelbook+"' ";
			Statement stat1 = conn.createStatement();
			stat1.executeUpdate(query);
			stat1.close();
			
			query = "UPDATE roombooking1 SET  cancellationdate='"+dat+"'  WHERE hotelbookingid='"+hotelbook+"' ";
			Statement stat2 = conn.createStatement();
			stat2.executeUpdate(query);
			stat2.close();
			
			
			int roomid=0;
			Statement stat3= conn.createStatement();//'"+tmp+"'
            ResultSet res1 = stat3.executeQuery("select roomid  from roombooking1 where hotelbookingid='"+hotelbook+"' ");//blepw ean to dwmatio einai diathesimo
            while (res1.next()) {
            	roomid=res1.getInt(1);
	    		
			}
            stat3.close();
            
            
            
            query = "UPDATE roomss SET  vacant=true  WHERE idroom='"+roomid+"' ";
			Statement stat4 = conn.createStatement();
			stat4.executeUpdate(query);
			stat4.close();
            
            
            
            
			
			
    	   
    	   
          }
       }
    timeEndControlU2=System.currentTimeMillis();
	totalTime=timeEndControlU2-timeStartControlU2;
	System.out.println("sinolikos xronos gia" + 100 + "meres:" + totalTime + " msec.");
    	
    }
    
    
  // telos gia....----------------------------------- U4 ----------------------------------------------- 
    
 //-----------------------------Q1------------------------------------
 public static void q1() throws Exception{
	 int[] epistrofh;
	 epistrofh = new int[1000];
	 ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
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
		int  c=0;
		//aritmhos clients
		Statement st1 = conn.createStatement();
        ResultSet rs_person = st1.executeQuery("select  count('idClient') from client");
        int number_person = 0;
        while (rs_person.next())
        {
           //System.out.print("Number of clients ");
           System.out.println(rs_person.getString(1));
           number_person = Integer.parseInt(rs_person.getString(1));
        } rs_person.close();
        st1.close();
        double pososto=0.2*number_person;
        if(pososto<1)
        	pososto=1;
        
        long timeStartControlU2=System.currentTimeMillis();
		long timeEndControlU2=System.currentTimeMillis();
		
		long totalTime=0L;
		timeStartControlU2=System.currentTimeMillis();
	for(int i=1;i<=50;i++){
		
		for(int ik=1;ik<=pososto;ik++){
			int a=p.zipf(9);
			String location=loc[a][0];
			//System.out.println(location);
			int meres=p.zipf(2);
			Statement stat3= conn.createStatement();//'"+tmp+"'
		     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1,roombooking1 where city='"+location+"' " +
		     		" and person1.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
		     		"order by idperson asc; ");
		    
		     
		     stat3.close();
							/*if(location.equals("Chania")==true)
							{	
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_chania,roombooking1 where city='"+location+"' " +
							     		" and person1_chania.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();}
							else if(location.equals("Edessa")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Edessa,roombooking1 where city='"+location+"' " +
							     		" and person1_Edessa.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							else if(location.equals("Larissa")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Larisa,roombooking1 where city='"+location+"' " +
							     		" and person1_Larisa.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							
							else if(location.equals("Volos")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Volos ,roombooking1 where city='"+location+"' " +
							     		" and person1_Volos.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							
							else if(location.equals("Thessaloniki")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Thessaloniki ,roombooking1 where city='"+location+"' " +
							     		" and person1_Thessaloniki.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							else if(location.equals("Hrakleio")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Hrakleio,roombooking1 where city='"+location+"' " +
							     		" and person1_Hrakleio.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							else if(location.equals("Rome")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Rome,roombooking1 where city='"+location+"' " +
							     		" and person1_Rome.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							else if(location.equals("Paris")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Paris,roombooking1 where city='"+location+"' " +
							     		" and person1_Paris.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							else if(location.equals("Milan")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Milan,roombooking1 where city='"+location+"' " +
							     		" and person1_Milan.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}
							
							else if(location.equals("Napoli")==true){
								 Statement stat3= conn.createStatement();//'"+tmp+"'
							     ResultSet res1 = stat3.executeQuery("select distinct idperson from person1_Napoli,roombooking1 where city='"+location+"' " +
							     		" and person1_Napoli.idperson=roombooking1.bookedforpersonid and roombooking1.checkout-roombooking1.checkin='"+meres+"' " +
							     		"order by idperson asc; ");
							    
							     
							     stat3.close();
								
							}*/
							
	
	
	
	 }  //telos for mikrhs
}
		timeEndControlU2=System.currentTimeMillis();
		totalTime=timeEndControlU2-timeStartControlU2;
		System.out.println(totalTime+ "ms");
 }
 //----------------------------telos Q1-------------------------------------------------------
 
 //------------------------------------Q2-------------------------------------------------------
 public static void q2() throws Exception{
     for(int i=1;i<100;i++){
    	 //System.out.println("mia nea anazitisi xekina.....");
	 Statement stat3= conn.createStatement();//'"+tmp+"'
     ResultSet res1 = stat3.executeQuery("with krathseis as(select COUNT(idhotelbooking)as " +
     		"c1,idperson from person1,hotalbooking1 where person1.idperson=hotalbooking1.bookedbyclientID " +
     		"and hotalbooking1.reservationdate='2014-04-27' group by person1.idperson order by idperson asc)" +
     		"select distinct fname,lname ,reservationdate,c1 from person1,hotalbooking,krathseis where c1>1 and person1.idperson=krathseis.idperson");
     while (res1.next())
     {
      
       // System.out.println(res1.getString(1)+" " + res1.getString(2) + " " + res1.getString(3) + " " + res1.getString(4));
        
     } res1.close();
  
     
     stat3.close(); }}
    
    
    
    
    
    
    
    
    
    
    
    
    
    //se periptwsh pou xreiastei
    
  public static void insert_person_employee_cc() throws Exception  {
    	PreparedStatement cs3 ;
    	 newdtb4 db = new newdtb4(); 
    	
    	ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
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
    	try{
    		  cs3=db.conn.prepareCall("{call add_person_emp(?,?,?,?,?,?,?,?,?,?,?)}");
	
		int j=1;
		int count=1;	//Metrame posous exoume valei gia na allazoume ta idperson (j)
		for(int hotelid=1;hotelid<=100;hotelid++) {
			int employeecount=10+p.zipf(10);
			int managercount=2+p.zipf(4);
			for(j=1;j<=employeecount;j++) {
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
				count=count+1;
				if(count==100)
					break;
			}
			if(count==100)
				break;
		}
    	}
    	catch(SQLException ex){
			ex.printStackTrace();
		}

}
    
    
    
    
	
    
    
    
    
    public static long insert_person_one_day(int counter) throws Exception{
		long t0,t1;
		 
		long dt=ins_client(counter); //eisagwgh - client....
		 
		
		System.out.println("Update 1 excecuted at:" + dt +" ms.");	
		System.out.println("Mean time to insert random names/last_names into table person:" + dt +" msec.");
        return dt;
	}
    
    
    
   //---------------------------------------------------------------------gia to U1-------------------------------------------------------------------------
    public static int insert_person_many_day() throws Exception{
    	ZipfGenerator p=new ZipfGenerator(new Random(System.currentTimeMillis()));
    	int days=100;
    	long time=0L;
    	System.out.println("arithmos hmerwn:"+ days);
    	
    	
        
    	
    	for(int i=1;i<=50;i++)
    	{      
    		//-- from persons--//
            Statement st1 = conn.createStatement();
            ResultSet rs_person = st1.executeQuery("select  count(idperson ) from person1");
            int number_person = 0;
            while (rs_person.next())
            {
               System.out.print("Number of person ");
               System.out.println(rs_person.getString(1));
               number_person = Integer.parseInt(rs_person.getString(1));
            } rs_person.close();
            st1.close();
            int counter=number_person;
    		time=time+insert_person_one_day(counter);
    		
    		
    	}
    	System.out.println("sinolikos xronos gia" + 50 + "meres:" + time + " msec.");
    	return 100*days;
    	
    }
  //---------------------------------------------------------------------telos gia to U1-------------------------------------------------------------------------



}//kleinei h klash...
