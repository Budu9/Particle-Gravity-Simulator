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

    
}
