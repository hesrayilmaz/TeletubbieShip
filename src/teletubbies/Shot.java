package teletubbies;


public class Shot {
	//x and y directions of the shot
	private int x,y;
	
	public Shot(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}