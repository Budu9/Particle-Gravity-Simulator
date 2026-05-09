package com.gravity.physics;

public class Particle {
    private Vector2D position;
    private Vector2D velocity;
    private Colour colour;
    private double mass;
    private double radius;

    public Particle(Vector2D position, Vector2D velocity, Colour colour, double mass, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.colour = colour;
        this.mass = mass;
        this.radius = radius;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}