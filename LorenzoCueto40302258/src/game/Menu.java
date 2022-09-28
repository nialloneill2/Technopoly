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
		int response = input.nextInt();
		while(response > options.length || response <= 0) {
			System.out.println("Error, please choose a valid choice");
			displayChoice();
			response = input.nextInt();
		}
		input = null;
			return response - 1;
	}
}
