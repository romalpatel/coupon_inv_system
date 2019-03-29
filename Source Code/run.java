package system;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class run {
	/*
	 * The main for the CIS. displays the Main Menu in console and performs
	 * operations based on the input entered
	 */
	public static void main(String[] args) throws Exception {

		masterlist au = new masterlist();
		Scanner in = new Scanner(System.in);
		int command = 9;
		do {
			main_menu();

			command = in.nextInt();

			if (!is_command_valid(command)) {
				/*
				 * If the input doen't match the operation list- display invalid
				 * and try again
				 */
				System.out.println("####INVALID OPERATION####");
				System.out.println("Please Try Again");
				continue;
			}
			/*
			 * Switch phase based on operation
			 */
			switch (command) {
			case 1:// FOR ADDING COUPONS FROM THE FILE
				command = 1;
				au.add_file_coupon();
				continue;
			case 2:// FOR ADDING COUPONS MANUALLY
				command = 2;
				au.add_coupon();
				continue;
			case 3:// FOR SEARCHING A COUPON FROM THE LIST
				command = 3;
				au.search_coupon();
				continue;
			case 4:// FOR VIEWING THE COUPONS 
				command = 4;
				au.view_list();
				continue;
			case 5:// TO QUIT
				command = 0;
				System.exit(0);
			}
			System.out.println("####  Ending the Program  ####");
		} while (command != 0);

		in.close();

	}

	public static void main_menu() throws IOException {
		/*
		 * Displays main menu for the program
		 */
		System.out.println();
		System.out.println("     ******************    COUPON INVENTORY SYSTEM    ******************     ");
		System.out.println();
		System.out.println("						Select your Operation    ");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(
				" 1. Add Coupon from an existing text file-" + "[" + "Add coupon using a file/Browse the file" + "]");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(
				" 2. Add a New Coupon Manually-" + "[" + "Manually add new coupon to the existing coupon" + "]");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(
				" 3. Search an Existing Coupon-" + "[" + "Locate a coupon based on the product it applies" + "]");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(" 4. View Coupon List-" + "[Displays complete list of Coupons]");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println("** Enter 0 to quit **");
		System.out.println("");
		System.out.println("Enter the serial number corresponding to the operation");

	}

	public static boolean is_command_valid(int com) {
		/*
		 * returns true if the command of operation entered is from the menu
		 * else returns false for an unknown command
		 */
		if ((com == 1) || (com == 2) || (com == 3) || (com == 0) || (com == 4)) {
			return true;
		} else {
			return false;
		}
	}

}
