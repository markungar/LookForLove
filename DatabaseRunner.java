import java.io.*;
import java.util.*;

public class DatabaseRunner {

	public static void main(String args[]) {

		String username = "", password = "";
		//initializes log in details
		
		boolean loggedIn = false, returnToMenu = false;

		int getChoice = -1;

		String fileName = "accountInfo.txt";
		//sets the name for the file in which names are stored 
		
		Scanner sc = new Scanner(System.in);

		Database test = loadFromFile(fileName);
		//System.out.println(test.loginLookUp.size());
        System.out.println(test.root.getWish().getAgeMin() + ", " + test.root.getWish().getAgeMax());

		System.out.println("Welcome to LookForLove where true love finds you!");
		System.out.println("LookForLove is a dating app. In our app, users will be matched base on their preferences and common interests through our top secret algorithm.");
		System.out.print("Press anything to continue: ");
		sc.nextLine();
		//Start program header, welcoming users to LookForLove

		do 
		{
			returnToMenu = false;
		System.out.println("Log In / Register");
		System.out.println("-----------------");
		System.out.println("Press 1 to log in");
		System.out.println("Press 2 to register");
		getChoice = sc.nextInt();
		sc.nextLine();
		//gets choice if they would like to log in or register

		if (getChoice == 1) {

			do
			{
				System.out.print("Enter your username: ");
				username = sc.nextLine();

				System.out.print("Enter your password: ");
				password = sc.nextLine();
				//gets user name and password from user
				
				loggedIn = test.logIn(username, password);
				//checks to see if the username matches an existing account aswell as if the password matches the specified account
			}
			while (!loggedIn);
			
		} else if (getChoice == 2) {

			
				boolean success = test.register();

				if (success) {
					saveToFile(fileName, test);
				} else {
					returnToMenu = true;
				}
		}
		}
		while (returnToMenu);

		if (getChoice == 1) {
			System.out.println("Thank you for logging in :)\n");
		} else if (getChoice == 2) {
			System.out.println("Thank you for registering :)\n");
		}

		do
		{
			//getChoice = userMenu();
			
			System.out.println("\nWelcome " + username);
			
			System.out.println("Press 1 to log out");
			System.out.println("Press 2 to change your personal information");
			System.out.println("Press 3 to see your account details");
			System.out.println("Press 4 to find your dream partner");

			getChoice = sc.nextInt();

			if (
				getChoice == 1) {
				System.out.println("Logging Out");
				return;
			} else if (getChoice == 2) {
				test.changeInfo();
				
				//System.out.println(test.root.getTrait().getSexuality());
				saveToFile(fileName, test);
				
				System.out.println("Your information has been altered");
			} else if (getChoice == 3) {
				System.out.println(test.logInUser);

			} else {

				System.out.println("DREAM PARTNERS (and their matching score)");
				System.out.println("- - - - - - - - - - - - - - - - - - - - -");

                LinkedList<PersonScorePair> unsortedLinkedMatch = test.findMatch(test.root);
                PersonScorePair[] matches = test.sortMatches(unsortedLinkedMatch);

				for (int i = 0; i < matches.length; i++) {
                    PersonScorePair match = matches[i];

					String[] personPrompts = new String[4];
					personPrompts = match.person.getPrompts();

                    System.out.println(match.person.getLoginInfo().firstName + " " + match.person.loginInfo.getLastName() + "\nmatch score: [" + match.score + "]");
					System.out.println("\nFavorite Movie: " + personPrompts[0] + "\nFavorite Sport: " + personPrompts[1] +"\nFavorite Season: " + personPrompts[2] +"\nFavorite Music Genre: " + personPrompts[3]);
					System.out.println(match.person.getDescription());

					for (int f = 0; f < match.person.getDescription().length(); f = f+2) {
						System.out.print("- ");
					}

					System.out.println();

                }
				//if any number other than 1, 2 or 3 is entered, our expert and advanced program begins finding a match for you
			}
		} while(getChoice != 1);

		
		saveToFile(fileName, test);
		sc.close();
	}

	//opens another menu

	// loadFromFile()
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Purpose: From the given file, use its information to retrieve a Database for us to use. We achieve this by creating a pseudo-empty database and repeatedly inserting elements
	// 	to it until we reach the end of the file.
	// Note: We think that this is the most time-efficient way of coding a decent load method. However, this is for sure not the best way. This runs on O(nLogn) but it could achieve O(n)

	public static Database loadFromFile(String fileName) {

		try {

			// A bufferedReader to read from fileName.txt
			BufferedReader in = new BufferedReader(new FileReader(fileName));

			// An empty Database.
			// Once we're done, we're going to return this to the main method.
			Database database = null;

			// Some temporary placeholders that we use to store information of each Person in the file.
			Account loginInfo;
			Character trait;
			WishCharacter wish;
			String[] prompts = new String[4];

			// Read from the first line of the file is how many Person is in database;
			int count = Integer.parseInt(in.readLine());

			// Repeat a process n times, each time the process loops it reads a person and add it to database.
			for (int i = 0; i < count; i++) {

				// All the fields needed to create a Person from scratch. Going to read them all in later.
				String firstName, lastName, emailAddress, username, password, ethnicity, wishSexuality, wishEthnicity, description, gender;
				int height, age, wishHeight, wishAgeMin, wishAgeMax;
				long phoneNumber;

				// The format goes as follows:
				// 	Line 1: first name
				// 	Line 2: last name
				// 	Line 3: phone number
				// 	Line 4: email address
				// 	Line 5: username
				// 	Line 6: password
				// 	Line 7: ethnicity
				// 	Line 8: gender
				// 	Line 9: age
                //  Line 10: height
				// 	Line 11: min. age of choice
				// 	Line 12: max. age of choice
				//	Line 13: gender preference of choice
				// 	Line 14: ethnicity of choice
				// 	Line 15: height of choice
				// 	Line 16: Useless age
				// 	Line 17: answer of prompt #1
				// 	Line 18: answer of prompt #2
				// 	Line 19: answer of prompt #3
				// 	Line 20: answer of prompt #4
				//	Line 21: description

				// Create loginInfo. Part 1/5.
				firstName = in.readLine();
				lastName = in.readLine();
				phoneNumber = Long.parseLong(in.readLine());
				emailAddress = in.readLine();
				username = in.readLine();
				password = in.readLine();
				loginInfo = new Account(firstName, lastName, phoneNumber, emailAddress, username, password);

				// Create trait. Part 2/5.
				ethnicity = in.readLine();
				gender = in.readLine();
                age = Integer.parseInt(in.readLine());
				height = Integer.parseInt(in.readLine());
				trait = new Character(ethnicity, gender, age, height);

				// Create wish. Part 3/5.
				wishAgeMin = Integer.parseInt(in.readLine());
				wishAgeMax = Integer.parseInt(in.readLine());
				wishSexuality = in.readLine();
				wishEthnicity = in.readLine();
				wishHeight = Integer.parseInt(in.readLine());
				in.readLine();
				wish = new WishCharacter(wishAgeMin, wishAgeMax, wishSexuality, wishEthnicity, wishHeight, 0);

				// Create prompts. Part 4/5.
				prompts[0] = in.readLine();
				prompts[1] = in.readLine();
				prompts[2] = in.readLine();
				prompts[3] = in.readLine();

				// Read description and gender. Part 5/5.
				description = in.readLine();

				// Create Person.
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


				// If this is the first loop, make a new database with person created above as its root.
				if (i == 0) {
					database = new Database(person);
				} 
				// If this is not the first loop, just insert person into our tree.
				else {
					database.insert(person);
				}

			}
			// Successfully executed the entire method, return database.
			in.close();
			return database;
		}
		// Unsuccessful with the method, return null.
		catch (IOException iox) {
			System.out.println("Something went wrong reading from file: " + fileName + "\n" + iox.getMessage());
			return null;
		}

	}


	// saveToFile()
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// Purpose: Based on the current contents of the binary tree inside database, write information of each person into the binary tree using a queue.
	// *Note 1: returns true if all process is successfully executed, false otherwise.
	// *Note 2: fileName specifies which file to write to.

	public static boolean saveToFile(String fileName, Database database) {

		try {	
			// the number of Person in database;
			int count = database.loginLookUp.size();

			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));

			// A queue that is keep track of Person's that haven't been writen to the file. 
			Queue<Person> que = new LinkedList<>();
			que.add(database.root); // add database's root to the queue as the initial node to be searched through.

			// write to the file the total Person count in database as the file's first line.
			out.write(Integer.toString(count));
			out.newLine();


			// main loop that traverses through the tree.
			// One could think of it's steps as a similar function to the breadth-first search algo.
			// First it has a starting node (which is the root), then it'll search through the root's children. 
			// Then the children of the root's children.
			// and so on.
			// The queue ends when there's no more node to be search through, therefore out would be closed.
			// *Note: if any error occurs during the loop, there would be a catch that would return false; so if the queue ever ends, the function would return true,
			// 	because no errors occurred.

			while (que.peek() != null) {

				// pulls the first element of the queue out of the it.
				Person current = que.poll();

				// We take out current's information so we wouldn't have to type a lot to use the setters and getters.
				Account loginInfo = current.getLoginInfo();
				Character trait = current.getTrait();
				WishCharacter wish = current.getWish();
				String description = current.getDescription();
				String[] prompts = current.getPrompts();


				// The format goes as follows:
				// 	Line 1: first name
				// 	Line 2: last name
				// 	Line 3: phone number
				// 	Line 4: email address
				// 	Line 5: username
				// 	Line 6: password
				// 	Line 7: ethnicity
				// 	Line 8: gender
				// 	Line 9: age
                //  Line 10: height
				// 	Line 11: min. age of choice
				// 	Line 12: max. age of choice
				//	Line 13: gender preference of choice
				// 	Line 14: ethnicity of choice
				// 	Line 15: height of choice
				// 	Line 16: Useless age
				// 	Line 17: answer of prompt #1
				// 	Line 18: answer of prompt #2
				// 	Line 19: answer of prompt #3
				// 	Line 20: answer of prompt #4
				//	Line 21: description

				// writing all information from loginInfo to the file.
				out.write(loginInfo.getFirstName());
				out.newLine();
				out.write(loginInfo.getLastName());
				out.newLine();
				out.write(Long.toString(loginInfo.getPhoneNumber()));
				out.newLine();
				out.write(loginInfo.getEmailAddress());
				out.newLine();
				out.write(loginInfo.getUsername());
				out.newLine();
				out.write(loginInfo.getPassword());
				out.newLine();

				// writing all information from trait to the file.
				out.write(trait.getEthnicity());
				out.newLine();
				out.write(trait.getSexuality());
				out.newLine();
                out.write(Integer.toString(trait.getAge()));
				out.newLine();
				out.write(Integer.toString(trait.getHeight()));
				out.newLine();


				// writing all information from wish to the file.
				out.write(Integer.toString(wish.getAgeMin()));
				out.newLine();
				out.write(Integer.toString(wish.getAgeMax()));
				out.newLine();
				out.write(wish.getSexuality());
				out.newLine();
				out.write(wish.getEthnicity());
				out.newLine();
				out.write(Integer.toString(wish.getHeight()));
				out.newLine();
				out.write("0");
				out.newLine();

				// writing all prompt answers to the file.
				out.write(prompts[0]);
				out.newLine();
				out.write(prompts[1]);
				out.newLine();
				out.write(prompts[2]);
				out.newLine();
				out.write(prompts[3]);
				out.newLine();

				// writing the description of current to the file.
				out.write(description);
				out.newLine();

				// if current has a left child, add it to the queue because it has not been added to the file.
				if (current.leftChild != null) {
					que.add(current.leftChild);
				}
				// if current has a right child, add it to the queue because it has not been added to the file.
				if (current.rightChild != null) {
					que.add(current.rightChild);
				}
			}

			// all went smoonthly, close the bufferedReader and return successful.
			out.close();
			return true;

		}
		catch (IOException iox) {

			// something went wrong. Print an error message, close the bufferedReader and return unsuccessful.
			System.out.println("Something went wrong writing to file: " + fileName + "\n" + iox.getMessage());
			return false;
		}

	}

	

}
