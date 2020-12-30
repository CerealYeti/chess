
public class Pawn extends Piece {

	public Pawn(boolean white) {
		super(white);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());

		// pawns cannot move sideways unless en pessant
		if (x == 1 && y == 1){
			return board.checkEnPessant(start, end);
		}


		return false;
	}

}
