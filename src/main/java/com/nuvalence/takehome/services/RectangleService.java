package com.nuvalence.takehome.services;

import com.nuvalence.takehome.domain.Adjacency;
import com.nuvalence.takehome.domain.Rectangle;
import com.nuvalence.takehome.domain.Shape;
import com.nuvalence.takehome.exceptions.InvalidArgumentsException;
import com.nuvalence.takehome.exceptions.UnprocessableArgumentsException;
import com.nuvalence.takehome.models.response.ShapesRelationResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RectangleService implements IShapeService {

    private final Logger logger = LogManager.getLogger(RectangleService.this.getClass().getCanonicalName());

    public ShapesRelationResponse getRelation(Shape s1, Shape s2) {
        if (s1 == null || s2 == null)
            throw new UnprocessableArgumentsException("Either of the rectangles cannot be null for this operation.");

        if (s1.getClass() != s2.getClass())
            throw new UnprocessableArgumentsException("Cannot perform this operation on different shapes.");

        if (logger.isInfoEnabled())
            logger.info("Getting relation between {} and {}", s1.toString(), s2.toString());

        ShapesRelationResponse response = new ShapesRelationResponse();
        if (intersects(s1, s2)) {
            response.setIntersects(true);
            response.setIntersectionPoints(intersection(s1, s2));
        }
        if (contains(s1, s2))
            response.setContains(true);
        response.setAdjacency(adjacency(s1, s2));
        return response;
    }

    private boolean adjacent(Shape s1, Shape s2) {
        if (!(s1 instanceof Rectangle) || !(s2 instanceof Rectangle))
            throw new InvalidArgumentsException("Adjacent operation does not work for different shapes.");

        Rectangle r1 = (Rectangle) s1;
        Rectangle r2 = (Rectangle) s2;

        if (logger.isInfoEnabled())
            logger.info("Checking if {} is adjacent to {}", r1.toString(), r2.toString());

        if (r1.getWidth() <= 0 || r1.getHeight() <= 0 || r2.getWidth() <= 0 || r2.getHeight() <= 0)
            return false;

        int rightXForR1 = r1.getPoint().x + r1.getWidth();
        int bottomYForR1 = r1.getPoint().y - r1.getHeight();
        int rightXForR2 = r2.getPoint().x + r2.getWidth();
        int bottomYForR2 = r2.getPoint().y - r2.getHeight();

        //if Rectangle r1's right most x and Rectangle r2's left most x are same (RIGHT SIDE ADJ)OR
        //if Rectangle r1's left most x and Rectangle r2's right most x are same (LEFT SIDE ADJ) OR
        //if Rectangle r1's top most y and Rectangle r2's bottom most y are same (TOP SIDE ADJ) OR
        //if Rectangle r1's bottom most y and Rectangle r2's top most y are same (BOTTOM SIDE ADJ) OR
        return Math.abs(rightXForR1 - r2.getPoint().x) == 0 || Math.abs(r1.getPoint().x - rightXForR2) == 0
                || Math.abs(bottomYForR1 - r2.getPoint().y) == 0 || Math.abs(r1.getPoint().y - bottomYForR2) == 0;
    }

    @Override
    public boolean intersects(Shape s1, Shape s2) {
        if (!(s1 instanceof Rectangle) || !(s2 instanceof Rectangle))
            throw new InvalidArgumentsException("Intersects operation does not work for different shapes.");

        Rectangle r1 = (Rectangle) s1;
        Rectangle r2 = (Rectangle) s2;

        if (logger.isInfoEnabled())
            logger.info("Checking if {} intersects {}", r1.toString(), r2.toString());

        if (r1.getWidth() <= 0 || r1.getHeight() <= 0 || r2.getWidth() <= 0 || r2.getHeight() <= 0)
            return false;

        int rightXForR2 = r2.getPoint().x + r2.getWidth();
        int bottomYForR2 = r2.getPoint().y - r2.getHeight();
        int rightXForR1 = r1.getPoint().x + r1.getWidth();
        int bottomYForR1 = r1.getPoint().y - r1.getHeight();

        //Rectangle r1's left most X is to the right of right most X of Rectangle r2's  OR
        //Rectangle r1's top most Y is to the down of bottom most Y of Rectangle r2's  OR
        //Rectangle r1's right most X is to the left of left most X of Rectangle r2's  OR
        //Rectangle r1's bottom most Y is to the top of top most Y of Rectangle r2's 
        if (contains(r1, r2) || r1.getPoint().x >= rightXForR2 || bottomYForR2 >= r1.getPoint().y || r2.getPoint().x >= rightXForR1
                || bottomYForR1 >= r2.getPoint().y)
            return false;

        return true;
    }

    @Override
    public List<Point> intersection(Shape s1, Shape s2) {
        if (!(s1 instanceof Rectangle) || !(s2 instanceof Rectangle))
            throw new InvalidArgumentsException("Intersects operation does not work for different shapes.");

        Rectangle r1 = (Rectangle) s1;
        Rectangle r2 = (Rectangle) s2;

        if (logger.isInfoEnabled())
            logger.info("Getting intersection points for {}, {}", r1.toString(), r2.toString());

        if (r1.getWidth() <= 0 || r1.getHeight() <= 0 || r2.getWidth() <= 0 || r2.getHeight() <= 0)
            return new ArrayList<>();

        List<Point> result = new ArrayList<>();

        //rectangle boundaries
        int rightXForR2 = r2.getPoint().x + r2.getWidth();
        int bottomYForR2 = r2.getPoint().y - r2.getHeight();
        int rightXForR1 = r1.getPoint().x + r1.getWidth();
        int bottomYForR1 = r1.getPoint().y - r1.getHeight();

        //intersection values for all cases.
        int leftXIntersection = Math.max(r1.getPoint().x, r2.getPoint().x);
        int rightXIntersection = Math.min(r1.getPoint().x + r1.getWidth(), r2.getPoint().x + r2.getWidth());
        int topYIntersection = Math.min(r1.getPoint().y, r2.getPoint().y);
        int bottomYIntersection = Math.max(r1.getPoint().y - r1.getHeight(), r2.getPoint().y - r2.getHeight());

        //horizontal line cases
        //LEFT boundary
        //  Rectangle r2's  rectangle top line intersection
        if (r1.getPoint().x > r2.getPoint().x && rightXForR2 > r1.getPoint().x
                && r1.getPoint().y > r2.getPoint().y && r2.getPoint().y > bottomYForR1) {
            result.add(new Point(leftXIntersection, topYIntersection));
        }
        //  Rectangle r2's  rectangle bottom line intersection
        if (r1.getPoint().x > r2.getPoint().x && rightXForR2 > r1.getPoint().x
                && r1.getPoint().y > bottomYForR2 && bottomYForR2 > bottomYForR1) {
            result.add(new Point(leftXIntersection, bottomYIntersection));
        }
        //RIGHT boundary
        //  Rectangle r2's  rectangle top line intersection
        if (rightXForR2 > rightXForR1 && rightXForR1 > r2.getPoint().x
                && r1.getPoint().y > r2.getPoint().y && r2.getPoint().y > bottomYForR1) {
            result.add(new Point(rightXIntersection, topYIntersection));
        }
        //  Rectangle r2's  rectangle bottom line intersection
        if (rightXForR2 > rightXForR1 && rightXForR1 > r2.getPoint().x
                && r1.getPoint().y > bottomYForR2 && bottomYForR2 > bottomYForR1) {
            result.add(new Point(rightXIntersection, bottomYIntersection));
        }

        //Vertical line cases
        //TOP boundary
        //  left line intersection
        if (r2.getPoint().x > r1.getPoint().x && rightXForR1 > r2.getPoint().x
                && r2.getPoint().y > r1.getPoint().y && r1.getPoint().y > bottomYForR2) {
            result.add(new Point(leftXIntersection, topYIntersection));
        }
        //  right line intersection
        if (rightXForR2 > r1.getPoint().x && rightXForR1 > rightXForR2
                && r2.getPoint().y > r1.getPoint().y && r1.getPoint().y > bottomYForR2) {
            result.add(new Point(rightXIntersection, topYIntersection));
        }
        //Bottom boundary
        //  left line intersection
        if (r2.getPoint().x > r1.getPoint().x && rightXForR1 > r2.getPoint().x
                && r2.getPoint().y > bottomYForR1 && bottomYForR1 > bottomYForR2) {
            result.add(new Point(leftXIntersection, bottomYIntersection));
        }
        //  right line intersection
        if (rightXForR2 > r1.getPoint().x && rightXForR1 > rightXForR2
                && r2.getPoint().y > bottomYForR1 && bottomYForR1 > bottomYForR2) {
            result.add(new Point(rightXIntersection, bottomYIntersection));
        }

        return result;
    }

    @Override
    public boolean contains(Shape s1, Shape s2) {
        if (!(s1 instanceof Rectangle) || !(s2 instanceof Rectangle))
            throw new InvalidArgumentsException("Contains operation does not work for different shapes.");

        Rectangle r1 = (Rectangle) s1;
        Rectangle r2 = (Rectangle) s2;

        if (logger.isInfoEnabled())
            logger.info("Checking if {} contains {}", r1.toString(), r2.toString());

        if (r1.getWidth() <= 0 || r1.getHeight() <= 0 || r2.getWidth() <= 0 || r2.getHeight() <= 0)
            return false;

        int rightXForR1 = r1.getPoint().x + r1.getWidth();
        int rightXForR2 = r2.getPoint().x + r2.getWidth();
        int bottomYForR2 = r2.getPoint().y - r2.getHeight();
        int bottomYForR1 = r1.getPoint().y - r1.getHeight();

        //Rectangle r1's left most X is to the left of left most X of Rectangle r2's  OR
        //Rectangle r1's top most Y is to the top of top most Y of Rectangle r2's  OR
        //Rectangle r1's right most X is to the right of right most X of Rectangle r2's  OR
        //Rectangle r1's bottom most Y is to the bottom of bottom most Y of Rectangle r2's 
        return r1.getPoint().x <= r2.getPoint().x && r1.getPoint().y >= r2.getPoint().y
                && rightXForR1 >= rightXForR2 && bottomYForR1 <= bottomYForR2;
    }

    @Override
    public Adjacency adjacency(Shape s1, Shape s2) {
        if (!(s1 instanceof Rectangle) || !(s2 instanceof Rectangle))
            throw new InvalidArgumentsException("Contains operation does not work for different shapes.");

        Rectangle r1 = (Rectangle) s1;
        Rectangle r2 = (Rectangle) s2;

        if (adjacent(r1, r2)) {
            if (logger.isInfoEnabled())
                logger.info("{} is adjacent to {}. Calculating the adjacency type.", r1.toString(), r2.toString());

            int rightXForR1 = r1.getPoint().x + r1.getWidth();
            int bottomYForR1 = r1.getPoint().y - r1.getHeight();
            int rightXForR2 = r2.getPoint().x + r2.getWidth();
            int bottomYForR2 = r2.getPoint().y - r2.getHeight();

            //Adjacent to the vertical lines of rectangle on left Or right
            if (Math.abs(rightXForR1 - r2.getPoint().x) == 0 || Math.abs(r1.getPoint().x - rightXForR2) == 0) {
                logger.info("Rectangles are adjacent either on the right side or left side of {}", r1.toString());
                if (r1.getPoint().y == r2.getPoint().y && r1.getHeight() == r2.getHeight())
                    return Adjacency.PROPER;
                if (r1.getPoint().y >= r2.getPoint().y && bottomYForR2 >= bottomYForR1)
                    return Adjacency.SUB_LINE;
                return Adjacency.PARTIAL;
            }

            if (Math.abs(bottomYForR1 - r2.getPoint().y) == 0 || Math.abs(r1.getPoint().y - bottomYForR2) == 0) {
                logger.info("Rectangles are adjacent either on the top side or bottom side of {}", r1.toString());
                if (r1.getPoint().x == r2.getPoint().x && r1.getWidth() == r2.getWidth())
                    return Adjacency.PROPER;
                if (r1.getPoint().x <= r2.getPoint().x && rightXForR1 >= rightXForR2)
                    return Adjacency.SUB_LINE;
                return Adjacency.PARTIAL;
            }
        }

        return Adjacency.NOT_ADJACENT;
    }
}
