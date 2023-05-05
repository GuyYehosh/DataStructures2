
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDensity() {
		return this.size/(xHead.getPrevX().getX()- xHead.getX())*(yHead.getPrevY().getY()- yHead.getY());
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean getLargestAxis() {
		int xSize=xHead.getPrevX().getX() - xHead.getX() , ySize=yHead.getPrevY().getY()  - yHead.getY();
		return xSize > ySize;
	}

	@Override
	public Container getMedian(Boolean axis) {
		// TODO Auto-generated method stub
		return null;
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

