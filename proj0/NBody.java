public class NBody{
	public static double readRadius(String args){
		In in = new In(args);
		int number_of_bodies = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	public static Body[] readBodies(String args){
		In in = new In(args);
		int number_of_bodies = in.readInt();
		double radius = in.readDouble();
		int n = 0;
		Body[] array_of_bodies = new Body[number_of_bodies];
		while(n < number_of_bodies){
			// array_of_bodies[n].xxPos = in.readDouble();
			// array_of_bodies[n].yyPos = in.readDouble();
			// array_of_bodies[n].xxVel = in.readDouble();
			// array_of_bodies[n].yyVel = in.readDouble();
			// array_of_bodies[n].mass = in.readDouble();
			// array_of_bodies[n].imgFileName = in.readString();
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			array_of_bodies[n] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			n = n + 1;
		}
		return array_of_bodies;
	}
// avoid use !in.isEmpty() in the while function. the file will not end,
// and the remaing will still be read, however, it is not int type. 
	//So error occurs.
	//Then, do not use "." to initiate a variable. A variable should be 
	//constracted by constractor functions using "new" and ().Before 
	//construction, there is no instance variable at all.
}


