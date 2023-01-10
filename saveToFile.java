import java.io.*;
import java.util.*;
import java.lang.*;

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
					// 	Line 12: ethnicity of choice
					// 	Line 13: height of choice
					// 	Line 14: answer of prompt #1
					// 	Line 15: answer of prompt #2
					// 	Line 16: answer of prompt #3
					// 	Line 17: answer of prompt #4

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
			System.out.println("Something went wrong writing to file: " + fileName + "\nPlease try again.");
			out.close();
			return false;
		}

	}