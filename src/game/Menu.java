package game;

import java.util.Scanner;

public class Menu {

	private Scanner input;
	private String title;
	private String[] options;
	
	
	public Menu(String[] options, String title){
		this.input = new Scanner(System.in);
		this.options = options;
		this.title = title;
	}
	
	private void displayChoice() {
		System.out.print("=========");
		System.out.print(title);
		System.out.print("=========\n");
		for(int i = 0; i < options.length;i++) {
			System.out.println(i + 1 + ". "+ options[i]);
		}
	}
	
	public int getChoice() {
		displayChoice();
		boolean ok = false;
		int response = 0;
		do {
			try {
				 response = input.nextInt();
				 if(response >= 0 && response <= options.length ) {
					 ok = true;
				 }
				 else {
					 System.out.println("Enter a value between 1 and " + options.length);
				 }
				 
			}catch(Exception e ) {
				System.out.println("Error input");
				input.nextLine();
				displayChoice();
			}
		}while(!ok);
		

		return response - 1;
	}
}
