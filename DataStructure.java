
public class DataStructure implements DT {

	private Container xHead;

	private Container yHead;

	private int size;


	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure() {
		this.size=0;
	}

	@Override
	public void addPoint(Point point) {
		if(xHead==null | yHead==null) {
			xHead = new Container(point, xHead, xHead, xHead, xHead);
			yHead = new Container(point, yHead, yHead, yHead, yHead);
			size=1;
		} else {
			Container newC= new Container(point, null, null, null, null);
			this.size+=1;

			//handling x order list
			if(point.getX() < xHead.getX()) {
				newC.setPrevX(xHead.getPrevX());
				xHead.getPrevX().setNextX(newC);
				xHead.setPrevX(newC);
				newC.setNextX(xHead);
				xHead= newC;
			} else {
				Container pointer = xHead;
				while(point.getX()>pointer.getNextX().getX() & !pointer.getNextX().equals(xHead))
					pointer=pointer.getNextX();
				newC.setPrevX(pointer);
				newC.setNextX(pointer.getNextX());
				pointer.getNextX().setPrevX(newC);
				pointer.setNextX(newC);
			}

			//handling y order list
			if(point.getY() < yHead.getY()) {
				newC.setPrevY(yHead.getPrevY());
				yHead.getPrevY().setNextY(newC);
				yHead.setPrevY(newC);
				newC.setNextY(yHead);
				yHead= newC;
			} else {
				Container pointer = yHead;
				while(point.getY()>pointer.getNextY().getY() & !pointer.getNextY().equals(yHead))
					pointer=pointer.getNextY();
				newC.setPrevY(pointer);
				newC.setNextY(pointer.getNextY());
				pointer.getNextY().setPrevY(newC);
				pointer.setNextY(newC);
			}
		}
	}

	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {
		if(axis)
			return getPointsInRangeXAxis(min , max);
		else
			return getPointsInRangeYAxis(min , max);
	}

	private Point[] getPointsInRangeXAxis(int min, int max)
	{
		Point[] arr = new Point[max-min+1];
		Container pointer = xHead;
		while(pointer.getX()<min)
			pointer=pointer.getNextX();
		int i = 0;
		while(pointer.getX()<=max)
		{
			arr[i]=pointer.getData();
			i+=1;
		}

		return arr;
	}

	private Point[] getPointsInRangeYAxis(int min, int max)
	{
		Point[] arr = new Point[max-min+1];
		Container pointer = yHead;
		while(pointer.getY()<min)
			pointer=pointer.getNextY();
		int i = 0;
		while(pointer.getY()<=max)
		{
			arr[i]=pointer.getData();
			i+=1;
		}

		return arr;
	}
	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		return getPointsInRangeRegAxis(min , max , !axis);
	}

	@Override
	public double getDensity() {
		return this.size/(xHead.getPrevX().getX()- xHead.getX())*(yHead.getPrevY().getY()- yHead.getY());
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		if(axis)
		{
			while (xHead.getX()< min)
			{
				xHead.getNextX().setPrevX(xHead.getPrevX());
				xHead.getPrevX().setNextX(xHead.getNextX());
				xHead.getNextY().setPrevY(xHead.getPrevY());
				xHead.getPrevY().setNextY(xHead.getNextY());
				if(xHead.equals(yHead))
					yHead = yHead.getNextY();
				xHead = xHead.getNextX();
				size--;
			}
			Container pointer = xHead.getPrevX();
			while (pointer.getX() > max)
			{
				pointer.getNextX().setPrevX(pointer.getPrevX());
				pointer.getPrevX().setNextX(pointer.getNextX());
				pointer.getNextY().setPrevY(pointer.getPrevY());
				pointer.getPrevY().setNextY(pointer.getNextY());
				pointer = pointer.getPrevX();
			}
		}
		else {
			while (yHead.getY()< min)
			{
				yHead.getNextY().setPrevY(yHead.getPrevY());
				yHead.getPrevY().setNextY(yHead.getNextY());
				yHead.getNextX().setPrevX(yHead.getPrevX());
				yHead.getPrevX().setNextX(yHead.getNextX());
				if(yHead.equals(xHead))
					xHead = xHead.getNextY();
				yHead = yHead.getNextX();
				size--;
			}
			Container pointer = xHead.getPrevX();
			while (pointer.getX() > max)
			{
				pointer.getNextY().setPrevY(pointer.getPrevY());
				pointer.getPrevY().setNextY(pointer.getNextY());
				pointer.getNextX().setPrevX(pointer.getPrevX());
				pointer.getPrevX().setNextX(pointer.getNextX());
				pointer = pointer.getPrevY();
			}
		}
	}

	@Override
	public Boolean getLargestAxis() {
		int xSize=xHead.getPrevX().getX() - xHead.getX() , ySize=yHead.getPrevY().getY()  - yHead.getY();
		return xSize > ySize;
	}

	@Override
	public Container getMedian(Boolean axis) {
		Container pointer = xHead;
		int toMove = size / 2;
		if(size%2 == 0)
			toMove--;
		if(axis)
		{
			for(int i = toMove; i > 0; i--)
				pointer = pointer.getNextX();
		}
		else {
			pointer = yHead;
			for(int i = toMove; i > 0; i--)
				pointer = pointer.getNextY();
		}
		return pointer;
	}

	@Override
	public Point[] nearestPairInStrip(Container container, double width,
									  Boolean axis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] nearestPair() {
		// TODO Auto-generated method stub
		return null;
	}


	//TODO: add members, methods, etc.

}

