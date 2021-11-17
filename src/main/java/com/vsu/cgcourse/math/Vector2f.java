package com.vsu.cgcourse.math;

public class Vector2f {

    private double x, y;

    public Vector2f(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2f = {" +
                "x = " + x +
                ", y = " + y +
                "}";
    }

    public boolean equals(Vector2f other) {
        // todo: потом это будет глобальная константа
        final double eps = 1e-7;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static void printVector(Vector2f vector) {
        System.out.println(vector.toString());
    }

    public static Vector2f sumVectors(final Vector2f vector1, final Vector2f vector2) {
        return new Vector2f(
                vector1.getX() + vector2.getX(),
                vector1.getY() + vector2.getY());
    }

    public static Vector2f subtractVectors(final Vector2f vector1, final Vector2f vector2) {
        return new Vector2f(
                vector1.getX() - vector2.getX(),
                vector1.getY() - vector2.getY());
    }

    public static Vector2f scalarMultiplication(final Vector2f vector, double scalar) {
        return new Vector2f(
                vector.getX() * scalar,
                vector.getY() * scalar);
    }

    public static Vector2f scalarDivision(final Vector2f vector, double scalar) throws Exception {
        if (scalar != 0) {
            return new Vector2f(
                    vector.getX() / scalar,
                    vector.getY() / scalar);
        } else throw new Exception("На ноль делить нельзя!");
    }

    public static double vectorLength(final Vector2f vector) {
        return Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY());
    }

    public static Vector2f normalization(final Vector2f vector) {
        double length = Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY());
        return new Vector2f(
                vector.getX() / length,
                vector.getY() / length);
    }

    public static double dotProduct(final Vector2f vector1, final Vector2f vector2) { //скалярное произведение
        return (vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY());
    }
}
