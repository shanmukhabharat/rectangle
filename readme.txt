API Documentation markup : rectangle/swagger-markup-doc/index.html
SwaggerUI link after application starts: http://localhost:8080/swagger-ui.html#/rectangle-controller/getRectanglesRelationUsingPOST
Test coverage report : rectangle/test-report/index.html

Java :
	Currently running on Java 8.


Endpoints:

	/rectangle/relation : 

		Gives us the relation between 2 rectangles.

		Considerations:	
		1. Assuming the rectangles to be in the first quadrant of the XY plane.
		2. Considering 2 rectangles with same dimensions to be in 'contains' relation.

		Response properties:
		1. contains : True if the first rectangle (param) contains the second rectangle (param), false otherwise
		2. intersects : True if they intersect, false otherwise. 
						If the first rectangle R1 is completely outside the boundaries of R2, OR if R1 contains R2 return false, true otherwise.
		3. adjacency : Returns any of {PROPER, SUB_LINE, PARTIAL, NOT_ADJACENT}
				NOT_ADJACENT : if the rectangles are not adjacent.
				PROPER : if both the rectangles have a common edge and if the height of both rectangles are the same.
				SUB_LINE :  if both the rectangles have a common edge and if R2's edge lies between the minimum and maximum of the parallel R1's edge.
				PARTIAL : if the rectangles are adjacent and they are not the above 2 cases.
		4. intersectionpoints : Will not be in the response if intersects is false, Otherwise list of intersection points.
