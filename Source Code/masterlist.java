package system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/*
 * Master List contains 
 * Forms Unsorted List
 * Forms Sorted List
 * Adds Coupons from file and manually
 * Search for coupon- Linear Search Algorithm and BST Algorithm
 * File Operations 
 */

public class masterlist {

	public LLNode<coupon> USL_coupons = new LLNode<>();// First use of Unsorted
														// List

	public LLNode<coupon> SL_coupons = new LLNode<>();// Sorting the Unsorted
														// List

	public int num = 0;// The number of Coupons in the system

	// *******************ADDING COUPONS TO THE LIST*************************

	public void add_file_coupon() throws IOException {
		/*
		 * Add coupons from the file Dialog Box opens to select the file which
		 * contains the coupon list Select file and read coupons from the file
		 * and add it to current list
		 */
		int i = 1;
		int couponsinfile = 32;
		String line = "";
		/*
		 * Dialog Box code
		 */
		JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		File file = null;

		int returnVal = choose.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = choose.getSelectedFile();
		}

		BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		coupon store = new coupon();
		/*
		 * Till the end of file add all coupons
		 */
		while (i <= couponsinfile) {
			store = new coupon();
			store.Provider = reader.readLine();
			store.product = reader.readLine();
			line = reader.readLine();
			store.price = Double.parseDouble(line);
			line = "";
			line = reader.readLine();
			store.dis_rate = Integer.parseInt(line);
			line = "";
			line = reader.readLine();
			store.exp_period = Integer.parseInt(line);
			line = "";
			line = reader.readLine();

			if (line == "true") {
				store.Used = true;
			} else {
				store.Used = false;
			}
			store.final_price = store.price - (store.price * store.dis_rate / 100);
			USL_coupons.add(store);
			i++;
			num++;
		}

		System.out.println(num + " coupons added from the file to the list.");
		// reading each line from the file

		reader.close();
		write_unsorted_list_file(USL_coupons);// writing the coupon list into
												// file

	}

	public void add_coupon() throws IOException {
		/*
		 * Add coupons manually to the list coupon is then added to the original
		 * list
		 */

		Scanner write_input = new Scanner(System.in);
		coupon temp = new coupon();

		LLNode<coupon> newlist = read_file(new FileReader("unsorted_coupons.txt"));

		System.out.println("*****ADD COUPON TO LIST*****");
		System.out.println("----------------------------------------------");
		System.out.println("## Enter 0 to quit ##");
		System.out.println("----------------------------------------------");
		System.out.println("Enter the coupon Provider:");
		System.out.println("(" + "example- Groupon/ Living Social/ Likeacoupon/ DealDaddie/ ..." + ")" + "("
				+ "max 20 characters" + ")");
		temp.Provider = write_input.nextLine();

		if (temp.Provider == "0") {
			System.out.println("Ending the Program");
			System.exit(0);
		}
		if (temp.Provider.length() > 20) {
			do {
				System.out.println(
						"ERROR 202: The Coupon Provider name exceeds 20 characters- Please try entering again ");
				System.out.println("Enter the coupon Provider:");
				System.out.println("(" + "example- Groupon/ Living Social/ Likeacoupon/ DealDaddie/ ..." + ")" + "("
						+ "max 20 characters" + ")");
				temp.Provider = write_input.next();
			} while (temp.Provider.length() > 20);
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("----------------------------------------------");
		System.out.println("## Enter 0 to quit ##");
		System.out.println("----------------------------------------------");
		System.out.println("Enter the product name for the coupon:");
		System.out.println("(" + "example- Groceries/ Medicines/ Tools/ Food/ Services/ Electronics ..." + ")" + "("
				+ "max 20 characters" + ")");
		temp.product = write_input.nextLine();

		if (temp.product == "0") {
			System.out.println("Ending the Program");
			System.exit(0);
		}

		if (temp.product.length() > 20) {
			do {
				System.out.println("ERROR 202: Invalid product name(exceeds 20 characters)- ***Please try again*** ");
				System.out.println("----------------------------------------------");
				System.out.println("Enter the product name for the coupon:");
				System.out.println("(" + "example- Groceries/ Medicines/ Tools/ Food/ Services/ Electronics ..." + ")"
						+ "(" + "max 20 characters" + ")");
				temp.product = write_input.next();
			} while (temp.product.length() > 20);
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("The Coupon is applicable on " + temp.product);
		System.out.println("----------------------------------------------");
		System.out.println("## Enter 0 to quit ##");
		System.out.println("----------------------------------------------");
		System.out.println("Enter the Price of the Product:");
		temp.price = write_input.nextDouble();

		if (temp.price == 0) {
			System.out.println("Ending the Program");
			System.exit(0);
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("The Coupon is applicable on " + temp.product);
		System.out.println("The initial price of the product is $" + temp.price);
		System.out.println("----------------------------------------------");
		System.out.println("## Enter 0 to quit ##");
		System.out.println("----------------------------------------------");
		System.out.println("Enter the Discount Rate on the Coupon:");
		temp.dis_rate = write_input.nextInt();

		if (temp.dis_rate == 0) {
			System.out.println("Ending the Program");
			System.exit(0);
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("The Coupon is applicable on " + temp.product);
		System.out.println("The initial price of the product is $" + temp.price);
		System.out.println("The Discount rate with the coupon is " + temp.dis_rate + "%");
		System.out.println("----------------------------------------------");
		System.out.println("## Enter 0 to quit ##");
		System.out.println("----------------------------------------------");
		System.out.println("Enter the Validity Period for the coupon:");
		System.out.println("(example -from 0 to 365 days)");
		temp.exp_period = write_input.nextInt();

		if (temp.exp_period == 0) {
			System.out.println("Ending the Program");
			System.exit(0);
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("The Coupon is applicable on " + temp.product);
		System.out.println("The initial price of the product is $" + temp.price);
		System.out.println("The Discount rate with the coupon is " + temp.dis_rate + "%");
		System.out.println("The Validity Period for the coupon is " + temp.exp_period + " days");
		System.out.println("----------------------------------------------");
		System.out.println("Enter 1 if the coupon is used or 0 for unused-");
		int used = write_input.nextInt();
		if (used == 1) {
			temp.Used = true;
		} else if (used == 0) {
			temp.Used = false;
		} else {
			do {
				System.out.println("Invalid Input, Please try Again");
				System.out.println("Enter 1 if the coupon is used or 0 for unused-");
				used = write_input.nextInt();
				if (used == 1) {
					temp.Used = true;
				} else if (used == 0) {
					temp.Used = false;
				}
			} while ((used != 0) && (used != 1));
		}

		System.out.println("The Coupon Provider is " + temp.Provider);
		System.out.println("The Coupon is applicable on " + temp.product);
		System.out.println("The initial price of the product is $" + temp.price);
		System.out.println("The Discount rate with the coupon is " + temp.dis_rate + "%");
		System.out.println("The Validity Period for the coupon is " + temp.exp_period + " days");
		if (temp.Used == true) {
			System.out.println("####The Coupon has been Used####");
		} else {
			System.out.println("****The Coupon can be Redeemed****");
		}

		temp.final_price = temp.price - (temp.price * temp.dis_rate / 100);
		System.out.println("The final Price for the coupon is $" + temp.final_price);

		System.out.println("Adding the above coupon to the DataBase.....");

		append_unsort_file();
		newlist.add(temp);
		System.out.println("		 Coupon Successfully Added			");
		write_unsorted_list_file(newlist);
		num++;

	}

	public void append_unsort_file() throws IOException {
		FileWriter fr = new FileWriter("unsorted_coupons.txt", true);
		BufferedWriter bw = new BufferedWriter(fr);
		PrintWriter pw = new PrintWriter(bw);
		pw.println("Hello WOrld");

		pw.close();
	}

	public void sort_list() throws IOException {
		/*
		 * sort the unsorted list from file "unsorted_coupons.txt" compare min
		 * node to each element in the list, at the end of list, there is a
		 * minimum element from the list add the element to sorted list and
		 * remove it from unsorted list after sorting , write the coupons to
		 * file "sorted_coupons.txt" gives IOException if file doesn't exist
		 */

		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));
		LLNode<coupon> point = new LLNode<>();// compare this point to rest of
												// values
												// in LL to be smallest
		LLNode<coupon> min = new LLNode<>();// this node holds the minimum
											// element from the list

		/*
		 * If at any comparison, the node in list is less than min node, then
		 * change the min node
		 */
		int counter = 0;

		while (counter < num) {
			point = unsort.getLink();
			min.info = point.info;
			while (point != null) {
				if (point.info.product.compareTo(min.info.product) < 0) {
					min.info = point.info;
				} else {
					point = point.getLink();
				}
			}

			SL_coupons.add(min.info);
			unsort.remove(min.info);

			counter++;
		}

		write_out(SL_coupons);// write the sorted list to a file
	}

	// ****************** READING AND WRITING FILES *************************

	public void write_unsorted_list_file(LLNode<coupon> t) {
		/*
		 * Write the coupon list from the parameter to the file
		 * named-"unsorted_coupons.txt" Catches File not found exception
		 */
		t = t.getLink();
		try {
			PrintWriter writer = new PrintWriter("unsorted_coupons.txt");
			do {

				writer.println(t.info.getProvider());
				writer.println(t.info.getProduct());
				writer.println(t.info.getPrice());
				writer.println(t.info.getDis_rate());
				writer.println(t.info.getExp_period());
				writer.println(t.info.Used);
				writer.println(t.info.getFinal_price());

				t = t.getLink();
			} while (t != null);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(" File not found");
		}
		;
	}

	public void write_out(LLNode<coupon> v) throws FileNotFoundException {

		/*
		 * writes sorted list in the parameter to a file "sorted_coupons.txt"
		 * gives FileNotFoundException if the file doesn't exist
		 */
		v = v.getLink();
		try {
			PrintWriter writer = new PrintWriter("sorted_coupons.txt");
			do {

				writer.println(v.info.getProvider());
				writer.println(v.info.getProduct());
				writer.println(v.info.getPrice());
				writer.println(v.info.getDis_rate());
				writer.println(v.info.getExp_period());
				writer.println(v.info.Used);
				writer.println(v.info.getFinal_price());

				v = v.getLink();
			} while (v != null);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		}
		;

	}

	public LLNode<coupon> read_file(FileReader fr) throws IOException {

		/*
		 * Reads coupons from a file and makes a list returns the list
		 */
		LLNode<coupon> read_list = new LLNode<>();

		BufferedReader read = new BufferedReader(fr);
		int k = 1;
		coupon addition = new coupon();
		String str = "";

		while (k <= num) {
			addition = new coupon();
			addition.Provider = read.readLine();
			addition.product = read.readLine();
			str = read.readLine();
			addition.price = Double.parseDouble(str);
			str = "";
			str = read.readLine();
			addition.dis_rate = Integer.parseInt(str);
			str = "";
			str = read.readLine();
			addition.exp_period = Integer.parseInt(str);
			str = "";
			str = read.readLine();
			if (str == "false") {
				addition.Used = false;
			} else if (str == "true") {
				addition.Used = true;
			}
			str = "";
			str = read.readLine();
			addition.final_price = Double.parseDouble(str);

			read_list.add(addition);
			k++;
		}

		read.close();
		return read_list;
	}

	// ************************** PRINTING COUPONS
	// ********************************

	public void print_list(LLNode<coupon> x) {

		/*
		 * Printing method to print a list
		 */
		System.out.println("---------------------COUPONS LIST------------------------- ");
		System.out.println();
		int i = 1;
		System.out.println("The total number of coupons in the systems is "+num);
		System.out.println();
		x = x.getLink();
		while (x != null) {
			System.out.println(x.getInfo());
			System.out.println("the coupon number in list is " + i);
			i++;
			x = x.getLink();
		}
	}

	// *****************************SEARCH OPERATIONS
	// ********************************

	public void search_coupon() throws IOException {

		/*
		 * Searching from the sorted list Use Linear search algorithm and Binary
		 * Search Tree Algorithm Displays the coupon if there's one and counts
		 * for each algorithm displays coupon absent if coupon is not found
		 */

		int bst_count = 0;
		int linear_count = 0;

		Scanner search = new Scanner(System.in);
		System.out.println("SEARCH COUPON- (Enter product name to show coupons) ");
		String s = search.nextLine();

		bst_count = search_bst(s);
		linear_count = search_linear(s);
		if (linear_count > num) {
			linear_count = 0;
			System.out.println(" #### COUPON NOT PRESENT IN SYSTEM ####");
		} else {
			System.out.println();
			System.out.println(" COUPON PRESENT IN THE SYSTEM ");
			System.out.println("Using linear Algorithm found in " + linear_count + "th count"
					+ " AND using BST found in " + bst_count + "the count");
		}

	}

	public coupon[] read_coupon_in_array() throws IOException {
		/*
		 * For BST search algorithm, reading coupons from file and adding them
		 * to a array of coupon objects returns coupon array
		 */
		coupon[] coupon_array = new coupon[num];

		String test = "";
		BufferedReader array_form = new BufferedReader(new FileReader("sorted_coupons.txt"));

		coupon sample = new coupon();
		int k = 0;

		while (k < num) {
			sample = new coupon();
			sample.Provider = array_form.readLine();
			sample.product = array_form.readLine();
			test = array_form.readLine();
			sample.price = Double.parseDouble(test);
			test = "";
			test = array_form.readLine();
			sample.dis_rate = Integer.parseInt(test);
			test = "";
			test = array_form.readLine();
			sample.exp_period = Integer.parseInt(test);
			test = "";
			test = array_form.readLine();
			if (test == "true") {
				sample.Used = true;
			} else {
				sample.Used = false;
			}
			test = "";
			test = array_form.readLine();
			sample.final_price = Double.parseDouble(test);
			test = "";
			coupon_array[k] = sample;
			k++;
		}

		array_form.close();
		return coupon_array;
	}

	public void print_array_list(coupon[] a) {
		/*
		 * Array printing method
		 */
		int l = 0;
		while (l < num) {
			System.out.println(a[l]);
			l++;
		}
	}

	public int search_bst(String str) throws IOException {
		/*
		 * Takes in the string and performs BST search in the array of coupons
		 * Search is performed for product name of the coupon returns count for
		 * the bst search to locate string
		 */
		int count = 1;
		coupon[] sbst = read_coupon_in_array();

		int first = 0;
		int last = num;
		coupon target = new coupon();
		target.product = str;
		int midpoint = (first + last) / 2;

		while (first < last) {
			if (target.product.compareTo(sbst[midpoint].product) == 0) {
				break;
			} else {
				if (target.product.compareTo(sbst[midpoint].product) < 0) {
					last = midpoint - 1;
					count++;
					midpoint = (first + last) / 2;
				} else {
					first = midpoint + 1;
					count++;
					midpoint = (first + last) / 2;
				}
			}
		}

		return count;
	}

	public int search_linear(String stg) throws FileNotFoundException, IOException {
		/*
		 * Perform Linear Search on sorted list to find 'stg' product name
		 */
		stg = stg.toLowerCase();
		coupon target = new coupon();
		target.product = stg;
		int lcount = 1;
		LLNode<coupon> reference = read_file(new FileReader("sorted_coupons.txt"));

		reference = reference.getLink();
		do {
			reference.info.product = reference.info.product.toLowerCase();
			if (target.product.compareTo(reference.info.product) == 0) {
				System.out.println(reference.getInfo());
				break;
			} else {
				reference = reference.getLink();
				lcount++;
			}
		} while (reference != null);
		return lcount;
	}

	// ******************************* VIEW COUPONS
	// ***************************************

	public void view_list() throws IOException {

		/*
		 * This methods runs the script for listing the coupon in the console
		 * Lists the parameter for listing the coupon based on ascending order
		 */
		Scanner view_list = new Scanner(System.in);

		System.out.println(" ****	  VIEW COUPONS		 ****");
		System.out.println("---------------------------------------------");
		System.out.println();
		System.out.println("Sort using");
		System.out.println("1. Coupon Provider ");
		System.out.println();
		System.out.println("2. Coupon Product ");
		System.out.println();
		System.out.println("3. Coupon Discount Rates ");
		System.out.println();
		System.out.println("4. Prices after coupon is redeemed ");
		System.out.println();
		System.out.println("5. Coupons Redeemed/Unused ");
		System.out.println();
		System.out.println("### Enter 0 to Quit ###");
		System.out.println();
		System.out.println("Enter the appropriate number:");

		int option = view_list.nextInt();

		switch (option) {
		case 1:
			option = 1;
			view_list_o1();
			break;
		case 2:
			option = 2;
			view_list_o2();
			break;
		case 3:
			option = 3;
			view_list_o3();
			break;
		case 4:
			option = 4;
			view_list_o4();
			break;
		case 5:
			option = 5;
			view_list_o5();
			break;

		case 6:
			option = 0;
			System.exit(0);
		}

	}

	public void view_list_o1() throws IOException {
		/*
		 * sort based on provider
		 */

		LLNode<coupon> o1_list = new LLNode<>();
		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));

		LLNode<coupon> ref = new LLNode<>();
		LLNode<coupon> min = new LLNode<>();

		int counter = 0;

		while (counter < num) {
			ref = unsort.getLink();
			min.info = ref.info;
			while (ref != null) {
				if (ref.info.Provider.compareTo(min.info.Provider) < 0) {
					min.info = ref.info;
				} else {
					ref = ref.getLink();
				}
			}

			o1_list.add(min.info);
			unsort.remove(min.info);
			counter++;
		}
		print_list(o1_list);

	}

	public void view_list_o2() throws IOException {

		/*
		 * Sort coupons based on product First get coupon list from file
		 * "unsorted_coupons.txt" sort list based on products of coupon
		 */
		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));
		LLNode<coupon> o2_list = new LLNode<>();
		LLNode<coupon> ref = new LLNode<>();
		LLNode<coupon> min = new LLNode<>();

		int counter = 0;

		while (counter < num) {
			ref = unsort.getLink();
			min.info = ref.info;
			while (ref != null) {
				if (ref.info.product.compareTo(min.info.product) < 0) {
					min.info = ref.info;
				} else {
					ref = ref.getLink();
				}
			}

			o2_list.add(min.info);
			unsort.remove(min.info);
			counter++;
		}
		print_list(o2_list);
	}

	public void view_list_o3() throws IOException {

		/*
		 * Sort based on the discount rate on the coupons
		 */
		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));
		LLNode<coupon> o3_list = new LLNode<>();
		LLNode<coupon> ref = new LLNode<>();
		LLNode<coupon> min = new LLNode<>();

		int counter = 0;

		while (counter < num) {
			ref = unsort.getLink();
			min.info = ref.info;
			while (ref != null) {
				if (ref.info.dis_rate < min.info.dis_rate) {
					min.info = ref.info;
				} else {
					ref = ref.getLink();
				}
			}

			o3_list.add(min.info);
			unsort.remove(min.info);
			counter++;
		}
		print_list(o3_list);
	}

	public void view_list_o4() throws FileNotFoundException, IOException {

		/*
		 * Sort coupons based on the final price of the coupons
		 */
		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));
		LLNode<coupon> o4_list = new LLNode<>();
		LLNode<coupon> ref = new LLNode<>();
		LLNode<coupon> min = new LLNode<>();

		int counter = 0;

		while (counter < num) {
			ref = unsort.getLink();
			min.info = ref.info;
			while (ref != null) {
				if (ref.info.final_price < min.info.final_price) {
					min.info = ref.info;
				} else {
					ref = ref.getLink();
				}
			}

			o4_list.add(min.info);
			unsort.remove(min.info);
			counter++;
		}
		print_list(o4_list);
	}

	public void view_list_o5() throws FileNotFoundException, IOException {

		/*
		 * sort the coupons that are unused
		 */
		LLNode<coupon> unsort = read_file(new FileReader("unsorted_coupons.txt"));
		LLNode<coupon> ref = new LLNode<>();

		ref = unsort.getLink();
		while (ref != null) {

			if (ref.info.Used == false) {
				System.out.println(ref.getInfo());
				ref = ref.getLink();
			} else {
				ref = ref.getLink();
			}

		}
	}

}
