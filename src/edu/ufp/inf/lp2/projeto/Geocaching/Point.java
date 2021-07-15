/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ufp.inf.lp2.projeto.Geocaching;


public class Point {

    private double x;
    private double y;
    int id;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public double distX(Point p) {
        return Math.abs(p.getX() - this.x);
    }

    public double distY(Point p) {
        return Math.abs(p.getY() - this.y);
    }

    public double dist(Point p) {
        return (double) Math.sqrt(Math.pow(this.distX(p),2) + (Math.pow(this.distY(p),2)));
    }

    public void moveX(float delta) {
        this.x += delta;
    }

    public void moveY(float delta) {
        this.y += delta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void move(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void printPoint() {
        System.out.println("x->" + this.getX() + " y->" + this.y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.0f, 2.0f);
        Point p2 = new Point(5.0f, 7.0f);
        Point p3 = new Point(10.0f, 1.0f);

        System.out.println(p1);
        System.out.println(p2);

        System.out.println(p1.distX(p2));
        System.out.println(p1.distX(p1));

        System.out.println(p2.distY(p1));
        System.out.println(p2.distY(p2));

        System.out.println(p1.dist(p2));

        p1.move(p1.distX(p3), p1.distY(p2));
        p1.printPoint();
    }
}

