/*
	Devontae Reid
	CIS 183
	April 23, 2016
*/
import java.awt.geom.Point2D;

public interface Travelable
{
	double toDistance(Travelable x);
	double toTime(Travelable y);
	Point2D.Double getPoint();
}
