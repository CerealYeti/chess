public class King extends Piece {	
	private boolean castlingDone = false;

	public King(boolean white) {
		super(white);
	}

	public boolean isCastlingDone() {
		return castlingDone;
	}

	public void setCastlingDone(boolean castlingDone) {
		if (!this.castlingDone)
			this.castlingDone = castlingDone;
		else
			System.out.println("Can no longer castle");
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		if(end.getPiece().isWhite() == this.isWhite())
			return false;
		
		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		
		if (x == 1 && y == 1 || x + y == 1) {
			//check to make sure we don't move into check
			return true;
		}
		
		return this.isValidCastling(board, start, end);
	}

	private boolean isValidCastling(Board board, Spot start, Spot end) {
		if(this.isCastlingDone())
			return false;
		//TODO finish logic for method
		return false;
	}
	
	public boolean isCastlingMove(Spot start, Spot end) {
		int x = Math.abs(start.getX() - end.getX());
		return start.getY() - end.getY() == 0 && x == 2;
	}

}
