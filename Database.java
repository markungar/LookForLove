
package LookForLove;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Database {
	

		// Global fields.

	private Person logInUser; // the current user that's currently logged in the Database, null if no user is logged in.
	private Person root; // the root of our unbalanced binary tree.
	public Map<String, LoginKey> loginLookUp;


		// Database()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Initializes a Database with 1 Person in it as the root and set the file that it wants to interact with.
		// *Note: No user is logged in so logInUseris null.

	public Database(Person root) {

		logInUser = null; // no user logged in.
		this.root = root; // the root of the binary tree.
		this.root.parent = null; // since this is the root, no parent is assigned to this node.

		// add this user to the lookup map, useful when checking if a user exists in the database or checking 
		loginLookUp = new Map<String, LoginKey>();
		loginLookUp.put(this.root.loginInfo.getUsername(), new LoginKey(this.root.loginInfo.getPassword(), root) );
	
	}


		// insert()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------	
		// Purpose: Wrapper method for the next insert function.

	public boolean insert(Person newNode) {
		insert(newNode, root);
	}


		// insert()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Insert newNode into the binary tree.
		// 	This method achieves it by recursively comparing newNode to the closest node by their age.
		// *Note: If the two nodes have equal age, then insert newNode to the left of oldNode.
 
	public boolean insert(Person newNode, Person oldNode) {

		try {
			// new node has a smaller equal age than old node.
			if (newNode.compareTo(oldNode) <= 0) {

				// if old node's left child is empty, append new node.
				if (oldNode.leftChild == null) {
					oldNode.leftChild = newNode;
					newNode.parent = oldNode;
				}

				// else continue down old node's left child.
				else {
					// return the results (whether successful) of insert(newNode, oldNode.leftChild).
					return insert(newNode, oldNode.leftChild);
				}
			}
			// new node hase a bigger age than old node.
			else {

				// if old node's right child is empty, append new node.
				if (oldNode.rightChild == null) {
					oldNode.rightChild = newNode;
					newNode.parent = oldNode;
				}

				// else continue down old node's right child.
				else {
					// return the results (whether successful) of insert(newNode, oldNode.rightChild).
					return insert(newNode, oldNode.rightChild);
				}
			}

			// everything went smoothly, return true.
			return true;

		} 
		// Errors occur in inserting
		catch (Exception e) {
			return false;
		}
	}


		// logIn()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: take in a pair of <username, password>.
		//	check if the username exists. If it does, set the loginUser as the Person corresponding to that username.

	public boolean logIn(String username, String password) {
		
		LoginKey userInfo = loginLookUp.get(username);
		if (userInfo == null) {
			System.out.println("Username doesn't exist. Please try again.");
			return false;
		}
		else if (userInfo.getPassword() != password) {
			System.out.println("Invalid password. Please try again");
			return false;
		}

		loginUser = userInfo.getPerson();
		return true;
	
	}

	public boolean logout() {
		try {
			loginUser = null;
			return true;
		}
		catch (Exception e) {
			System.out.println("Trouble logging out. Please try again.");
			return false;
		}
	}

	public boolean checkUsernameExist(String username) {
		return loginLookup.containsKey(username);
	}

	public boolean register() {

		Scanner sc = new Scanner(System.in);

		String result;

		String firstName, lastName, emailAddress, username, password, passwordCheck, ethnicity, description;
		String wishEthnicity;

		int age, height, phoneNumber;
		int wishAgeMin, wishAgeMax, wishHeight;

		String[] prompts = new String[4];
		Account loginInfo;
		Character trait;
		WishCharacter wish;
		
		System.out.println("We will now begin the registering process...");
		System.out.println("We will ask a couple of questions about yourself so we can understand your needs.");
		System.out.println("Our algorithms will use this information to find the best match for you.");
		System.out.println("If you would like to cancel the registering process, simply type in \"quit\" as a question's answer.");
		System.out.println("Hop on the LookForLove train, and have fun!");

		System.out.println();	
		System.out.println("Part A. About the account:");
		System.out.println("First off, let's get started on the basic informations about you: ");
		System.out.println("In this section, we will create the username and password for you;");
		System.out.println("As well as adding other informations such as your first and last name to our system!");

		boolean validInfo;

		do {
			validInfo = true;
			System.out.println();

			System.out.println("A1. What would be your preferred username when logging in LookForLove? ");
			System.out.print("Please provide your answer: ");
			username = sc.nextLine();
			if (username.equals("quit")) {
				return false;
			}
		
			boolean usernameTaken = checkUsernameExist(username);
			if (usernameTaken) {
				System.out.println("Ooops :(");
				System.out.println("Username is already taken.");
				validInfo = false;
			}
			
		} while (!validInfo);

		do {

			validInfo = true;
			System.out.println();
			
			System.out.println("A2. What would be your password when logging into LookForLove? ");
			System.out.print("Please provide your answer: ");
			password = sc.nextLine();
			if (password.equals("quit")) {
				return false;
			}

			System.out.println("Please confirm the password again in the prompt below.");
			System.out.print("Please provide your answer: ");
			passwordCheck = sc.nextLine();
			if (passwordCheck.equals("quit")) {
				return false;
			}

			boolean matchPassword = password.equals(passwordCheck);
			if (!matchPassword) {
				System.out.println("Oops :(");
				System.out.println("The password and confirmation does not match each other.");
				validInfo = false;
			}
		
		} while (!validInfo);

		try {

			System.out.println();

			System.out.println("A3. What is your first name? ");
			System.out.print("Please provide your answer: ");
			firstName = sc.nextLine();
			if (firstName.equals("quit")) {
				return false;
			}

			System.out.println();

			System.out.println("A4. What is your last name? ");
			lastName = sc.nextLine();
			if (lastName.equals("quit")) {
				return false;
			}

			System.out.println();

			System.out.println("A5. What is your email address? ");
			emailAddress = sc.nextLine();
			if (emailAddress.equals("quit")) {
				return false;
			}

		}
		catch (Exception e) {
			return false;
		}

		do {

			validInfo = true;
			System.out.println();
	
			System.out.println("A6. What is your phone number? ");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				phoneNumber = Integer.valueOf(result);
				
			}
			catch (NumberFormatException nfx) {
				System.out.println("Oops :(");
				System.out.println("Unfortunately, the phone number you entered has a digit that is not a number.");
				System.out.println("Please try again with all numbers.");
				validInfo = false;
			}

		} while (!validInfo);

		System.out.println();
		System.out.println("Part B. Identity and preferences:");
		System.out.println("Congradulations! You have finished part 1 of the registering process.");
		System.out.println("Lucky you, the most boring part is over! Now we get to the more interesting stuff.");
		System.out.println("In this section, we will ask a couple more general questions.");
		System.out.println("Both for yourself, and your ideal partner.");
		System.out.println();
		System.out.println("Note that this section is purely optional; ");
		System.out.println("If you are uncomfortable or don't know the answer to any question below, you can skip it by typing \"skip\" as the answer.");
		System.out.println("Don't worry if you mistyped an answer! We'll have an option for users to edit their information once their accounts has been created.");
		System.out.println();
		System.out.println("Ready?");
		System.out.println("Enter (\"Yes\") or (\"No\"):");
		
		String ready = sc.nextLine();
		if (!ready.equalsIgnoreCase("yes")) {
			if (ready.equalsIgnoreCase("no")) {
				System.out.println("Love can't wait! Just a couple more questions and true love is ahead!");
			}
			else {
				System.out.println("Welp.. Guess you're too excited that you couldn't type properly.");
				System.out.println("That's alright! We'll help you get through the registering process.");
			}
		}

		System.out.println();
		
		System.out.println("B1. Which culture are you from? (IE. African, Asian-American, French, etc..)");
		System.out.print("Please provide your answer: ");
		ethnicity = sc.nextLine();
		if (ethnicity.equalsIgnoreCase("quit")) {
			return false;
		}
		else if (ethnicity.equalsIgnoreCase("skip")) {
			ethnicity = "-1";
		}

		do {

			validInfo = true;
			System.out.println();
	
			System.out.println("B2. What is your height? (In centimeters)");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				height = Integer.valueOf(result);

			}
			catch (NumberFormatException nfx) {
				if (result.equalsIgnoreCase("skip")) {
					height = -1;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}

		} while (!validInfo);

		do {

			validInfo = true;
			System.out.println();
	
			System.out.println("B3. What is your age? (In centimeters)");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				age = Integer.valueOf(result);

				if (age < 18) {
					System.out.println();
					System.out.println("Oops :(");
					System.out.println("Unfortunately, your age does not meet our policy.");
					System.out.println("Our app requires the user to be 18 years or older to use.");
					return false;
				}

			}
			catch (NumberFormatException nfx) {
				System.out.println("Oops :(");
				System.out.println("Unfortunately, the response you entered is not a number.");
				System.out.println("Please try again with valid numbers.");
				validInfo = false;
			}

		} while (!validInfo);

		System.out.println();

		System.out.println("Now, we'll ask some similar questions about your preferences.");
		System.out.print("Please type anything to continue: ");
		sc.nextLine();

		System.out.println("B4. In which culture do you think your ideal one is most attractive from?");
		System.out.print("Please provide your answer: ");
		wishEthnicity = sc.nextLine();
		
		if (wishEthnicity.equalsIgnoreCase("quit")) {
			return false;
		}
		else if (wishEthnicity.equalsIgnoreCase("skip")) {
			wishEthnicity = "-1";
		}

		do {
		
			validInfo = true;
			System.out.println();
			
			System.out.println("B5. What would be the preferrable height that you wish your partner have? ");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				wishHeight = Integer.valueOf(result);				

			}
			catch (NumberFormatException nfx) {
				if (result.equals("skip")) {
					wishHeight = -1;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);

		do {
		
			validInfo = true;
			System.out.println();
			
			System.out.println("B4. What would be the youngest age that you wish your partner is? ");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				wishAgeMin = Integer.valueOf(result);				

			}
			catch (NumberFormatException nfx) {
				if (result.equals("skip")) {
					wishAgeMin = -1;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);

		do {
		
			validInfo = true;
			System.out.println();
			
			System.out.println("B5. What would be the oldest age that you wish your partner is? ");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				if (result.equals("quit")) {
					return false;
				}
				wishAgeMax = Integer.valueOf(result);				

			}
			catch (NumberFormatException nfx) {
				if (result.equals("skip")) {
					wishAgeMax = -1;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);

		// Prompt Questions which are stored into an array inside of Person

       		System.out.println("\nPart C. Prompts");
        	System.out.println("For this part, we ask more questions about yourself;");
		System.out.println("In this part, the answers will match you with other people with similar interests as you!");

		System.out.println();

        	System.out.println("C1. What is your favourite movie?");
        	System.out.print("Please provide your answer: ");
        	prompts[0] = sc.nextLine();

		System.out.println();

       	 	System.out.println("C2. What is your favourite sport?");
        	System.out.print("Please provide your answer: ");
        	prompts[1] = sc.nextLine();

		System.out.println();

        	System.out.println("C3. What is your favourite season?");
        	System.out.print("Please provide your answer: ");
        	prompts[2] = sc.nextLine();

		System.out.println();

        	System.out.println("\nC4. What is your favourite music genre?");
        	System.out.print("Please provide your answer: ");
        	prompts[3] = sc.nextLine();

		System.out.println();

		System.out.println("Part 4. Description");
		System.out.println("Next, please write out anything you would like to add for others to know about you.");
		System.out.println("Please write it in a single line of code, pressing enter will effectively terminate your answers.");
		System.out.print("Please provide your answer: ");
		description = sc.nextLine();

		//loginInfo = new Account(firstName, lastName, phoneNumber, emailAddress, age, username, password);
		//trait = new Character(ethnicity, height, age);
		//wish = new Wishcharacter(wishAgeMin, wishAgemax, wishEthnicity, wishHeight, 0);
		
		
		
	}


}