package edu.unca.csci202;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Gameboard {
	static final int BOARD_HIEGHT = 10;
	static final int BOARD_WIDTH = 10;
	Cell[][] cell = new Cell[BOARD_HIEGHT][BOARD_WIDTH];
	Cell[][] cell2 = new Cell[BOARD_HIEGHT][BOARD_WIDTH];
	Stack<Cell> cellStack =  new Stack<Cell>();
	Stack<Cell> cellStack2 =  new Stack<Cell>();
	private Random generator = new Random();
	int x = generator.nextInt(7);
	int userRow;
	int userColumn;
	int mineCount;
	int mineCount2;
	int tenMines;
	Scanner sc = new Scanner(System.in);
	boolean yesInput = false;
	boolean peek = false;
	boolean win = false;
	boolean dontUpdate = false;
	boolean playAgain = false;
	
	public Gameboard() {
		for (int i = 0; i < BOARD_HIEGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){ 
                cell[i][j] = new Cell(false, i, j);
            
                
            }
        }
		setAdjacentMines();
		
		run();
	}
	
	public void run() {
		randomMine2();
		generateUserCells();
		
		while (!playAgain) {
			if (!win) {
				peek();
				if (peek) {
					generateUserCells();
					peek = false;
				}
				
				processUserInput();
				detection();
				changeChar();
				explode();
				stackCells();
				printCellStack();
				//explode2();
				
				
				generateUserCells();
				
			}
			lostGame();
				
			mineCount = 0;
			dontUpdate = false;
		} 
	}
	
	public void processUserInput() {
		
		boolean valid_input = false;
		
		valid_input = false;
		while(!valid_input) {
			System.out.print("Enter a row (1 - 8): ");
			String userRowS = sc.nextLine();
			try {
				userRow = Integer.parseInt(userRowS);
				valid_input = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a Number.");
			}
		}
		valid_input = false;
		while(!valid_input) {
			System.out.print("Enter a column (1 - 8): ");
			String userColumnS = sc.nextLine();
			try {
				userColumn = Integer.parseInt(userColumnS);
				valid_input = true;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a Number.");
			}
		}
		valid_input = false;
		Scanner keyboard_input = new Scanner(System.in);
		while(!valid_input) {
			System.out.println("Guess if there is mine in this position (y/n)");
			String input = keyboard_input.nextLine();
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
				valid_input = true;
				if(input.equalsIgnoreCase("y")) {
					yesInput =true;
				} else if(input.equalsIgnoreCase("n")) {
					yesInput = false;
				}
			} else {
				System.out.println("invalid input");
			}
		}
		
	}
	
	public void peek() {
		boolean valid_input = false;
		
		valid_input = false;
		Scanner keyboard_input1 = new Scanner(System.in);
		while(!valid_input) {
			System.out.println("Would Like to Peak? (y/n)");
			String input = keyboard_input1.nextLine();
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
				valid_input = true;
				if(input.equalsIgnoreCase("y")) {
					peek = true;
					//generateUserCells();
				} else if(input.equalsIgnoreCase("n")) {
					peek = false;
				}
			} else {
				System.out.println("invalid input");
			}
		}
	}
	
	public void lostGame() {
		if(cell[userRow][userColumn].isMine() && yesInput) {
			tenMines++;
		} 
		
		if (tenMines == 10) {
			System.out.println("You Win!!!");
			dontUpdate = true;
			playAgain = true;
			win = true;
		}
		if (cell[userRow][userColumn].isMine() && !yesInput) {
			boolean valid_input = false;
			Scanner keyboard_input = new Scanner(System.in);
			while(!valid_input) {
				System.out.println("You lost, Play Again?(y/n)");
				String input = keyboard_input.nextLine();
				
				if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n")) {
					valid_input = true;
					if(input.equalsIgnoreCase("y")) {
						playAgain = true;
						generateUserCells();				
						dontUpdate = true;
						playAgain = false;
						userRow = 0;
						userColumn = 0;
					} else if(input.equalsIgnoreCase("n")) {
						System.out.println();
						System.out.println("Game Over");
						playAgain = true;
					}
				} else {
					System.out.println("invalid input");
				}
			}
		}
	}
	public void generateUserCells() {
		
		for(int i = 1; i < 9; i++ ) {
			
			for(int j = 1; j < 9; j++ ) {
   
				if (peek) {
					cell[i][j].setPeek(true);
					cell2[i][j].setPeek(true);
					System.out.print(cell[i][j] + " ");	
				} if (!peek) {
					cell[i][j].setPeek(false);
					System.out.print(cell[i][j] + " ");	
				}
			
				
			}		
		//y
		System.out.println();
		}
	}
	public void stackCells() {
	
		System.out.println();
		for(int i = 1; i < 9; i++ ) {
			
			for(int j = 1; j < 9; j++ ) {
				cellStack.push(cell2[i][j]);
				
				//System.out.print(cell2[i][j] + " ");
			}		
		
		}
		
		for(int i = 1; i < 9; i++ ) {
			
			for(int  j = 1; j < 9 ; j++) {
				cellStack2.push(cellStack.pop());
				
			}		
	
		}
		
	}
	
	public void printCellStack() {
		for(int i = 1; i < 9; i++ ) {
			
			for(int  j = 1; j < 9 ; j++) {
				System.out.print(cell2[i][j]+ " ");
				
			}		
			System.out.println();
		}
	}
	
	public void randomMine() {
		int numMines = 0;
		while (numMines < 10) {
			
			int number = generator.nextInt(10);
			int number2 = generator.nextInt(10);
			while (number < 1 || number2 < 1 || number > 8|| number2 > 8) {
				number = generator.nextInt(10);
				number2 = generator.nextInt(10);
			}
			if (cell[userRow][userColumn].isMine()) {
				continue;
			} else {
				cell[number][number2].setMine(true);	
				cell2[number][number2].setMine(true);
				numMines++;		
			}		
		}						
	}	
	public void randomMine2() {
		Random rand = new Random();
	    int mineCount = 0;
	    while (mineCount < 10)
	    {
	        int randomInteger = (int) (rand.nextDouble() * cell.length);
	        int randomInteger2 = (int) (rand.nextDouble() * cell[0].length);
	        while (randomInteger < 1 || randomInteger2 < 1 || randomInteger > 8|| randomInteger2 > 8) {
	        	randomInteger = (int) (rand.nextDouble() * cell.length);
	        	randomInteger2 = (int) (rand.nextDouble() * cell[0].length);
			}
	        if (cell[randomInteger][randomInteger2].isMine())
	            continue;
	        else
	        {
	            cell[randomInteger][randomInteger2].setMine(true);
	            cell2[randomInteger][randomInteger2].setMine(true);
	            mineCount++;
	        }
	    }		
	}	
	
	public void explode() {
		Stack<Integer> stack =  new Stack<Integer>();
		for(int i = 1; i < 9; i++ ) {
			for(int j = 1; j < 9; j++ ) {
				if (cell2[i - 1][j].isMine()) mineCount2++;
				if (cell2[i - 1][j - 1].isMine()) mineCount2++;
				if (cell2[i - 1][j + 1].isMine()) mineCount2++;
				if (cell2[i][j - 1].isMine()) mineCount2++;
				if (cell2[i][j + 1].isMine()) mineCount2++;
				if (cell2[i + 1 ][j].isMine()) mineCount2++;
				if (cell2[i + 1 ][j -1].isMine()) mineCount2++;
				if (cell2[i + 1][j + 1].isMine()) mineCount2++;
				changeChar2(i,j, mineCount2);
				
				stack.push(mineCount2);
				mineCount2 = 0;	
			}
		}
		
			
	}
	
	private void setAdjacentMines() {

		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				int index[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 },
						{ 0, -1 } };

				int count =0;
				for (int k = 0; k < index.length; k++) {
					try {
						Boolean temp = cell[i + index[k][0]][j + index[k][1]].isMine();
						if (temp) {
							
							cell[i][j].setMineNeighbors(count++);;
						}
					} catch (Exception e) {
						
					}

				}

			}

		}

	}
		
	
	
	public void detection() {
		if (cell[userRow - 1][userColumn].isMine()) mineCount++;
		if (cell[userRow - 1][userColumn - 1].isMine()) mineCount++;
		if (cell[userRow - 1][userColumn + 1].isMine()) mineCount++;
		if (cell[userRow][userColumn - 1].isMine()) mineCount++;
		if (cell[userRow][userColumn + 1].isMine()) mineCount++;
		if (cell[userRow + 1 ][userColumn].isMine()) mineCount++;
		if (cell[userRow + 1 ][userColumn -1].isMine()) mineCount++;
		if (cell[userRow + 1][userColumn + 1].isMine()) mineCount++;
	}
	
	public void changeChar() {
		if (!cell[userRow][userColumn].isMine()) {
			if(mineCount == 0) {
				cell[userRow][userColumn].setZeroAdjacent(true);
			} else if(mineCount == 1) {
				cell[userRow][userColumn].setOneAdjacent(true);
			} else if(mineCount == 2) {
				cell[userRow][userColumn].setTwoAdjacent(true);
			} else if(mineCount == 3) {
				cell[userRow][userColumn].setThreeAdjacent(true);
			} else if(mineCount == 4) {
				cell[userRow][userColumn].setFourAdjacent(true);
			} 
		}
		if (cell[userRow][userColumn].isMine() && yesInput) {
			cell[userRow][userColumn].setUserMine(true);
		}
	}
	
	public void changeChar2(int i, int j, int mineCount) {
		if (!cell2[i][j].isMine()) {
			if(mineCount == 0) {
				cell2[i][j].setZeroAdjacent(true);
				cellStack.push(cell2[i][j]);
			} else if(mineCount == 1) {
				cell2[i][j].setOneAdjacent(true);
				cellStack.push(cell2[i][j]);
			} else if(mineCount == 2) {
				cell2[i][j].setTwoAdjacent(true);
				cellStack.push(cell2[i][j]);
			} else if(mineCount == 3) {
				cell2[i][j].setThreeAdjacent(true);
				cellStack.push(cell2[i][j]);
			} else if(mineCount == 4) {
				cell2[i][j].setFourAdjacent(true);
				cellStack.push(cell2[i][j]);
			} 
		}
	}
	
	
}
