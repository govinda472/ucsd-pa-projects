public class PetriDish {
	
	public Cell[][] dish;
	
	public PetriDish(String[][] board) {
		
		dish = new Cell[board.length][board[0].length];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("null")) {
					dish[i][j] = null;
				} else {
					String[] tokens = board[i][j].split(" ");
					int mass = Integer.parseInt(tokens[1]);
					
					switch (tokens[0]) {
						case "CellStationary" -> dish[i][j] = new CellStationary(i, j, mass);
						case "CellDivide" -> dish[i][j] = new CellDivide(i, j, mass);
						case "CellMoveUp" -> dish[i][j] = new CellMoveUp(i, j, mass);
						case "CellMoveToggle" -> dish[i][j] = new CellMoveToggle(i, j, mass);
						case "CellMoveDiagonal" -> dish[i][j] = new CellMoveDiagonal(i, j, mass);
						case "CellMoveToggleChild" -> dish[i][j] = new CellMoveToggleChild(i, j, mass);
						default -> dish[i][j] = null;
					}
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		 //typically 2*board.length+3 would display the board nicely
		
		String HORIZONTAL_BARS = "-";
		sb.append(String.valueOf(HORIZONTAL_BARS).repeat(Math.max(0, 2 * dish[0].length + 1)));
		String NEW_LINE = "\n";
		sb.append(NEW_LINE);
		
		for (int i = 0; i < dish.length; i++) {
			String VERTICAL_BAR = "|";
			sb.append(VERTICAL_BAR);
			for (int j = 0; j < dish[0].length; j++) {
				String EMPTY_STRING = " ";
				sb.append(dish[i][j] == null ? EMPTY_STRING : dish[i][j].toString());
				sb.append(VERTICAL_BAR);
			}
			sb.append(NEW_LINE);
			
			sb.append(String.valueOf(HORIZONTAL_BARS).repeat(Math.max(0, 2 * dish[0].length + 1)));
			sb.append(NEW_LINE);
		}
		return sb.toString();
	}
}
