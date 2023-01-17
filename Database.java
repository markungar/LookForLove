//package LookForLove;

import java.util.*;

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

	public Person logInUser; // the current user that's currently logged in the Database, null if no user is logged in.
	public Person root; // the root of our unbalanced binary tree.
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
		loginLookUp = new TreeMap<String, LoginKey>();
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
			this.loginLookUp.put(newNode.loginInfo.getUsername(), new LoginKey(newNode.loginInfo.getPassword(), newNode));
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
		
		// finding the LoginKey for that corresponding username.
		LoginKey userInfo = loginLookUp.get(username);
		
		// no such username was appended to the system.
		if (userInfo == null) {
			System.out.println("Username doesn't exist. Please try again.");
			return false;
		}
		// password that the user entered is not the same as the one stored in our system.
		else if (!(userInfo.getPassword().equals(password))) {
			System.out.println("Invalid password. Please try again");
			return false;
		}

		// successful, change logInUser to the Person that the user requested.
		logInUser = userInfo.getPerson();
		return true;
	
	}

		// logout()
		// ---------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Set logInUser to null; which effectively "logged" the user out of the database.

	public boolean logout() {
		
		// change logInUser to point to an empty memory in the computer.
		try {
			logInUser = null;
			return true;
		}
		catch (Exception e) {
			System.out.println("Trouble logging out. Please try again.");
			return false;
		}
	}

		// changeInfo()
		//
		//
		//
	
	public void changeInfo() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nWhat information would you like to change?");
		System.out.println("Press 1 to edit account information (Username, Password...)");
		System.out.println("Press 2 to edit your traits");
		System.out.println("Press 3 to edit your preferences");
		System.out.println("Press 4 to edit your prompts");
		System.out.println("Press 5 to edit your description");
		System.out.println("Enter anything else to exit.");

		try {
			int choice = sc.nextInt();
			
			if(choice == 1) {
				System.out.println("\nPress 1 to edit your username"
						+ "\nPress 2 to edit your password"
						+ "\nPress 3 to edit first name"
						+ "\nPress 4 to edit last name"
						+ "\nPress 5 to edit email"
						+ "\nPress 6 to edit phone number");

				int secChoice = sc.nextInt();

				if(secChoice == 1) {
					System.out.print("Enter new username: ");
					String newUser = sc.nextLine();
					logInUser.loginInfo.setUsername(newUser);
				}
				else if(secChoice == 2) {
					System.out.print("Enter new password: ");
					String newPass = sc.nextLine();
					logInUser.loginInfo.setPassword(newPass);
				}
				else if(secChoice == 3) {
					System.out.print("Enter new first name: ");
					String newFirst = sc.nextLine();
					logInUser.loginInfo.setFirstName(newFirst);
				}
				else if(secChoice == 4) {
					System.out.print("Enter new last name: ");
					String newLast = sc.nextLine();
					logInUser.loginInfo.setLastName(newLast);
				}
				else if(secChoice == 5) {
					System.out.print("Enter new email: ");
					String newEmail = sc.nextLine();
					logInUser.loginInfo.setEmailAddress(newEmail);
				}
				else if(secChoice == 6) {
					long newNumber = -1;
					do {
						try {
							System.out.print("Enter new phone number (without dashes): ");
							newNumber = sc.nextLong();
						} catch(Exception e) {
							System.out.println("Please enter a valid phone number");
						}

					} while(newNumber == -1);

					logInUser.loginInfo.setPhoneNumber(newNumber);
				}
			} else if(choice == 2) {
				System.out.println("\nPress 1 to edit race"
						+ "\nPress 2 to edit height"
						+ "\nPress 3 to edit age");

				int secChoice = sc.nextInt();

				if(secChoice == 1) {
					System.out.print("Enter new race (options ~ white, asian, black, latino, other): ");
					String newRace = sc.nextLine();			
					logInUser.trait.setEthnicity(newRace);
				} else if(secChoice == 2) {
					int newHeight = -1;
					do {
						try {
							System.out.print("Enter new height (In centimeters): ");
							newHeight = sc.nextInt();
						} catch(Exception e) {
							System.out.println("Please enter a valid height");
						}

					} while(newHeight == -1);

					logInUser.trait.setHeight(newHeight);
				} else if(secChoice == 3) {
					int newAge = -1;
					do {
						try {
							System.out.print("Enter new age: ");
							newAge = sc.nextInt();
						} catch(Exception e) {
							System.out.println("Please enter a valid age");
						}

					} while(newAge == -1);

					logInUser.trait.setAge(newAge);
				}
			} else if(choice == 3) {
				System.out.println("\nPress 1 to edit preferred gender"
						+ "\nPress 2 to edit preferred culture"
						+ "\nPress 3 to edit preferred height"
						+ "\nPress 4 to edit minimum age"
						+ "\nPress 5 to edit maximum age");
				
				int secChoice = sc.nextInt();
				
				if(secChoice == 1) {
					String gender = "";
					do {
						System.out.print("Enter new preferred gender (male, female, other, all): ");
						gender = sc.nextLine();
					} while(!gender.equals("male") || !gender.equals("female") || !gender.equals("other") || !gender.equals("all"));
					
					logInUser.wish.setSexuality(gender);
				} else if(secChoice == 2) {
					String culture = "";
					do {
						System.out.print("Enter new preferred culture (white, asian, black, latino, other): ");
					} while(!culture.equals("white") || !culture.equals("asian") || !culture.equals("black") || !culture.equals("latino") || !culture.equals("other"));
					
					logInUser.wish.setEthnicity(culture);
				} else if(secChoice == 3) {
					int height = -1;
					do {
						try {
							System.out.print("Enter new preferred height: ");
							height = sc.nextInt();
						} catch(Exception e) {
							System.out.println("Please enter a valid height");
						}

					} while(height == -1);

					logInUser.wish.setHeight(height);
				} else if(secChoice == 4) {
					int minAge = -1;
					do {
						try {
							System.out.print("Enter new preferred minimum age: ");
							minAge = sc.nextInt();
						} catch(Exception e) {
							System.out.println("Please enter a valid age");
						}

					} while(minAge == -1);

					logInUser.wish.setAgeMin(minAge);
				} else if(secChoice == 5) {
					int maxAge = -1;
					do {
						try {
							System.out.print("Enter new preferred maximum age: ");
							maxAge = sc.nextInt();
						} catch(Exception e) {
							System.out.println("Please enter a valid age");
						}

					} while(maxAge == -1);

					logInUser.wish.setAgeMax(maxAge);
				}
			} else if(choice == 4) {
				System.out.println("\nPress 1 to edit your favourite movie"
						+ "\nPress 2 to edit your favourite sport"
						+ "\nPress 3 to edit your favourite season"
						+ "\nPress 4 to edit your favourite music genre");
				
				int secChoice = sc.nextInt();
				
				if(secChoice == 1) {
					System.out.print("Enter new favourite movie: ");
					String movie = sc.nextLine();
					
					logInUser.setMovie(movie);
				} else if(secChoice == 2) {
					System.out.print("Enter new favourite sport: ");
					String sport = sc.nextLine();
					
					logInUser.setSport(sport);
				} else if(secChoice == 3) {
					System.out.print("Enter new favourite season: ");
					String season = sc.nextLine();
					
					logInUser.setSeason(season);
				} else if(secChoice == 4) {
					System.out.print("Enter new favourite music genre: ");
					String genre = sc.nextLine();
					
					logInUser.setGenre(genre);
				}
			} else if(choice == 5) {
				System.out.print("Enter new description: ");
				String description = sc.nextLine();
				
				logInUser.setDescription(description);
			}
		} catch(Exception e) {
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

		String firstName = "", lastName = "", gender = "", emailAddress = "", username = "", password = "", passwordCheck = "", ethnicity = "", description = "";
		String wishEthnicity = "", wishSexuality = "";

		int age = -1, height = -1;
		long phoneNumber = -1;
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
				System.out.println("Type in:");
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
			System.out.println("*Note: Please enter with out any dashes (\"-\")");
			System.out.print("Please provide your answer: ");

			try {

				result = sc.nextLine();
				phoneNumber = Long.parseLong(result); // if !(result instanceof long), jump to the catch block
				
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
		System.out.println("Congratulations! You have finished part 1 of the registering process.");
		System.out.println("Lucky you, the boring part is over! Now we get to the more interesting stuff.");
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
		System.out.println("B1. Which race are you? (options ~ white, asian, black, latino, other)");
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
				height = Integer.valueOf(result); // Jumps to catch block if !(result instanceof int)

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
			System.out.println("Type in:");
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
		System.out.println("B5. In which culture do you think your ideal one is most attractive from? (options ~ white, asian, black, latino, other)");
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
			trait = new Character(ethnicity, gender, height, age);
			wish = new WishCharacter(wishAgeMin, wishAgeMax, wishSexuality, wishEthnicity, wishHeight, 0);

			Person person;
            		if (gender.equals("male")) {
                    		person = new Male(loginInfo, trait, wish, description, prompts);
            		}
            		else if (gender.equals("female")) {
                   	 	person = new Female(loginInfo, trait, wish, description, prompts);
            		}
            		else {
                    		person = new Other(loginInfo, trait, wish, description, prompts);
            		}

			// insert the new Person into database's tree.
			insert(person);
		} 
		catch(Exception e) {
		    	sc.close();
			return false;
		}

       		sc.close();
		return true;

	} // end register()
	
	
		// findMatch()
		// ------------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: A wrapper for findSubsetMatch().
	
	public LinkedList<PersonScorePair> findMatch(Person user) {
		return findSubsetMatch(user, this.root);
	}
	
		// findSubsetMatch()
		// ------------------------------------------------------------------------------------------------------------------------------------------------------
		// Purpose: Find all the nodes that matches the conditions listed by user for all nodes that's under (and include this) node in an unsorted linkedlist.
		//		For each element in the linkedlist has a Person object and a score of the Person when matched with user. Going to use the int later to
		// 		determine the order of Person to display when we eventually sort the linkedlist.
		
	public LinkedList<PersonScorePair> findSubsetMatch(Person user, Person current) {
			
		// create an empty linkedlist that we're going to return later (that might ad more elements if we found any matches in the subtree).
		LinkedList<PersonScorePair> people = new LinkedList<PersonScorePair>();

		// current is a valid Person and not an empty placeholder
		if (current != null) {

			// digging out current's information to gain easier access.
            		Character trait = user.getTrait();
            		Character currentTrait = current.getTrait();
            		WishCharacter wish = user.getWish();
            		WishCharacter currentWish = current.getWish();

			// wish.fit(currentTrait) returns:
			//
			//      -1     |     0      |       1   
			//           AgeMin       AgeMax
			//
			// if current.age is smaller than age min, returns -1;
			// else if current.age is inside [AgeMin, AgeMin] inclusive, returns 0;
			// else current.age is bigger than ageMax, return 1;
			
			// current.age is smaller than age min.
            		if (wish.fit(currentTrait) == -1) {
				// Check the subtree that has current.rightChild as the root.
                		return findSubsetMatch(user, current.rightChild);
            		}
    			// current.age is bigger than age max
            		else if (wish.fit(currentTrait) == 1) {
				// Check the subtree that has current.leftChild as the root
                		return findSubsetMatch(user, current.leftChild);
            		}
			// current is inside [ageMin, ageMax]
			else {

				// find 
				people.addAll(findSubsetMatch(user, current.leftChild));
				if ((wish.getSexuality().equals(currentTrait.getSexuality()) || wish.getSexuality().equals("all")) && 
                        		(currentWish.getSexuality().equals(trait.getSexuality()) || currentWish.getSexuality().equals("all"))) {
						
					people.add(new PersonScorePair(current, user.totalScore(current)));
					
				}
				people.addAll(findSubsetMatch(user, current.rightChild));
				
			}
		}

		return people;
			
	} // end findSubsetMatch()
	
	public PersonScorePair[] sortMatches(LinkedList<PersonScorePair> list) {

		Object[] buffer = list.toArray();
        	PersonScorePair[] people = Arrays.copyOf(buffer, buffer.length, PersonScorePair[].class);
		int size = people.length;

		try {
			for (int i = 0; i < size; i++) {
			
				int maxIndex = i;
				for (int j = i + 1; j < size; j++) {
					if (people[maxIndex].compareTo(people[j]) == -1) {
						maxIndex = j;
					}
				}
				PersonScorePair temp = people[i];
				people[i] = people[maxIndex];
				people[maxIndex] = temp;
			}
			return people;
		}
		catch (Exception e) {
			return null;
		}

	} // end sortMatches()
	
} // end Database
