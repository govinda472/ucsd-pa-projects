import java.util.List;

public class CellMoveToggleChild extends CellMoveToggle {
	
	public static int numAlive=0;
	
	public CellMoveToggleChild(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
		numAlive++;
	}
	
	public CellMoveToggleChild(CellMoveToggleChild otherSubclass) {
		super(otherSubclass);
		numAlive++;
	}
	
	@Override
	public void apoptosis() {
		super.apoptosis();
		numAlive--;
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return super.checkApoptosis(neighbors) && numAlive < 10;
	}
	
}
