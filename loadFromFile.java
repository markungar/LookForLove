import java.io.*;
import java.util.*;
import java.lang.*;

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
				String firstName, lastName, emailAddress, username, password, ethnicity, wishEthnicity, description;
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
				wishEthnicity = in.readLine();
				wishHeight = in.readLine();
				in.readLine();
				wish = new WishCharacter(wishEthnicity, wishHeight, wishAgeMin, wishAgeMax);

				// Create prompts. Part 4/5.
				prompts[0] = in.readLine();
				prompts[1] = in.readLine();
				prompts[2] = in.readLine();
				prompts[3] = in.readLine();

				// Read description. Part 5/5.
				description = in.readLine();

				// Create Person.
				Person person = new Person(loginInfo, trait, wish, description, prompts);
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