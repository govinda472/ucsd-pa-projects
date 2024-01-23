import java.util.List;

public class CellStationary extends Cell {
	public CellStationary(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
	}
	
	public CellStationary(CellStationary otherSubclass) {
		super(otherSubclass);
	}
	
	public String toString() {
		return ".";
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return neighbors.size() <= 7 && neighbors.size() >= 3;
	}
	
}
