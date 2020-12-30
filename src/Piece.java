public abstract class Piece {
	private boolean taken = false;
	private boolean	white = false;
	
	public Piece(boolean white) {
		this.setWhite(white);
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	public boolean isWhite() {
		return white;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	public abstract boolean canMove(Board board, Spot start, Spot end);
}
