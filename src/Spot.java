public class Spot {
	private Piece piece;
	private int x;
	private int y;
	
	public Spot(int x, int y, Piece piece) {
		this.setPiece(piece);
		try{
			this.setX(x);
			this.setY(y);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) throws Exception {
		if (0 < x || x > 9)
			this.x = x;
		else
			throw new Exception(String.format("Location x = %d is out of range, must be between 1 and 8 inclusive", x));
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) throws Exception {
		if (0 < y || y > 9)
			this.y = y;
		else
			throw new Exception(String.format("Location y = %d is out of range, must be between 1 and 8 inclusive", y));
	}
}
