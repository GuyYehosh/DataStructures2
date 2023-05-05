
//Don't change the class name
public class Container{
	private Point data;//Don't delete or change this field;
	private Container nextX;

	private Container prevX;

	private Container nextY;

	private Container prevY;

	public Container(Point p, Container nextX , Container prevX , Container nextY, Container prevY)
	{
		this.data=p;
		this.nextX=nextX;
		this.prevX=prevX;
		this.nextY=nextY;
		this.prevY=prevY;
	}

	public Container getNextX() {
		return nextX;
	}

	public Container getNextY() {
		return nextY;
	}

	public Container getPrevX() {
		return prevX;
	}

	public Container getPrevY() {
		return prevY;
	}

	public void setNextX(Container nextX) {
		this.nextX = nextX;
	}

	public void setNextY(Container nextY) {
		this.nextY = nextY;
	}

	public void setPrevX(Container prevX) {
		this.prevX = prevX;
	}

	public void setPrevY(Container prevY) {
		this.prevY = prevY;
	}

	public void setData(Point data) {
		this.data = data;
	}

	public int getX()
	{
		return data.getX();
	}

	public int getY()
	{
		return data.getY();
	}
	//Don't delete or change this function
	public Point getData()
	{
		return data;
	}
}
