package LookForLove;

import java.io.*;
import java.util.*;
import java.lang.*;

	// Database.java
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------
	// Purpose: 1. Maintains informations of all users through an unbalanced binary tree and a dictionary/map.
	// 	    2. Execute functions of our app which includes:
	//                 A. insert(): Insert a new person into the database tree.
	//                 B. logIn(): Log in with a user. The program then runs user-dependent methods base on this person.
	//                 c. logOut(): Log the user out of the database.
	//                 D. checkUsernameExist(): Check if a username is already in use through our dictionary.
	//                 E. register(): Ask the user a couple of questions 
	

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
		loginLookUp.put(this.root.loginInfo.getUsername(), new LoginKey(this.root.loginInfo.getPassword(), root));
	
	}


		// insert()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------	
		// Purpose: Wrapper method for the next insert function.

	public boolean insert(Person newNode) {
		try {
			insert(newNode, root);
			return true;
		} catch(Exception e) {
			return false;
		}
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

		logInUser = userInfo.getPerson();
		return true;
	
	}

		// logout()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Set logInUser to null; which effectively "logged" the user out of the database.

	public boolean logout() {
		try {
			logInUser = null;
			return true;
		}
		catch (Exception e) {
			System.out.println("Trouble logging out. Please try again.");
			return false;
		}
	}

		// checkUsernameExist()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Checks if a given username exists in the database through our dictionary.
		//	Useful when registering a new user because we don't want to have duplicate usernames.

	public boolean checkUsernameExist(String username) {
		return loginLookUp.containsKey(username); 
	}

		// Register()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Asks the user a bunch of questions and combining them into an instance of Person, and then inserting the Person into our database.

	public boolean register() {

			// Global fields necessary to create an instance of Person

		Scanner sc = new Scanner(System.in);
		String result = ""; // temporary buffer for user inputs.
		boolean validInfo; // flag for checking if the user's response is valid in a do-while loop.

		String firstName, lastName, gender, emailAddress, username, password, passwordCheck, ethnicity, description;
		String wishEthnicity, wishSexuality;

		int age = -1, height = -1, phoneNumber = -1;
		int wishAgeMin = -1, wishAgeMax = -1, wishHeight = -1;

		String[] prompts = new String[4];
		Account loginInfo;
		Character trait;
		WishCharacter wish;


			// The registering process.
			// Split in four parts: A. loginInfo, B. trait & wish, C. prompts, D. description.


		// A brief introduction for the user.
		System.out.println("We will now begin the registering process...");
		System.out.println("We will ask a couple of questions about yourself so we can understand your needs.");
		System.out.println("Our algorithms will use this information to find the best match for you.");
		System.out.println("If you would like to cancel the registering process, simply type in \"quit\" as a question's answer.");
		System.out.println("Hop on the LookForLove train, and have fun!");

		
			// Part A. loginInfo


		System.out.println();	
		System.out.println("Part A. About the account:");
		System.out.println("First off, let's get started on the basic informations about you: ");
		System.out.println("In this section, we will create the username and password for you;");
		System.out.println("As well as adding other informations such as your first and last name to our system!");

		do {

			// reset the loop
			validInfo = true;
			System.out.println();

			// Asking for username.
			System.out.println("A1. What would be your preferred username when logging in LookForLove? ");
			System.out.print("Please provide your answer: ");
			username = sc.nextLine();

			// User wants to quit registering
			if (username.equals("quit")) {
				return false;
			}
		
			// check if the username is taken. If so, output error message and prompt the user to try again.
			boolean usernameTaken = checkUsernameExist(username);
			if (usernameTaken) {
				System.out.println("Ooops :(");
				System.out.println("Username is already taken.");
				validInfo = false;
			}
			
		} while (!validInfo);

		do {

			// reset the loop
			validInfo = true;
			System.out.println();
			
			// Asking about the password.
			System.out.println("A2. What would be your password when logging into LookForLove? ");
			System.out.print("Please provide your answer: ");
			password = sc.nextLine();

			// User wants to quit registering.
			if (password.equals("quit")) {
				return false;
			}

			// Confirming the password.
			System.out.println("Please confirm the password again in the prompt below.");
			System.out.print("Please provide your answer: ");
			passwordCheck = sc.nextLine();

			// User wants to quit registering.
			if (passwordCheck.equals("quit")) {
				return false;
			}

			// Check if the first and second password match each other. If it doesn't match, repeat loop again.
			boolean matchPassword = password.equals(passwordCheck);
			if (!matchPassword) {
				System.out.println("Oops :(");
				System.out.println("The password and confirmation does not match each other.");
				validInfo = false;
			}
		
		} while (!validInfo);

		try {

			System.out.println();

			// Asking for first name.
			System.out.println("A3. What is your first name? ");
			System.out.print("Please provide your answer: ");
			firstName = sc.nextLine();

			// User wants to quit registering.
			if (firstName.equals("quit")) {
				return false;
			}

			System.out.println();

			// Asking for last name.
			System.out.println("A4. What is your last name? ");
			lastName = sc.nextLine();

			// User wants to quit registering.
			if (lastName.equals("quit")) {
				return false;
			}

			do {

				// Reset the loop.
				validInfo = true;
				System.out.println();
	
				// Asking for the gender.
				System.out.println("A5. What is your gender? ");
				System.out.println("Type in:);
	 			System.out.println("  1 for male");
				System.out.println("  2 for female");
				System.out.println("  3 for other");
				gender = sc.nextLine();

				// User did not enter 1, 2, or 3.
				if (!gender.equals("1") && !gender.equals("2") && !gender.equals("3")) {
					System.out.println("Oops :(");
					System.out.println("You did not input a valid response! Please try again..");
					validInfo = false;
				}
				// User enter a valid answer. Set gender to their corresponding String.
				else {
					if (gender.equals("1")) gender = "male";
					else if (gender.equals("2")) gender = "female";
					else gender = "other";
				}

			} while (!validInfo);

			System.out.println();

			// Asking for email address.
			System.out.println("A6. What is your email address? ");
			emailAddress = sc.nextLine();

			// User wants to quit registering.
			if (emailAddress.equals("quit")) {
				return false;
			}

		}
		catch (Exception e) {
			return false;
		}

		do {

			// Reset the loop
			validInfo = true;
			System.out.println();
	
			// Asking for the phone number.
			System.out.println("A7. What is your phone number? ");
			System.out.println("*Note: Please enter with out any dashes ("-")");
			System.out.print("Please provide your answer: ");

			try {

				result = sc.nextLine();
				phoneNumber = Integer.parseInt(result); // if !(result instanceof int), jump to the catch block
				
			}
			// user did not enter a number. Repeat the loop.
			catch (NumberFormatException nfx) {

				// User wants to quit registering.
				if (result.equals("quit")) {
					return false;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the phone number you entered has a digit that is not a number.");
					System.out.println("Please try again with all numbers.");
					validInfo = false;
				}
			}

		} while (!validInfo);


			// Part B. trait & wish

		// A little introduction.
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
		
		// Making sure that the user understand what they're doing next.
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
		
		// Asking which culture that the user is from.
		System.out.println("B1. Which culture are you from? (IE. African, Asian-American, French, etc..)");
		System.out.print("Please provide your answer: ");
		ethnicity = sc.nextLine();
		
		// User wants to quit registering.
		if (ethnicity.equalsIgnoreCase("quit")) {
			return false;
		}
		// User wants to skip this question.
		else if (ethnicity.equalsIgnoreCase("skip")) {
			ethnicity = "-1";
		}

		do {

			// Reset the loop
			validInfo = true;
			System.out.println();
	
			// Asking for the height.
			System.out.println("B2. What is your height? (In centimeters)");
			System.out.print("Please provide your answer: ");
			try {

				result = sc.nextLine();
				height = Integer.valueOf(result); Jumps to catch block if !(result instanceof int)

			}
			catch (NumberFormatException nfx) {

				// User wants to skip, in this case they intentionally not put an integer.
				if (result.equalsIgnoreCase("skip")) {
					height = -1;
				}
				// User wants to quit registering.
				else if (result.equalsIgnoreCase("quit")) {
					return false;
				}
				// Otherwise repeat the loop.
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}

		} while (!validInfo);

		do {

			// reset the loop
			validInfo = true;
			System.out.println();
	
			// Asking about the age.
			System.out.println("B3. What is your age? ");
			System.out.println("*Note: This question is mandatory.");
			System.out.print("Please provide your answer: ");
			try {

				result = sc.nextLine();
				age = Integer.valueOf(result); // jumps to catch block if !(result instanceof int).

				// The user is not older than 18, which does not meet our policy. Quit registering.
				if (age < 18) {
					System.out.println();
					System.out.println("Oops :(");
					System.out.println("Unfortunately, your age does not meet our policy.");
					System.out.println("Our app requires the user to be 18 years or older to use.");
					return false;
				}

			}
			// User enter NaN. Repeat the loop.
			catch (NumberFormatException nfx) {

				// User wants to quit registering.
				if (result.equals("quit")) {
					return false;
				}
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}

		} while (!validInfo);

		System.out.println();

		// let the user take a breath.
		System.out.println("Now, we'll ask some similar questions about your preferences.");
		System.out.print("Please type anything to continue: ");
		sc.nextLine();

		// continuing.
		do {
			// Reset the loop
			validInfo = true;
			System.out.println();	
	
			// Asking for the preferred gender.
			System.out.println("B4. Which gender are you attracted to? ");
			System.out.println("Type in:);
 			System.out.println("  1 for male");
			System.out.println("  2 for female");
			System.out.println("  3 for other");
			System.out.println("  4 if you do not have a preference when it comes to gender of your other half");
			System.out.print("Please provide your answer: ");
			result = sc.nextLine();

			// User wants to quit registering.
			if (result.equals("quit")) {
				return false;
			}
			// User wants to skip the question.
			else if (result.equals("skip")) {
				wishSexuality = "all";
			}
			else {

				// changing the genders to their corresponding Strings.
				if (result.equals("1")) {
					wishSexuality = "male";
				}
				else if (result.equals("2")) {
					wishSexuality = "female";
				}
				else if (result.equals("3")) {
					wishSexuality = "other";
				}
				else if (result.equals("4")) {
					wishSexuality = "all";
				}
				// The user did not enter a valid response. Repeat the loop.
				else {
					System.out.println("Oops :(");
					System.out.println("You did not enter a valid number.");
					System.out.println("Please try again...");
					validInfo = false;
				}

			}


		} while (!validInfo);

		// Asking about preferred culture.
		System.out.println("B5. In which culture do you think your ideal one is most attractive from?");
		System.out.print("Please provide your answer: ");
		wishEthnicity = sc.nextLine();
		
		// User wants to quit registering.
		if (wishEthnicity.equalsIgnoreCase("quit")) {
			return false;
		}
		// User wants to skip the question.
		else if (wishEthnicity.equalsIgnoreCase("skip")) {
			wishEthnicity = "-1";
		}

		do {
		
			// Reset the loop.
			validInfo = true;
			System.out.println();
			
			// Asking about preferred height.
			System.out.println("B6. What would be the preferrable height that you wish your partner have? ");
			System.out.print("Please provide your answer: ");
			try {

				result = sc.nextLine();
				wishHeight = Integer.valueOf(result); // Jumps to catch block if !(result instanceof int).		

			}
			catch (NumberFormatException nfx) {

				// User wants to quit registering.
				if (result.equals("quit")) {
					return false;
				}
				// User wants to skip the question.
				else if (result.equals("skip")) {
					wishHeight = -1;
				}
				// User enters invalid info.
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);

		do {
		
			// reset the loop
			validInfo = true;
			System.out.println();
			
			// Asking about min. preferred age.
			System.out.println("B7. What would be the youngest age that you wish your partner is? ");
			System.out.print("Please provide your answer: ");
			try {
				result = sc.nextLine();
				wishAgeMin = Integer.valueOf(result); // jumps to catch block if !(result instanceof int).		
			}
			// User entered invalid input. Repeat the loop
			catch (NumberFormatException nfx) {
				
				// User wants to quit registering.
				if (result.equals("quit")) {
					return false;
				}
				// User wants to skip this question.
				else if (result.equals("skip")) {
					wishAgeMin = -1;
				}
				// User entered invalid input. Output error message.
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);

		do {
		
			// Reset the loop
			validInfo = true;
			System.out.println();
			
			// Asking for the oldest preferred age.
			System.out.println("B8. What would be the oldest age that you wish your partner is? ");
			System.out.print("Please provide your answer: ");
			try {

				result = sc.nextLine();
				wishAgeMax = Integer.valueOf(result); // jumps to catch block if !(result instanceof int).				

			}
			// User entered invalid input. Repeat the loop.
			catch (NumberFormatException nfx) {
				
				// User wants to quit registering.
				if (result.equals("quit")) {
					return false;
				}
				// User wants to skip the question.
				else if (result.equals("skip")) {
					wishAgeMax = 99999;
				}
				// User entered invalud input. Output error message.
				else {
					System.out.println("Oops :(");
					System.out.println("Unfortunately, the response you entered is not a number.");
					System.out.println("Please try again with valid numbers.");
					validInfo = false;
				}
			}			
					

		} while (!validInfo);


			// Part C. Prompt questions


		// A little introduction.
		System.out.println();
       		System.out.println("Part C. Prompts");
        	System.out.println("For this part, we ask more questions about yourself;");
		System.out.println("In this part, the answers will match you with other people with similar interests as you!");

		System.out.println();

		// Asking about the favorite movie.
        	System.out.println("C1. What is your favourite movie?");
        	System.out.print("Please provide your answer: ");
        	result = sc.nextLine();

		// User would like to quit registering.
		if (result.equals("quit")) {
			return false;
		}
		// User would like to skip the question.
		else if (result.equals("skip")) {
			prompts[0] = "-1";
		}
		else {
			prompts[0] = result;
		}
		
		System.out.println();

		// Asking for favorite sport.
       	 	System.out.println("C2. What is your favourite sport?");
        	System.out.print("Please provide your answer: ");
        	result = sc.nextLine();

		// User would like to quit registering.
		if (result.equals("quit")) {
			return false;
		}
		// User would like to skip the question.
		else if (result.equals("skip")) {
			prompts[1] = "-1";
		}
		else {
			prompts[1] = result;
		}

		System.out.println();

		// Asking for favorite season.
        	System.out.println("C3. What is your favourite season?");
        	System.out.print("Please provide your answer: ");
        	result = sc.nextLine();

		// User would like to quit registering.
		if (result.equals("quit")) {
			return false;
		}
		// User would like to skip the question.
		else if (result.equals("skip")) {
			prompts[2] = "-1";
		}
		else {
			prompts[2] = result;
		}

		System.out.println();

		// Asking for favorite music genre.
        	System.out.println("C4. What is your favourite music genre?");
        	System.out.print("Please provide your answer: ");
        	result = sc.nextLine();

		// User would like to quit registering.
		if (result.equals("quit")) {
			return false;
		}
		// User would like to skip the question.
		else if (result.equals("skip")) {
			prompts[3] = "-1";
		}
		else {
			prompts[3] = result;
		}

		System.out.println();

		// Asking for a description of the user.
		System.out.println("Part D. Description");
		System.out.println("Next, please write out anything you would like to add for others to know about you.");
		System.out.println("Please write it in a single line of code, pressing enter will effectively terminate your answers.");
		System.out.print("Please provide your answer: ");
		description = sc.nextLine();

		// creating the person.
		try {
			loginInfo = new Account(firstName, lastName, phoneNumber, emailAddress, username, password);
			trait = new Character(ethnicity, height, age);
			wish = new WishCharacter(wishAgeMin, wishAgeMax, wishSexuality, wishEthnicity, wishHeight, 0);

			Person person = new Person(loginInfo, trait, wish, prompts, description);

			return true;
		} 
		catch(Exception e) {
			return false;
		}

		public LinkedList<Pair<int, Person>> findMatch(Person user) {
			findSubsetMatch(user, this.root);
		}
		
		public LinkedList<Pair<int, Person>> findSubsetMatch(Person user, Person current) {
			
			Character trait = user.getTrait();
			Character currentTrait = user.getTrait();
			WishCharacter wish = user.getWish();
			WishCharacter currentWish = current.getWish();

			LinkedList<Pair<Person, int>> people = new LinkedList();

			if (wish.fit(currentTrait) == -1) {
				return findSubsetMatch(user, current.rightChild);
			}

			else if (wish.fit(currentTrait) == 1) {
				return findSubsetMatch(user, current.leftChild);
			}

			if (current != null) {
				people.addAll(findSubsetMatch(user, current.leftChild));
				if (wish.getSexuality().equals(currentTrait.getGender()) && currentWish.getSexuality().equals(trait.getGender())) {
					people.add(new Pair()<user.totalScore(current), current>);
				}
				people.addAll(findSubsetMatch(user, current.rightChild));
			}
			return people;
			
		}

	}
}
