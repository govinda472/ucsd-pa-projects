import java.util.List;

public class CellMoveToggle extends CellMoveUp {
	
	public boolean toggled;
	
	public CellMoveToggle(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
		toggled = true;
	}
	
	public CellMoveToggle(CellMoveToggle otherSubclass) {
		super(otherSubclass);
	}
	
	public String toString() {
		if (toggled){
			return "T";
		}
		return "t";
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return neighbors.size() < 2 || neighbors.size() > 5;
	}
	
}
