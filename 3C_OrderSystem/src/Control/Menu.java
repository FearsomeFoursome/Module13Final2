/*
 * 3's Company (Amy Roberts, Bella Belova, Scott Young)
 * "We pledge that we have complied with the AIC in this work."
 *
 * Class containing all of the menu functions and logic
 */
package Control;

import static Databases.OrdersDB.*;
import Objects.Address;
import Objects.Customer;
import Objects.Order;
import Objects.OrderItem;
import Objects.Product;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A class which holds all of the logic for the program's menus.
 * <p>
 * This class contains the text of all user-facing menus, as well as the required
 * logic to take user input and execute the requested functions.
 *
 * @author Amy Roberts
 */
public class Menu {
	
	//variable which controls breaking back to main menu
	private static boolean returntomain = false;
	
	//variables to control loops and switches
	private static boolean repeat;
	private static String input;
	private static char choice;
	private static BufferedReader brin = new BufferedReader(new InputStreamReader(System.in));
	private static Order cart;
	
	/**
	 * Presents the main menu options.
	 */
	public static void main_menu()
	{
		System.out.println("Main menu:\n");
		System.out.println("1. Browse catalog");
		System.out.println("2. View shopping cart");
		System.out.println("3. Place order");
		System.out.println("X. Exit program\n");
		System.out.println("Please enter your selection: ");
	} //end main_menu
	
	/**
	 * Presents the full catalog for browsing and selection.
	 * @param current_cart The Order object being used to store the current cart.
	 */
	public static void browse_catalog(Order current_cart)
	{
		try
		{
			returntomain = false;
			cart = current_cart;
			ArrayList<Product> allProducts;
			Product temp;
			System.out.println("Full catalog:\n");

			allProducts = Databases.ProductDB.getAllProducts();
			
			System.out.println("Product ID - Product Name - Product Price - Stock Status");
			
			for(int x = 0; x < allProducts.size(); x++)
			{
				temp = allProducts.get(x);
				String stockstatus = "";
				int currentstock = Databases.StockItemsDB.searchforStockQTY(temp.getProductID());
				
				if(currentstock == 0)
				{
					stockstatus = "Out of Stock";
				} //end if
				else
				{
					stockstatus = "In Stock";
				} //end else
				System.out.println((x + 1) + ". " + temp.getProductID() + " - " + temp.getProductName() + 
						  " - " + temp.getProductPrice() + " - " + stockstatus);
								
				} //end for	
			System.out.println("\nM. Main menu");
			System.out.println("X. Exit program\n");
			System.out.println("Which product are you interested in? Please make a selection: ");

			//take user input and switch based on choice
			try
			{
				repeat = true;
				while(repeat = true) 
				{
					input = brin.readLine();
					input = input.toUpperCase();
					choice = input.charAt(0);

					switch(choice)
					{
						//for cases 1-9, get product ID from the product object at the right place
						//in the array and pass to product_details() - we don't use all of these;
						//case 0 would be "next page"
						case '1':						
							temp = allProducts.get(0);
							product_details(cart, temp.getProductID());
							break;
						case '2':
							temp = allProducts.get(1);
							product_details(cart, temp.getProductID());
							break;
						case '3':
							temp = allProducts.get(2);
							product_details(cart, temp.getProductID());
							break;
						case '4':
							temp = allProducts.get(3);
							product_details(cart, temp.getProductID());
							break;
						case '5':
							temp = allProducts.get(4);
							product_details(cart, temp.getProductID());
							break;						
						case '6':
							temp = allProducts.get(5);
							product_details(cart, temp.getProductID());
							break;
						case '7':
							temp = allProducts.get(6);
							product_details(cart, temp.getProductID());
							break;
						case '8':
							temp = allProducts.get(7);
							product_details(cart, temp.getProductID());
							break;
						case '9':
							temp = allProducts.get(8);
							product_details(cart, temp.getProductID());
							break;
						case 'M':
							main_menu();
							repeat = false;
							returntomain = true;
							break;
						case 'X':
							System.out.println("Goodbye! Thank you for shopping with us!");
							repeat = false;
							System.exit(0);
							break;
						default:
							System.out.println("Sorry, invalid selection. Please select an option from the menu above: ");
					} //end switch

					if(returntomain == true)
					{
						return;
					} //end if

				} //end while loop
			} //end try
			catch (Exception e)
			{
				System.err.println("Error: " + e);
			} //end catch
		} //end try
		catch (Exception e)
		{
			System.out.println("Error: " + e);
		} //end catch
	} //end browse_menu
	
	
	/**
	 * Presents the details on a specific product and allows selecting a quantity to order.
	 * @param current_cart The Order object being used to store the current cart.
	 * @param prodID The ID number of the product the customer has indicated interest in.
	 */
	public static void product_details(Order current_cart, int prodID)
	{
		try
		{
			returntomain = false;
			cart = current_cart;
			System.out.println("Product details:\n");
			
			//reformat and display full product details
			Product currentproduct = Databases.ProductDB.getProductbyID(prodID);
			System.out.println("Product ID: " + currentproduct.getProductID());
			System.out.println("Product name: " + currentproduct.getProductName());
			System.out.println("Product price: " + currentproduct.getProductPrice());
			System.out.println("Product description:");
			System.out.println(currentproduct.getProductDesc());
			
			String stockstatus;
			int currentstock = Databases.StockItemsDB.searchforStockQTY(currentproduct.getProductID());
				
			if(currentstock == 0)
			{
				stockstatus = "out of stock";
			} //end if
			else
			{
				stockstatus = "in stock";
			} //end else
			
			System.out.println("This product is currently " + stockstatus + '\n');
			
			System.out.println("B. Browse product catalog");
			System.out.println("C. View shopping cart");
			System.out.println("M. Main menu");
			System.out.println("X. Exit program\n");
			System.out.println("Please enter how many of this product you would like to order, or make a menu selection.");
			System.out.println("What is your selection?");

			//take user input and switch based on choice
			try
			{
				repeat = true;
				while(repeat = true) 
				{
					input = brin.readLine();
					input = input.toUpperCase();
					choice = input.charAt(0);

					switch(choice)
					{
						case 'B':
							browse_catalog(cart);
							break;
						case 'C':
							view_cart(cart);
							break;
						case 'M':
							main_menu();
							repeat = false;
							returntomain = true;
							break;
						case 'X':
							System.out.println("Goodbye! Thank you for shopping with us!");
							repeat = false;
							System.exit(0);
							break;
						default:
							if(choice >= '1' && choice <= '9')
							{
								if(stockstatus.equals("out of stock"))
								{
									System.out.println("Sorry, this product is out of stock and cannot be ordered. Please make a menu selection:");
								} //end if
								else
								{
									int quant = Character.getNumericValue(choice);
									//extract prodID, price, prodname from the temporary product object and create new orderitem object
									OrderItem item = new OrderItem(currentproduct.getProductID(), quant, currentproduct.getProductPrice(), currentproduct.getProductName());
									cart.addOrderItem(item);

									System.out.println(choice + " products added to order. Please make a menu selection:");
								} //end else
								
								System.out.println("B. Browse product catalog");
								System.out.println("C. View shopping cart");
								System.out.println("M. Main menu");
								System.out.println("X. Exit program\n");
								System.out.println("What is your selection?");
							} //end if
							else if(choice == '0')
							{
								System.out.println("You cannot order 0 of a product. Please retry.");
							}
							else
							{
								System.out.println("Sorry, invalid selection. Please select an option from the menu above or a number of products to order: ");
							}					

					} //end switch

					if(returntomain == true)
					{
						return;
					} //end if

				} //end while loop
			} //end try
			catch (Exception e)
			{
				System.err.println("Error: " + e);
			} //end catch
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch	
			
	} //end product_details
	
	
	/**
	 * Displays the contents of the cart.
	 * @param current_cart The Order object being used to store the current cart.
	 */
	public static void view_cart(Order current_cart)
	{
		returntomain = false;
		boolean cart_empty = false;
		cart = current_cart;
		System.out.println("Your cart:\n");
		
		//create temporary pointer to arraylist in cart object for use
		ArrayList<OrderItem> orderItemList = cart.getOrderItems();
		
		//set cart_empty flag and present appropriate message if cart is empty
		if (0 == orderItemList.size())
		{
			System.out.println("Your cart is empty!\n");
			cart_empty = true;
		} //end if
		else
		{
			System.out.println("Product name - product quantity - price per unit - total price");
				
			for(int x = 0; x < orderItemList.size(); x++)
			{
				OrderItem temp = orderItemList.get(x);
				System.out.println((x + 1) + ". " + temp.getProductName() + " - " + temp.getProductQuant() + 
						  " - $" + temp.getProductPrice() + " - $" + (temp.getProductQuant() * temp.getProductPrice()));
			} //end for
			
		} //end else
				
		System.out.println("\nCurrent total: $" + cart.calcOrderTotal());
		System.out.println("P. Place order");
		System.out.println("M. Main menu");
		System.out.println("X. Exit program\n");
		System.out.println("Please select a line number to modify that item, or make a menu selection.");
		System.out.println("What is your selection?");
		
		//take user input and switch based on choice
		try
		{
			repeat = true;
			while(repeat = true) 
			{
				input = brin.readLine();
				input = input.toUpperCase();
				choice = input.charAt(0);
				
				OrderItem temp;
				switch(choice)
				{
					//for numerical cases: create a pointer variable to indicate which
					//object in the cart was selected and pass to modify_cart_item
					//case 0 would be "next page"
					case '1':
						temp = orderItemList.get(0);
						modify_cart_item(cart, temp);
						break;
					case '2':
						temp = orderItemList.get(1);
						modify_cart_item(cart, temp);
						break;
					case '3':
						temp = orderItemList.get(2);
						modify_cart_item(cart, temp);
						break;
					case '4':
						temp = orderItemList.get(3);
						modify_cart_item(cart, temp);
						break;
					case '5':
						temp = orderItemList.get(4);
						modify_cart_item(cart, temp);
						break;
					case '6':
						temp = orderItemList.get(5);
						modify_cart_item(cart, temp);
						break;
					case '7':
						temp = orderItemList.get(6);
						modify_cart_item(cart, temp);
						break;
					case '8':
						temp = orderItemList.get(7);
						modify_cart_item(cart, temp);
						break;
					case '9':
						temp = orderItemList.get(8);
						modify_cart_item(cart, temp);
						break;
					case 'P':
						//confirm the cart is not empty before calling place_order
						if(cart_empty == false)
						{
							place_order(cart);
						} //end if
						else
						{
							System.out.println("Sorry, you cannot place an order with an empty cart.");
						} //end else
						break;
					case 'M':
						main_menu();
						repeat = false;
						returntomain = true;
						break;
					case 'X':
						System.out.println("Goodbye! Thank you for shopping with us!");
						repeat = false;
						System.exit(0);
						break;
					default:						
						System.out.println("Sorry, invalid selection. Please select an option from the menu above or an item to modify: ");				
						
				} //end switch
				
				if(returntomain == true)
				{
					return;
				} //end if
				
			} //end while loop
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch
		
	} //end view_cart
	
	/**
	 * Presents options which allow modifying the quantity of an ordered item or removing it from the cart. 
	 * @param current_cart The Order object being used to store the current cart.
	 * @param current_item The OrderItem object being modified or removed.
	 */
	public static void modify_cart_item(Order current_cart, OrderItem current_item)
	{
		returntomain = false;
		cart = current_cart;
		System.out.println("Selected cart item:\n");
		
		OrderItem currentitem = current_item;
		
		System.out.println("Product ID: " + currentitem.getProductID());
		System.out.println("Product name: " + currentitem.getProductName());
		System.out.println("Price per unit: $" + currentitem.getProductPrice());
		System.out.println("Quantity in cart: " + currentitem.getProductQuant());
		System.out.println("Total price for this quantity: $" + (currentitem.getProductPrice() * currentitem.getProductQuant()) + '\n');		
		
		System.out.println("1. Remove this item from cart");
		System.out.println("2. Change quantity of item in cart");
		System.out.println("C. View shopping cart");
		System.out.println("M. Main menu");
		System.out.println("X. Exit program\n");
		
		//take user input and switch based on choice
		try
		{
			repeat = true;
			while(repeat = true) 
			{
				input = brin.readLine();
				input = input.toUpperCase();
				choice = input.charAt(0);

				switch(choice)
				{
					case '1':
						//remove product from cart
						cart.removeOrderItem(currentitem.getProductID());
						System.out.println("This item has been removed from your cart.");
						break;
					case '2':
						System.out.println("How many of this product would you like?");
						boolean i = true;
						while(i == true)
						{
							input = brin.readLine();
							char quantinput = input.charAt(0);
							int quant = Character.getNumericValue(quantinput);
							if (quant == 0)
							{
								cart.removeOrderItem(currentitem.getProductID());
								System.out.println("This item has been removed from your cart. Please make a menu selection.");
								i = false;
							} //end if
							else {
								if(quant >= 1)
								{
									//update quantity of item
									cart.changeQuantity(currentitem.getProductID(), quant);
									System.out.println("You now have " + quant + " of this item in your cart.");	
									i = false;
								} //end if
								else
								{
									System.out.println("Sorry, invalid entry. Please re-enter the quantity of this item you would like.");
								} //end inner else
							} //end outer else
						} //end while						
						break;
					case 'C':
						view_cart(cart);
						break;
					case 'M':
						main_menu();
						repeat = false;
						returntomain = true;
						break;
					case 'X':
						System.out.println("Goodbye! Thank you for shopping with us!");
						repeat = false;
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, invalid selection. Please select an option from the menu above: ");
				} //end switch
				
				if(returntomain == true)
				{
					return;
				} //end if
				
			} //end while loop
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch
		
	}
	
	/**
	 * Presents the cart and begins the procedure to finalize an order.
	 * @param current_cart The Order object being used to store the current cart.
	 */
	public static void place_order(Order current_cart)
	{
		returntomain = false;
		boolean cart_empty = false;
		cart = current_cart;
		System.out.println("Place order:\n");
		
		//copy arraylist out of cart for use
		ArrayList<OrderItem> orderItemList = cart.getOrderItems();
		
		//set cart_empty flag and present appropriate message if cart is empty
		if (orderItemList.isEmpty())
		{
			System.out.println("Your cart is empty!\n");
			cart_empty = true;
		} //end if
		else
		{
			System.out.println("Product name - product quantity - price per unit - total price");
				
			for(int x = 0; x < orderItemList.size(); x++)
			{
				OrderItem temp = orderItemList.get(x);
				System.out.println((x + 1) + ". " + temp.getProductName() + " - " + temp.getProductQuant() + 
						  " - $" + temp.getProductPrice() + " - $" + (temp.getProductQuant() * temp.getProductPrice()));
			} //end for
			
		} //end else
		
		System.out.println("\nCurrent total: $" + cart.calcOrderTotal());
		System.out.println("1. Make changes to this order");
		System.out.println("2. Place this order");
		System.out.println("M. Main menu");
		System.out.println("X. Exit program\n");
		System.out.println("Please make a selection: ");
		
		//take user input and switch based on choice
		try
		{
			repeat = true;
			while(repeat = true) 
			{
				input = brin.readLine();
				input = input.toUpperCase();
				choice = input.charAt(0);
				
				switch(choice)
				{
					case '1':
						view_cart(cart);
						break;
					case '2':
						if(cart_empty == false)
						{
							confirm_order(cart);
						} //end if
						else
						{
							System.out.println("Sorry, you cannot place an order with an empty cart.");
						} //end else
						break;
					case 'M':
						main_menu();
						repeat = false;
						returntomain = true;
						break;
					case 'X':
						System.out.println("Goodbye! Thank you for shopping with us!");
						repeat = false;
						System.exit(0);
						break;
					default:
						System.out.println("Sorry, invalid selection. Please select an option from the menu above: ");
				} //end switch
				
				if(returntomain == true)
				{
					return;
				} //end if
				
			} //end while loop
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch
		
	} //end place_order
	
	/**
	 * Presents the shipping and billing addresses for confirmation and finalizes the order.
	 * @param current_cart The Order object being used to store the current cart.
	 */
	public static void confirm_order(Order current_cart)
	{
		try
		{
			returntomain = false;
			cart = current_cart;
			System.out.println("Confirm order information:\n");

			//get customer with ID in cart
			Customer cust = Databases.CustomerDB.getCustomerbyID(cart.getCustomerID());
			Address bill = cust.getBilling();
			Address ship = cust.getShipping();

			System.out.println("Billing address:");
			System.out.println(bill.getAddress1());

			//test to see if Address 2 is empty
			String temp = bill.getAddress2();
			if(!temp.equals(""))
			{
				System.out.println(bill.getAddress2());
			} //end if

			System.out.println(bill.getCity() + ", " + bill.getState() + "  " + bill.getZIP() + '\n');

			System.out.println("Shipping address:");
			System.out.println(ship.getAddress1());

			//test to see if Address 2 is empty
			temp = ship.getAddress2();
			if(!temp.equals(""))
			{
				System.out.println(ship.getAddress2());
			} //end if

			System.out.println(ship.getCity() + ", " + ship.getState() + "  " + ship.getZIP() + '\n');

			System.out.println("1. Place order");
			System.out.println("2. Correct this information (NOT YET IMPLEMENTED)");
			System.out.println("M. Main menu");
			System.out.println("X. Exit program\n");
			System.out.println("Please make a selection: ");

			//take user input and switch based on choice
			try
			{
				repeat = true;
				while(repeat = true) 
				{
					input = brin.readLine();
					input = input.toUpperCase();
					choice = input.charAt(0);

					switch(choice)
					{
						case '1':
							int confirm = placeOrder(cart);
							System.out.println("Your order has been placed!");
							System.out.println("Your confirmation number is: " + confirm);
							break;
						case '2':
							System.out.println("Sorry, modifying customer addresses is not currently implemented.");
							System.out.println("Please make another selection.");
							break;
						case 'M':
							main_menu();
							repeat = false;
							returntomain = true;
							break;
						case 'X':
							System.out.println("Goodbye! Thank you for shopping with us!");
							repeat = false;
							System.exit(0);
							break;
						default:
							System.out.println("Sorry, invalid selection. Please select an option from the menu above: ");
					} //end switch

					if(returntomain == true)
					{
						return;
					} //end if

				} //end while loop
			} //end try
			catch (Exception e)
			{
				System.err.println("Error: " + e);
			} //end catch
		} //end try
		catch (Exception e)
		{
			System.err.println("Error: " + e);
		} //end catch
	} //end confirm_order

} //end menu class
