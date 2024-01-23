import java.util.List;

public abstract class Cell {
	public int currRow;
	public int currCol;
	public int mass;
	
	public Cell(int currRow, int currCol, int mass) {
		this.currRow = Math.max(currRow, 0);
		this.currCol = Math.max(currCol, 0);
		this.mass = Math.max(mass, 0);
	}
	
	public Cell(Cell otherCell) {
		if (otherCell == null) {
			throw new NullPointerException();
		} else {
			this.currRow = otherCell.currRow;
			this.currCol = otherCell.currCol;
			this.mass = otherCell.mass;
		}
	}
	
	public void apoptosis() {
		currRow = -1;
		currCol = -1;
		mass = -1;
	}
	
	public int getCurrRow() {
		return currRow;
	}
	
	public int getCurrCol() {
		return currCol;
	}
	
	public int getMass() {
		return mass;
	}
	
	public boolean setCurrRow(int newRow) // optional
	{
		if (newRow >= 0) {
			this.currRow = newRow;
			return true;
		}
		return false;
	}
	
	public boolean setCurrCol(int newCol) // optional
	{
		if (newCol >= 0) {
			this.currCol = currCol;
			return true;
		}
		return false;
	}
	
	public boolean setMass(int newMass) {
		if (newMass >= 0) {
			this.mass = newMass;
			return true;
		}
		return false;
	}
	
	public void updatePosition(int[] newPosition) {
		if (setCurrRow(newPosition[0])) {
			setCurrCol(newPosition[1]);
		}
	}
	
	public abstract boolean checkApoptosis(List<Cell> neighbors);
}
