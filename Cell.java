package edu.unca.csci202;

public class Cell {
	private boolean isMine;
	private boolean peek;
    private boolean selected = false;
    private boolean zeroAdjacent = false;
    private boolean oneAdjacent = false;
    private boolean twoAdjacent = false;
    private boolean threeAdjacent = false;
    private boolean fourAdjacent = false;
    private int row;
	private int col;
	private boolean isVisible;
	private boolean isFlagged;
	private int mineNeighbors;
    private int numBombs;
    private boolean userMine;
    private static boolean gameOver = false;
    
    public Cell(boolean isMine, int numBombs) {
        gameOver = false;
        this.isMine = isMine;
        this.numBombs = numBombs;
    }
    
	public boolean isMine() {
		return isMine;
	}
	
	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isUserMine() {
		return userMine;
	}

	public void setUserMine(boolean userMine) {
		this.userMine = userMine;
	}

	public boolean isPeek() {
		return peek;
	}

	public void setPeek(boolean peek) {
		this.peek = peek;
	}

	public boolean isSelected() {
		return selected;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	public boolean isZeroAdjacent() {
		return zeroAdjacent;
	}


	public void setZeroAdjacent(boolean zeroAdjacent) {
		this.zeroAdjacent = zeroAdjacent;
	}


	public boolean isOneAdjacent() {
		return oneAdjacent;
	}


	public void setOneAdjacent(boolean oneAdjacent) {
		this.oneAdjacent = oneAdjacent;
	}


	public boolean isTwoAdjacent() {
		return twoAdjacent;
	}


	public void setTwoAdjacent(boolean twoAdjacent) {
		this.twoAdjacent = twoAdjacent;
	}


	public boolean isThreeAdjacent() {
		return threeAdjacent;
	}


	public void setThreeAdjacent(boolean threeAdjacent) {
		this.threeAdjacent = threeAdjacent;
	}


	public boolean isFourAdjacent() {
		return fourAdjacent;
	}


	public void setFourAdjacent(boolean fourAdjacent) {
		this.fourAdjacent = fourAdjacent;
	}


	public int getNumBombs() {
		return numBombs;
	}


	public void setNumBombs(int numBombs) {
		this.numBombs = numBombs;
	}


	public static boolean isGameOver() {
		return gameOver;
	}


	public static void setGameOver() {
		gameOver = true;
	}


	@Override
	public String toString() {
		if (isMine && peek) {
			return "m";
			
		} else if (isMine && userMine) {
			return "m";
		}
		if (isMine) {
			return "-";
		}
		if (zeroAdjacent) {
			return "0";
		}
		if (oneAdjacent) {
			return "1";
		}
		if (twoAdjacent) {
			return "2";
		}
		if (threeAdjacent) {
			return "3";
		}
		if (fourAdjacent) {
			return "4";
		}
		return "-";
	}
    
}
