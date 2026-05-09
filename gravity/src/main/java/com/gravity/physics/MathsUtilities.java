package com.gravity.physics;

public class MathsUtilities {
    

    public static Vector2D polarToCartesian(double radius, double angle) {
        return new Vector2D(radius * Math.cos(angle), radius * Math.sin(angle));
    }
}
