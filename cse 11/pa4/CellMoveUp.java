import java.util.List;

public class CellMoveUp extends Cell {
	public CellMoveUp(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
	}
	
	public CellMoveUp(CellMoveUp otherSubclass) {
		super(otherSubclass);
	}
	
	public String toString() {
		return "^";
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return neighbors.size() != 4;
	}
	
}