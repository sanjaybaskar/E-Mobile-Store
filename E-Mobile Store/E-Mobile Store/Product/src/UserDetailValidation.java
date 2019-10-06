import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserDetailValidation {
	String name;
	long mobile;
	String email;
	int buildingno;
	String street;
	String district;
	int pincode;
	String state;
	int billNumber = 0;
	
	Scanner input = new Scanner(System.in);
	public void userDetailvalidate() {
	System.out.println("----------------------*CUSTOMER DETAILS*--------------------");
	
		System.out.print("\nEnter your name: ");
		 name = input.nextLine();
		Pattern namepattern = Pattern.compile("^[a-z||A-Z||\\s]{6,14}$");
		Matcher namematch = namepattern.matcher(name);
		
		if(namematch.matches())
			
		{  System.out.println("\nCustomer name:"+name); }

		else
	
		{ System.out.println(" Invalid input !!"); }
	
	
	
		System.out.println("\nEnter your Mobile Number: ");
		 mobile = input.nextLong();
		Pattern mobilepattern = Pattern.compile("^[7-9][0-9]{9}");
		Matcher mobilematch = mobilepattern.matcher(mobile);
		
	if(mobilematch.matches())
		
			{  System.out.println("\nMobile Number :"+mobile); }
	
	else
			{ System.out.println(" Invalid input !!"); }
		
	
	
	
		System.out.println("\nEnter your  Email Id: ");
		 email = input.nextLine();
		Pattern emailpattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		Matcher emailmatch = emailpattern.matcher(email);
		
	if(emailmatch.matches())
		
		{  System.out.println("\nEmail Id :"+email); }
	
	else
			 { System.out.println(" Invalid input !!"); }
		
	
	
	
		System.out.println("\nEnter your Resident Address : ");
		 buildingno = input.nextInt();
		Pattern addresspattern = Pattern.compile("^[a-zA-Z0-9\\s]$");
		Matcher match3=addresspattern.matcher(buildingno);
		
		if(match3.matches())
			
		{  System.out.println("\nCustomer Address :"+address);}
				
		else
			
		{ System.out.println(" Invalid input !!"); }
	
	
	}}
	

