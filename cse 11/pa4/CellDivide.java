import java.util.List;

public class CellDivide extends Cell {
	
	public int direction;
	
	public CellDivide(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
		direction = 1;
	}
	
	public CellDivide(CellDivide otherSubclass) {
		super(otherSubclass);
		direction = otherSubclass.direction;
	}
	
	public String toString() {
		return "+";
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return neighbors.size() == 3;
	}
	
}
