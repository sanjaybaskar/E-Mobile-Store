import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;


// Product Cart class
// In product class we are adding product to cart
// deleting the added product in cart
// display Invoice summary
public class ProductCart {
	
	String brandname;
	int quantity;
	int productid;	
	int itemCount;
	int billno;
	int totalprice;
	Date orderdate;
	Date deliverydate;
	
	UserDetail userdetail = new UserDetail();
	BufferedReader bufferreader = new BufferedReader(new InputStreamReader(System.in));
	
	 // Database connection method
	
	public static Connection getconnection() 
	{
		try {
				Connection connection=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Product","sa","aspire@123");
				return connection;
			}catch(Exception exp)
				{
					System.out.println(exp);
				}
	return null;
	}	
	
		// addToCart method

		public void addToCart()  
		{ 
			Connection connection=getconnection();
			try {
				
					System.out.println("\nEnter the item you want to add cart : ");
					brandname = bufferreader.readLine();
			
					System.out.println("\nEnter Number of Pieces you want :");
					quantity =Integer.parseInt(bufferreader.readLine());
			 
					System.out.println("\nEnter Model Number :");
					productid =Integer.parseInt(bufferreader.readLine());
			  
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 2);
					String newDate = sdf.format(cal.getTime()); 
			  
			 
					try { 
				 
							String sql = "insert into Cart values(?,?,?,?,?,?)";
			 
							PreparedStatement preparedStatement = connection.prepareStatement(sql);
			  
							preparedStatement.setString(1,brandname);
							preparedStatement.setInt(2,quantity);
							preparedStatement.setInt(3,productid);
							preparedStatement.setDate(4,new java.sql.Date(System.currentTimeMillis()));
							preparedStatement.setString(5,newDate);
							preparedStatement.setInt(6,totalprice);

							int count = preparedStatement.executeUpdate();
			  				System.out.println(count+" Data stored to database ");
							connection.close();
						 
						}catch(SQLException sqlexception)
							{ 
								System.out.println(sqlexception);
							}
					
				}catch(Exception exception)
					{ 
						System.out.println(exception);
					}
			
		userdetail.getUserDetail();
		}
		
		// deleteItem method
		// delete an Item from the cart 
		public void deleteItem()  
		{ 
			 Connection connection=getconnection();
			 try {
				 	System.out.println("\nEnter the Model you want to delete :");
				 	String	brandname = bufferreader.readLine();
			
				 	try {
				 			PreparedStatement preparedStatement = connection.prepareStatement( " delete from Cart where brandname=?;");			 
				 			preparedStatement.setString(1,brandname);
				 			int count = preparedStatement.executeUpdate();
				 			System.out.println(count+" Data deleted ");
				 			connection.close(); 
				 		}catch(SQLException sqlexpception)
				 			{
				 				System.out.println("Please give your inputs properly !! ");
				 			}
		
			 	}catch(Exception expception) 
			 		{
			 			System.out.println("Exception occured");
			 		}
			
		}
		
		//getTotalPrice method
		// to get the total price of the bill
		
		public void getTotalPrice()                
		{
			
			  	try {
			  			System.out.println("\nEnter the billno to get Total Price :");
			  			billno = Integer.parseInt(bufferreader.readLine());
			  			try
			  			{
			  				Connection connection=getconnection();
			  				System.out.println("\tTotal Price : "); 	
			  				String sql ="select c.billno, Sum(c.quantity*m.price) as totalprice  from Cart c left Join Mobile m  on m.model_no=c.productid where c.billno = ? group by c.billno,c.billno";  
			  				PreparedStatement preparedStatement = connection.prepareStatement(sql);
			  				preparedStatement.setInt(1,billno);
			  				ResultSet resultset;
			  				resultset= preparedStatement.executeQuery(); 
			  				while(resultset.next())
			  				{
			  					totalprice = resultset.getInt(2);
			  					billno = resultset.getInt(1);
					 
			  					System.out.println("\tBillNumber   Total Price");
			  					System.out.println("\t"+resultset.getInt("billno")+"             "+resultset.getInt("totalprice")); 
			  				}
			   				connection.close();  
			  			}catch(SQLException expception)
			  				{  
			  					expception.printStackTrace(); 
			  				}
			  		}catch(IOException ioexpception)
			  			{
			  				ioexpception.printStackTrace();
			  			}
			 
		}
		
		// getCartDetail method
		// to display the invoice summary
		
		public void getCartDetail()
		{
			try 
				{
				 System.out.println("Enter the Bill No :");
				 billno = Integer.parseInt(bufferreader.readLine());
				 try{  
				 	
					 	Connection connection =getconnection(); 
					 	String sql ="select * from Customer ctmr left join Cart c on c.billno = ctmr.billno where c.billno = ?";
					 	PreparedStatement  preparedStatement = connection.prepareStatement(sql);
					 	preparedStatement.setInt(1,billno);
					 	ResultSet resultset = preparedStatement.executeQuery();  
					 	while(resultset.next())
					 	{ 
					 		System.out.println("-------------------------** INVOICE SUMMARY **----------------------------------");
					 		System.out.println("\n Name |    Mobile   |       Email        | House number | Street | District | State | Pincode |      "); 
					 		System.out.println("\n"+resultset.getString("name")+"  "+resultset.getInt("mobile")+"  "+resultset.getString("email")+"       "+resultset.getInt("buildingno")+"        "+resultset.getString("street")+"    "+resultset.getString("district")+" "+resultset.getString("state")+"  "+resultset.getInt("pincode"));
					 		System.out.println("\nBill No | Model Name | Number of pieces | Model number | Ordered Date | Delivery Date |"); 
					 		System.out.println("\n"+  resultset.getInt("billno")+"           "+resultset.getString("brandname")+"            "+resultset.getInt("quantity")+"                "+resultset.getInt("productid")+"          "+resultset.getDate("orderdate")+"     "+resultset.getDate("deliverydate"));  
				
					 		getTotalPrice();
					 	}
					 	connection.close();
				 	}catch(Exception exception)
				 		{  
				 			System.out.println(exception);
				 		}  
				 
				}catch(IOException ioexpception) 
					{ 
						System.out.println(ioexpception);
					}
				getTotalPrice();
		}
			
		}

		

			
		
	

