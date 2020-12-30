
public class Board {
	Spot[][] squares;
	
	public Board() {
		this.reset();
	}
	
	public Spot getSquare(int x, int y) throws Exception {
		if(x < 0 || x > 7 || y < 0 || y > 7) 
			throw new Exception(String.format("Index out of bounds, x = %d, y = %d", x, y));
		
		return squares[x][y];
	}

	private void reset() {
		for(int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if(y == 0) {
					switch(x) {
					case 0:
					case 7:
						squares[x][y] = new Spot(x, y, new Rook(false));
						break;
					case 1:
					case 6:
						squares[x][y] = new Spot(x, y, new Knight(false));
						break;
					case 2:
					case 5:
						squares[x][y] = new Spot(x, y, new Bishop(false));
						break;
					case 3:
						squares[x][y] = new Spot(x, y, new Queen(false));
						break;
					case 4:
						squares[x][y] = new Spot(x, y, new King(false));
						break;	
					}
				}
				else if (y == 1)
					squares[x][y] = new Spot(x, y, new Pawn(false));
				
				else if (y == 6)
					squares[x][y] = new Spot(x, y, new Pawn(true));
				
				else if(y == 7) {
					switch(x) {
					case 0:
					case 7:
						squares[x][y] = new Spot(x, y, new Rook(true));
						break;
					case 1:
					case 6:
						squares[x][y] = new Spot(x, y, new Knight(true));
						break;
					case 2:
					case 5:
						squares[x][y] = new Spot(x, y, new Bishop(true));
						break;
					case 3:
						squares[x][y] = new Spot(x, y, new Queen(true));
						break;
					case 4:
						squares[x][y] = new Spot(x, y, new King(true));
						break;	
					}
				}
			}
		}
		
	}

	public boolean checkEnPessant(Spot start, Spot end){
		return false;
	}

	public boolean pieceInBetween(Spot start, Spot end) {
		int x = start.getX();
		int y = start.getY();
		Piece checkpiece = null;

		while(x < end.getX() && y < end.getY()){
			checkpiece = squares[x][y].getPiece();
			if (checkpiece != null){
				return true;
			}
			if (x != end.getX()){
				x++;
			}
			if (y != end.getY()) {
				y++;
			}
		}

		return false;
	}
}
