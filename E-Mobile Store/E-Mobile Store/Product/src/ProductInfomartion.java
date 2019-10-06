import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

// Product Information class
// User Menu
// Display Products
// Adding products into the product list
// Update Mobile price
class ProductInformation {
	
	 	int choice;
		Scanner input = new Scanner(System.in);
		BufferedReader bufferreader = new BufferedReader(new InputStreamReader(System.in));
		ProductCart  productcart = new ProductCart();
		
				
	public void option()
	{
		System.out.println("\n\n--------------------**E-Mobile Store**------------------------------");
		System.out.println("Are you a Customer or Seller ?");	
		System.out.println("1) Customer"  +"\n" +"2) Seller");
	
	
	 try {
			Menu:
				for(int count=1; count<=5;count++)
				{
					System.out.println("\nEnter the option :");
					choice =input.nextInt();
					switch(choice)
	   					{
	   					case 1: getCustomerMenu();
	   					break;
	   					case 2: getSellerMenu();
	   					break;
	   					default: System.out.println(" Please give your inputs properly !!");
	   					}
	   		continue Menu;
				}
    	}catch(InputMismatchException inputmismatchexception)
    			{	
    				System.out.println(" Please give your input properly "); 	
    			}
  }
	// Customer Menu method
	
	public void getCustomerMenu() 
	{
		 System.out.println("Menu:");
		 System.out.println("1) Product Information" + "\n"
		    		    + "2) Add item to shopping cart" + "\n"
		    			+ "3) Delete item from shopping cart" + "\n"
		    			+ "4) Order list"+"\n"
		    			+ "5) Exit\n");
		 Menu:
		    for(int counter=1;counter<=5;counter++) 
		    {
		    	 System.out.println("\nEnter the option :");		
		    	 int menuoption =input.nextInt();
		    	 switch (menuoption)
		    	 {
		    	 case 1: getDetail();
		    	 break;
		    	 case 2: productcart.addToCart();
		    	 break;
		    	 case 3: productcart.deleteItem();
		    	 break;
		    	 case 4: productcart.getCartDetail();
		    	 break;
		    	 case 5:  System.out.println("\nThank you for Shopping !!");
		    	 break;
		    	 default: System.out.println(" Enter the correct option!! ");	
		    	 }
		continue Menu;
			}
	}
	
	// Seller Menu method 
	 
	public void getSellerMenu() 
	{
		System.out.println("\nMenu:");
	    System.out.println("1) Update item" + "\n"
	    		    + "2) Add item to product list" + "\n"
		            + "3) Exit");
	    
	    int option = input.nextInt();
	    switch(option)
	    {
	    case 1: updatePrice();
	    	break; 
	    case 2: addItem();
	    	break;
	    case 3: System.out.println("\nThank you for the Updation !!");
	    	break;
	    default: System.out.println("Please give your inputs properly !!");
	    }
			
	}
	
	
	
	// Jdbc Connection method
	
	public static Connection getconnection() 
	{
		try{
			Connection connection=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Product","sa","aspire@123");
			return connection;
			} catch(Exception expception)
				{	
					System.out.println(expception);	
				}
		return null;
	 }	
		
	
	
	// Product Details method
	
	public void getDetail() 
	 {
		 Connection connection=getconnection(); 
		 try {  
			 	System.out.println("\nModel No  Model Name  Price           Specifications         Stock  \n"); 	
	 		 	Statement stmt=connection.createStatement();
			 	ResultSet resultset=stmt.executeQuery("select * from Mobile");  
			 	while(resultset.next())  
			 	System.out.println(resultset.getInt("model_no")+"        "+resultset.getString("name")+"    "+resultset.getInt("price")+"         "+resultset.getString("specifications")+"            "+resultset.getInt("stock"));  
			 	connection.close();  
		 	}
		 	catch(Exception exception)
		 		{ 
		 			System.out.println(exception);
		 		} 
	 
	 }
	
	//AddItem method
	
	public void addItem() 
	{
		Connection connection=getconnection();
		try {
					
			System.out.println("\nEnter Mobile Brand :");
			String name = bufferreader.readLine();
		
			System.out.println("\nEnter Mobile Price :");
			int price =Integer.parseInt(bufferreader.readLine());
		
			System.out.println("\nEnter Mobile Specification :");
			String specifications = bufferreader.readLine();
		
			System.out.println("\nEnter Mobile Stock :");
			int stock = Integer.parseInt(bufferreader.readLine());
	
			try {
					PreparedStatement preparedStatement = connection.prepareStatement( "insert into Mobile values (?,?,?,?)");
					preparedStatement.setString(1,name);
					preparedStatement.setInt(2,price);
					preparedStatement.setString(3,specifications);
					preparedStatement.setInt(4,stock);
					int count = preparedStatement.executeUpdate();
			    	 System.out.println(count+" Data stored ");
			    	 connection.close();
				}   catch(SQLException sqlexpception)
						{	
							System.out.println(" Data Not stored");	
						}
		 
		}	catch(IOException ioexpception)
				{	
					System.out.println(" Exception occured");
				}
		
	}
	
	
	// Update Price method
			
		public void updatePrice()  
		{    
			Connection connection=getconnection();
			try {
					System.out.println("\nEnter the Model Number:");
					int model_no = Integer.parseInt(bufferreader.readLine());
					System.out.println("\nEnter the Updated Price");
					int price =Integer.parseInt(bufferreader.readLine());
					try {
							 PreparedStatement preparedStatement = connection.prepareStatement( " update Mobile set price =? where model_no = ?");
							 preparedStatement.setInt(2,model_no);
							 preparedStatement.setInt(1, price);
				 			 int count = preparedStatement.executeUpdate();
				 			 System.out.println(count+" Data updated ");
				 			 connection.close();
						}catch(Exception expception)
							{
								System.out.println(expception);
							}
				}catch(IOException ioexpception)
					{
						System.out.println(ioexpception);
					}
		}
	

}
	
   

