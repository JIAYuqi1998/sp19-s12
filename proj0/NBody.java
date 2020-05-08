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
	public static void main(String[] args) {
		int waitTimeMilliseconds = 10;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] planets = readBodies(filename);
		double radius_of_whole = readRadius(filename);
		StdDraw.setScale(-radius_of_whole,radius_of_whole);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Body planet:planets){
			planet.draw();
		}
		double t = 0.0;
		int number_of_bodies = planets.length;
		double[] xForces = new double[number_of_bodies];
		double[] yForces = new double[number_of_bodies];
		StdDraw.enableDoubleBuffering();
		while(t < T){
			for(int i = 0; i < number_of_bodies; i = i +1){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(int i = 0; i < number_of_bodies; i = i +1){
				planets[i].update(dt,xForces[i],yForces[i]);
				planets[i].draw();
				StdDraw.show();
				StdDraw.pause(waitTimeMilliseconds);
			}
			t = t + dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius_of_whole);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}
}


