package com.gravity.physics;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private List<Particle> particles;
    private double dT; // time step
    private double G; // gravitational constant
    
    public Simulation(double dT, double G) {
        this.particles = new ArrayList<>();
        this.dT = dT;
        this.G = G;
    }


    public static Simulation createGalaxy(int particleCount, Vector2D centrePos, double galaxyRadius, int spread, double G, double dT) {
        Simulation simulation = new Simulation(dT, G);
        simulation.particles.addAll(Generator.createGalaxy(particleCount, centrePos, galaxyRadius, spread, G));
        return simulation;
    }

    public List<Particle> getParticles() {
        return particles;
    }


    public void update() {
        // called every frame

        for (Particle p : particles) {
            Vector2D force = calculateForce(p);

            // a = F / m
            double ax = force.getX() / p.getMass();
            double ay = force.getY() / p.getMass();

            // v = v + a*dT
            p.getVelocity().setX(p.getVelocity().getX() + ax * dT);
            p.getVelocity().setY(p.getVelocity().getY() + ay * dT);
        }

        for (Particle p : particles) {
            // separate loop as the above loop is for updating velocities and this one positions

            // x = x + v * dT
            p.getPosition().setX(p.getPosition().getX() + p.getVelocity().getX() * dT);
            p.getPosition().setY(p.getPosition().getY() + p.getVelocity().getY() * dT);
        }
    }


    private Vector2D calculateForce(Particle p) {
        double softening = 1.5; // prevent division by 0;

        Vector2D totalForce = new Vector2D(0, 0);

        for (Particle other : this.particles) {
            if (p == other) continue;

            // find vector for the direction
            double dx = other.getPosition().getX() - p.getPosition().getX();
            double dy = other.getPosition().getY() - p.getPosition().getY();

            double distance = dx * dx + dy * dy + softening * softening;

            double forceMagnitude = (G * p.getMass() * other.getMass() / Math.pow(distance, 2)); // newton's formula

            // split into vectors for force direction
            double fx = forceMagnitude * (dx / distance);
            double fy = forceMagnitude * (dy / distance);

            totalForce = totalForce.add(new Vector2D(fx, fy));
        }

        return totalForce;
    }

    
}
