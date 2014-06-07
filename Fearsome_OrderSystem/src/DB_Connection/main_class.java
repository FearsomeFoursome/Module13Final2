/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import Msg_Displays.Menu;
/**
 *
 * @author Amy Roberts
 */
public class main_class 
{
	public static void main(String[] args)
	{
		//variables
		String input;
		char choice;
		boolean repeat = true;
		
		//initialize database connections
		//initialize tables
		//load data to tables
		
		//display main menu
		System.out.println("Welcome to the store, James!\n");
		Menu.main_menu();
		
		//take user input and switch based on choice
		try
		{
			while(repeat = true)
			{
				BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
				input = brin.readLine();
				choice = input.charAt(0);
				
				System.out.println(choice);
				switch(choice)
				{
					case '1':
						Menu.browse_menu();
						break;
					case '2':
						Menu.view_cart();
						break;
					case '3':
						Menu.place_order();
						break;
					case 'X':
						System.out.println("Goodbye! Thank you for shopping with us!");
						repeat = false;
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, invalid selection. Please select an option from the menu above: ");
						break;
				} //end switch
			} //end while loop
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch
		
		
	}
}
