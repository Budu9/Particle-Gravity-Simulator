package com.gravity.physics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Generator {
    

    public static List<Particle> createGalaxy(int particleCount, Vector2D centrePos, double galaxyRadius, int spread, double G) { // TODO think about randomizing particle sizes and masses

        double blackHoleMass = 1000;
        double blackHoleRadius = 10;

        
        List<Particle> particles = new ArrayList<>();


        // galaxy radius around 400px should be good
        Particle blackHole = new Particle(centrePos, new Vector2D(0, 0), Colour.WHITE, blackHoleMass, blackHoleRadius); // 1000 times more massive than stars, 10 times wider
        // TODO make blackHole class inherit Particle
        particles.add(blackHole);

        Random random = new Random();

        // use normal distribution to distribute the stars around the core
        for (int i = 0; i < particleCount; i++) {

            // radius from black hole calculation
            double r = random.nextGaussian() * spread; // good spread should be around 150
            r = Math.abs(r); // since normal dist can give negative radius
            if (r > galaxyRadius) {
                r = galaxyRadius;
            }

            r = Math.max(r, blackHoleRadius + (random.nextDouble() * 10)); // make sure particle is not inside black hole


            double angle = random.nextDouble(0, 2 * Math.PI);

            Vector2D offset = MathsUtilities.polarToCartesian(r, angle); // convert radius and angle into actual (x, y) coords
            // this is relative to BH right now

            double orbitVelocity = Math.sqrt((G * blackHoleMass) / r); // minimum magnitude of velocity to keep star in orbit of BH
            // TODO maybe add randomness to velocity

            double xVelocity = -(offset.getY() / r); // find unit vector (direction) and flips to get perpendicular direction
            double yVelocity = (offset.getX() / r); // these are now directions

            xVelocity *= orbitVelocity; // multiply by magnitude
            yVelocity *= orbitVelocity;

            Vector2D screenPos = offset.add(centrePos);
            

            
            // make radius correlated to mass so maybe sqrt(mass) = radius
            // also actually initialise
            double randomRadius = random.nextDouble() * 2; // small random radius
            particles.add(new Particle(screenPos, new Vector2D(xVelocity, yVelocity), Colour.WHITE, Math.pow(randomRadius, 2) + 0.1, randomRadius));
            // +0.1 is used to make sure tiny particles have non-negligeable gravity
        }

        return particles;

    }
}
