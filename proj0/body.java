public class Body{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public static double G = 6.67e-11;
	public Body(double xP, double yP, double xV, double yV,
		double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){		
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	public double calcDistance(Body body2){
		double dx = body2.xxPos - xxPos;
		double dy = body2.yyPos - yyPos;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}
	public double calcForceExertedBy(Body body2){
		double distance = this.calcDistance(body2);
		double force = this.mass * body2.mass * G/distance/distance;
		return force;
	}
	public double calcForceExertedByX(Body body2){
		double dx = body2.xxPos - this.xxPos;
		double distance = this.calcDistance(body2);
		double totalforce = this.calcForceExertedBy(body2);
		double xforce = totalforce*dx/distance;
		return xforce;
	}
	public double calcForceExertedByY(Body body2){
		double dy = body2.yyPos - this.yyPos;
		double distance = this.calcDistance(body2);
		double totalforce = this.calcForceExertedBy(body2);
		double yforce = totalforce*dy/distance;
		return yforce;
	}
	public double calcNetForceExertedByX(Body[] bodyarray){
		double netxforce = 0;
		for(Body i:bodyarray){
			if (i.equals(this)){
				continue;
			}
			netxforce += this.calcForceExertedByX(i);
		}
		return netxforce;
	}
	public double calcNetForceExertedByY(Body[] bodyarray){
		double netyforce = 0;
		for(Body i:bodyarray){
			if (i.equals(this)){
				continue;
			}
			netyforce += this.calcForceExertedByY(i);
		}
		return netyforce;
	}
	public void update(double time, double xforce, double yforce){
		double x_acce = xforce/ this.mass;
		double y_acce = yforce / this.mass;
		this.xxVel = this.xxVel + time*x_acce;
		this.yyVel = this.yyVel + time*y_acce;
		this.xxPos = this.xxPos + this.xxVel * time;
		this.yyPos = this.yyPos + this.yyVel * time;
	}
}