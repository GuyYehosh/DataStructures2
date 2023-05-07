
public class DataStructure implements DT {

	private Container xHead;//min for x

	private Container yHead;//min for y

	private int size;//how many points


	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure() {
		this.size=0;
	}

	@Override
	public void addPoint(Point point) {
		if(xHead==null | yHead==null) {//if it's the first point
			xHead = new Container(point, xHead, xHead, xHead, xHead);
			xHead.setNextX(xHead);
			xHead.setNextY(xHead);
			xHead.setPrevX(xHead);
			xHead.setPrevY(xHead);
			yHead = new Container(point, yHead, yHead, yHead, yHead);
			yHead.setNextX(yHead);
			yHead.setNextY(yHead);
			yHead.setPrevX(yHead);
			yHead.setPrevY(yHead);
			size=1;
		}
		else {
			Container newC= new Container(point, null, null, null, null);//the new point
			this.size+=1;

			//handling x order list
			if(point.getX() < xHead.getX()) {//if it's the new min
				newC.setPrevX(xHead.getPrevX());
				xHead.getPrevX().setNextX(newC);
				xHead.setPrevX(newC);
				newC.setNextX(xHead);
				xHead= newC;
			}
			else {
				Container pointer = xHead;
				while(point.getX()>pointer.getNextX().getX() & !pointer.getNextX().equals(xHead))//move to the right place for the new point
					pointer=pointer.getNextX();
				newC.setPrevX(pointer);
				newC.setNextX(pointer.getNextX());
				pointer.getNextX().setPrevX(newC);
				pointer.setNextX(newC);
			}

			//handling y order list
			if(point.getY() < yHead.getY()) {//checks if it's the new min
				newC.setPrevY(yHead.getPrevY());
				yHead.getPrevY().setNextY(newC);
				yHead.setPrevY(newC);
				newC.setNextY(yHead);
				yHead= newC;
			} else {
				Container pointer = yHead;
				while(point.getY()>pointer.getNextY().getY() & !pointer.getNextY().equals(yHead))//move to the right place for the new point
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
			return getPointsInRangeXAxis(min , max);//call the function for x
		else
			return getPointsInRangeYAxis(min , max);//call the function for y
	}

	private Point[] getPointsInRangeXAxis(int min, int max)
	{
		Point[] arr = new Point[max-min+1];//temp
		Container pointer = xHead;
		while(pointer.getX()<min)//if it's out of range skip it
			pointer=pointer.getNextX();
		int i = 0;
		while(pointer.getX()<=max)//if it is in the range add it
		{
			arr[i]=pointer.getData();
			pointer = pointer.getNextX();
			i+=1;
		}
		Point[] ans = new Point[i];
		i--;
		while(i>=0)
		{
			ans[i] = arr[i];
			i--;
		}

		return ans;
	}

	private Point[] getPointsInRangeYAxis(int min, int max)
	{
		Point[] arr = new Point[max-min+1];//ans
		Container pointer = yHead;
		while(pointer.getY()<min)//if it's out of range skip it
			pointer=pointer.getNextY();
		int i = 0;
		while(pointer.getY()<=max)//if it is in the range add it
		{
			arr[i]=pointer.getData();
			pointer = pointer.getNextY();
			i+=1;
		}
		Point[] ans = new Point[i];
		i--;
		while(i>=0)
		{
			ans[i] = arr[i];
			i--;
		}

		return ans;
	}
	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {
		return getPointsInRangeRegAxis(min , max , !axis);//use the regaxis function for the opposite axis
	}

	@Override
	public double getDensity() {
		return (double)this.size/(xHead.getPrevX().getX()- xHead.getX())*(yHead.getPrevY().getY()- yHead.getY());//amount of points divided by the range of x multiply by the range of y
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		if(axis)
		{
			while (xHead.getX()< min)//if the current min is too small make the next one min
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
			while (pointer.getX() > max)//if the biggest point is too big get it out
			{
				pointer.getNextX().setPrevX(pointer.getPrevX());
				pointer.getPrevX().setNextX(pointer.getNextX());
				pointer.getNextY().setPrevY(pointer.getPrevY());
				pointer.getPrevY().setNextY(pointer.getNextY());
				pointer = pointer.getPrevX();
			}
		}
		else {
			while (yHead.getY()< min)//if the current min is too small make the next one min
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
			while (pointer.getX() > max)//if the biggest point is too big get it out
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
		int xSize=xHead.getPrevX().getX() - xHead.getX() , ySize=yHead.getPrevY().getY()  - yHead.getY();//the difference between x max and min and y max and min
		return xSize > ySize;
	}

	@Override
	public Container getMedian(Boolean axis) {
		Container pointer = xHead;//the ans
		int toMove = size / 2;// calculate where is the middle
		if(size%2 == 0)//check if there are even or odd number of points
			toMove--;
		if(axis)
		{
			for(int i = toMove; i > 0; i--)//move to the right point
				pointer = pointer.getNextX();
		}
		else {
			pointer = yHead;//the ans
			for(int i = toMove; i > 0; i--)//move to the right point
				pointer = pointer.getNextY();
		}
		return pointer;
	}

	@Override
	public Point[] nearestPairInStrip(Container container, double width,
									  Boolean axis) {
		Point[] p1;
		if(axis)
			p1 = nearestPairInStrip(container, width, axis, container.getData().getX());
		else
			p1 = nearestPairInStrip(container, width, axis, container.getData().getY());
		if(p1.length == 2)
			return p1;
		return new Point[0];
	}
	private double dis(Point pa, Point pb){
		return Math.sqrt(Math.pow((pa.getX() - pb.getX()),2) + Math.pow((pa.getY() - pb.getY()),2));
	}
	private Point[] nearestPairInStrip(Container container, double width, boolean axis, double p){
		int dis = 0;
		Container temp = container;
		if(axis) {
			while (temp != null && temp.getX() >= p - width / 2)
				temp = temp.getPrevX();
			while (temp != null && temp.getX() <= p + width / 2)
				temp = temp.getNextX();
			while (temp != null && temp.getX() >= p - width / 2) {
				temp = temp.getPrevX();
				dis++;
			}
		}
		else {
			while (temp != null && temp.getY() >= p - width / 2)
				temp = temp.getPrevY();
			while (temp != null && temp.getY() <= p + width / 2)
				temp = temp.getNextY();
			while (temp != null && temp.getY() >= p - width / 2) {
				temp = temp.getPrevY();
				dis++;
			}
		}
		Point[] points = new Point[dis];
		for(int i = 0; i < dis; i++){
			points[i] = temp.getData();
			if(axis)
				temp = temp.getNextX();
			else
				temp = temp.getNextY();
		}
		if(points.length <= 2) return points;
		else{
			Point[] p1 = nearestPairInStrip(container, width/2, axis, p + width/2);
			Point[] p2 = nearestPairInStrip(container, width/2, axis, p - width/2);
			return nearest2(p1, p2);
		}
	}
	private Point[] nearest2(Point[] pa, Point[] pb){
		// assume that length <= 2
		double dis1 = 1000000000, dis2 = 1000000000;
		if(pa.length == 2) dis1 = dis(pa[0], pa[1]);
		if(pb.length == 2) dis2 = dis(pb[0], pb[1]);
		double mind = dis2;
		Point[] min = new Point[2];
		if(dis1 < dis2 & pa.length == 2){
			mind = dis1;
			min[0] = pa[0];
			min[1] = pa[1];
		}
		else if(pb.length == 2){
			mind = dis2;
			min[0] = pb[0];
			min[1] = pb[1];
		}
		else{
			if(pa.length == 1 & pb.length == 1 && pa[0].equals(pb[0]))
				return pa;
			if(pa.length == 0)
				return pb;
			else
				return pa;
		}
		for (Point point1 : pa) {
			for (Point point2 : pb) {
				double dis = dis(point1, point2);
				if(mind > dis & dis != 0) {
					mind = Math.min(mind, dis);
					min[0] = point1;
					min[1] = point2;
				}
			}
		}
		return min;
	}
	@Override
	public Point[] nearestPair() {
		// TODO Auto-generated method stub
		int dis = 0;
		Container temp = this.xHead;
		while (temp != null) {
			temp = temp.getNextX();
			dis++;
		}
		if (dis < 2)
			return new Point[0];
		else if (dis == 2) {
			Point[] p = new Point[2];
			p[0] = xHead.getData();
			p[1] = xHead.getPrevX().getData();
			return p;
		} else {
			boolean axis = getLargestAxis();
			Container mid = getMedian(axis);
			Point[] big, small;
			if (axis) {
				double width = xHead.getPrevX().getData().getX() - mid.getData().getX();
				big = nearestPairInStrip(mid, width, axis, mid.getData().getX() + width / 2);
				width = mid.getData().getX() - xHead.getData().getX();
				small = nearestPairInStrip(mid, width, axis, mid.getData().getX() - width / 2);
			} else {
				double width = yHead.getPrevY().getData().getY() - mid.getData().getY();
				big = nearestPairInStrip(mid, width, axis, mid.getData().getY() + width / 2);
				width = mid.getData().getY() - yHead.getData().getY();
				small = nearestPairInStrip(mid, width, axis, mid.getData().getY() - width / 2);
			}
			double dist;
			Point[] closeA = new Point[2];
			if (dis(big[0], big[1]) > dis(small[0], small[1])) {
				dist = dis(small[0], small[1]);
				closeA[0] = small[0];
				closeA[1] = small[1];
			} else {
				dist = dis(big[0], big[1]);
				closeA[0] = big[0];
				closeA[1] = big[1];
			}
			Point[] closeB;
			if(axis)
				closeB = nearestPairInStrip(mid, 2 * dist, axis, mid.getX());
			else
				closeB = nearestPairInStrip(mid, 2 * dist, axis, mid.getY());
			if (dis(closeB[0], closeB[1]) < dis(closeB[0], closeB[1]))
				return closeA;
			else
				return closeB;
		}
	}



	//TODO: add members, methods, etc.

}

