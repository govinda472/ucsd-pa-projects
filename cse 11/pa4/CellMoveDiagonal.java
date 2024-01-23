import java.util.List;

public class CellMoveDiagonal extends CellMoveUp {
	
	public boolean orientedRight;
	public int diagonalMoves;
	
	public CellMoveDiagonal(int currRow, int currCol, int mass) {
		super(currRow, currCol, mass);
		orientedRight = true;
		diagonalMoves = 0;
	}
	
	public CellMoveDiagonal(CellMoveDiagonal otherSubclass) {
		super(otherSubclass);
		this.orientedRight = otherSubclass.orientedRight;
		this.diagonalMoves = otherSubclass.diagonalMoves;
	}
	
	public String toString() {
		if (orientedRight) {
			return "/";
		}
		return "\\";
	}
	
	public boolean checkApoptosis(List<Cell> neighbors) {
		return neighbors.size() > 3;
	}
	
}
