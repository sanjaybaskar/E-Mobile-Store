import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

// User Detail validation Class 

public class UserDetail {
		String name;
		long mobile;
		String email;
		int buildingno;
		String street;
		String district;
		int pincode;
		String state;
		int billNumber = 0;
		BufferedReader bufferreader = new BufferedReader(new InputStreamReader(System.in));
	
	//User Detail method	
		
	public void getUserDetail()  
	{
		try {
		
			System.out.println("----------------------*CUSTOMER DETAILS*--------------------");
		
			System.out.print("\nEnter your name: ");
			name = bufferreader.readLine();
				
			System.out.println("\nEnter your Mobile Number: ");
			mobile = Long.parseLong(bufferreader.readLine());
		
			System.out.println("\nEnter your  Email Id: ");
			email = bufferreader.readLine();
		
			System.out.println("\nEnter your Resident Address  ");	
		
			System.out.println("\nBuilding No : ");
			buildingno = Integer.parseInt(bufferreader.readLine());
			
			System.out.println("\nEnter street / Area :");
			street = bufferreader.readLine();
		
			System.out.println("\nDistrict  : ");
			district = bufferreader.readLine();
						
			System.out.println("\nState : ");
			state = bufferreader.readLine();
			 
			System.out.println("\nPin Code - ");
			pincode = Integer.parseInt(bufferreader.readLine());
			
			try {
					Connection connection=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Product","sa","aspire@123"); 
					String sql = "insert into Customer values (?,?,?,?,?,?,?,?)";
				 
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
				
					preparedStatement.setString(1,name);
					preparedStatement.setLong(2,mobile);
					preparedStatement.setString(3,email);
					preparedStatement.setInt(4,buildingno);
					preparedStatement.setString(5,street);
					preparedStatement.setString(6,district);
					preparedStatement.setString(7,state);
					preparedStatement.setInt(8,pincode);
				 
					int count = preparedStatement.executeUpdate();
					billNumber = billNumber + 1;
					System.out.println(count+" Information stored");
					System.out.println("Your Bill Number is :"+billNumber);
					connection.close();
				}catch(SQLException sqlexpception)
					{
						System.out.println("Information not stored ");
					}
		}catch(IOException ioexpception)
			{
				System.out.println(" IO Exception Occured ");
			}
	
	}
}


