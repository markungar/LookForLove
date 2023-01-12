package LookForLove;

import java.io.*;
import java.util.*;
import java.lang.*;

public class DatabaseRunner {

	public static void main(String args[]) {
		String username = "", password = "";
		
		String fileName = "accountInfo.txt";
		Scanner sc = new Scanner(System.in);
		
		

		System.out.println("Welcome to LookForLove where true love finds you!");
		System.out.println("LookForLove is a dating app. In our app, users will be matched base on their preferences and common interests through our top secret algorithm.");
		System.out.print("Press anything to continue: ");
		sc.nextLine();
		

		System.out.println("Log In / Register");
		System.out.println("-----------------");
		System.out.println("Press 1 to log in");
		System.out.println("Press 2 to register");
		getChoice = sc.nextInt();
		
		if (getChoice == 1) {
			
			System.out.print("Enter your username: ");
			username = sc.nextLine();
			
			System.out.print("Enter your password: ");
			password = sc.nextLine();
			
			loginIn(username, password);
		} else if (getChoice == 2) {
			register();
		}
			
	do
	{
		getChoice = userMenu();


        	if (getChoice == 1) {
			System.out.println("Logging Out");
            		return;
        	} else if (getChoice == 2) {
			changeInfo(username);
		
			System.out.println("Your information has been altered");
        	} else if (getChoice == 3) {
			System.out.println(username);
		} else {
			findMatch(username);
		}
	}
	while(getChoice != 1);


	// loadFromFile()
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Purpose: From the given file, use its information to retrieve a Database for us to use. We achieve this by creating a pseudo-empty database and repeatedly inserting elements
	// 	to it until we reach the end of the file.
	// Note: We think that this is the most time-efficient way of coding a decent load method. However, this is for sure not the best way. This runs on O(nLogn) but it could achieve O(n)

	public Database loadFromFile(String fileName) {
		
		try {

			// A bufferedReader to read from fileName.txt
			BufferedReader in = new BufferedReader(new FileReader(fileName));

			// An empty Database.
			// Once we're done, we're going to return this to the main method.
			Database database;
			
			// Some temporary placeholders that we use to store information of each Person in the file.
			Account loginInfo;
			Character trait;
			WishCharacter wish;
			String description;
			String[] prompts = new String[4];

			// Read from the first line of the file is how many Person is in database;
			int count = Integer.valueOf(in.readLine());

			// Repeat a process n times, each time the process loops it reads a person and add it to database.
			for (int i = 0; i < count; i++) {
					
				// All the fields needed to create a Person from scratch. Going to read them all in later.
				String firstName, lastName, emailAddress, username, password, ethnicity, wishSexuality, wishEthnicity, description, gender;
				int phoneNumber, height, age, wishHeight, wishAgeMin, wishAgeMax;

				// Create loginInfo. Part 1/5.
				firstName = in.readLine();
				lastName = in.readLine();
				phoneNumber = Integer.valueOf(in.readLine());
				emailAddress = in.readLine();
				username = in.readLine();
				password = in.readLine();
				loginInfo = new Account(firstName, lastName, phoneNumber, emailAddress, username, password);

				// Create trait. Part 2/5.
				ethnicity = in.readLine();
				height = Integer.valueOf(in.readLine());
				age = Integer.valueOf(in.readLine());
				trait = new Character(ethnicity, height, age);

				// Create wish. Part 3/5.
				wishAgeMin = Integer.valueOf(in.readLine());
				wishAgeMax = Integer.valueOf(in.readLine());
				wishSexuality = in.readLine();
				wishEthnicity = in.readLine();
				wishHeight = in.readLine();
				in.readLine();
				wish = new WishCharacter(wishAgeMin, wishAgeMax, wishSexuality, wishHeight, 0);

				// Create prompts. Part 4/5.
				prompts[0] = in.readLine();
				prompts[1] = in.readLine();
				prompts[2] = in.readLine();
				prompts[3] = in.readLine();

				// Read description and gender. Part 5/5.
				description = in.readLine();
				gender = in.readLine();

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
			System.out.println("Something went wrong reading from file: " + fileName + "\nPlease try again.");
			return null;
		}

	}


	// saveToFile()
	// --------------------------------------------------------------------------------------------------------------------------------------------------------
	// Purpose: Based on the current contents of the binary tree inside database, write information of each person into the binary tree using a queue.
	// *Note 1: returns true if all process is successfully executed, false otherwise.
	// *Note 2: fileName specifies which file to write to.

	public boolean saveToFile(String fileName, Database database) {
		
		try {	
			// the number of Person in database;
			int count = database.loginLookup.size();

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
				Account loginInfo = current.getAccount();
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
					// 	Line 8: height
					// 	Line 9: age
					// 	Line 10: min. age of choice
					// 	Line 11: max. age of choice
					//	Line 12: gender preference of choice
					// 	Line 13: ethnicity of choice
					// 	Line 14: height of choice
					// 	Line 15: answer of prompt #1
					// 	Line 16: answer of prompt #2
					// 	Line 17: answer of prompt #3
					// 	Line 18: answer of prompt #4
					//	Line 19: description
					//	Line 20: gender of user

				// writing all information from loginInfo to the file.
				out.write(loginInfo.getFirstName());
				out.newLine();
				out.write(loginInfo.getLastName());
				out.newLine();
				out.write(Integer.toString(loginInfo.getPhoneNumber()));
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
				out.write(Integer.toString(trait.getHeight()));
				out.newLine();
				out.write(Integer.toString(trait.getAge()));
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

				// writing the gender of current to the file.
				if (current instanceof Male) {
					out.write("1");
				}
				else if (current instanceof Female) {
					out.write("2");
				}
				else {
					out.write("3");
				}
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
			System.out.println("Something went wrong writing to file: " + fileName + "\nPlease try again.");
			out.close();
			return false;
		}

	}

}
