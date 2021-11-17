package com.vsu.cgcourse.math;

public class Vector3f {

    private double x, y, z;

    public Vector3f(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Vector3f = {" +
                "x = " + x +
                ", y = " + y +
                ", z = " + z +
                "}";
    }

    public static void printVector(Vector3f vector) {
        System.out.println(vector.toString());
    }

    public boolean equals(Vector3f other) {
        // todo: потом это будет глобальная константа
        final double eps = 1e-7;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public static Vector3f sumVectors(final Vector3f vector1, final Vector3f vector2) {
        return new Vector3f(
                vector1.getX() + vector2.getX(),
                vector1.getY() + vector2.getY(),
                vector1.getZ() + vector2.getZ());
    }

    public static Vector3f subtractVectors(final Vector3f vector1, final Vector3f vector2) {
        return new Vector3f(
                vector1.getX() - vector2.getX(),
                vector1.getY() - vector2.getY(),
                vector1.getZ() - vector2.getZ());
    }

    public static Vector3f scalarMultiplication(final Vector3f vector, double scalar) {
        return new Vector3f(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar);
    }

    public static Vector3f scalarDivision(final Vector3f vector, double scalar) throws Exception {
        if (scalar != 0) {
            return new Vector3f(
                    vector.getX() / scalar,
                    vector.getY() / scalar,
                    vector.getZ() / scalar);
        } else throw new Exception("На ноль делить нельзя!");
    }

    public static double vectorLength(final Vector3f vector) {
        return Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY() +
                        vector.getZ() * vector.getZ());
    }

    public static Vector3f normalization(final Vector3f vector) {
        double length = Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY() +
                        vector.getZ() * vector.getZ());
        return new Vector3f(
                vector.getX() / length,
                vector.getY() / length,
                vector.getZ() / length);
    }

    public static double dotProduct(final Vector3f vector1, final Vector3f vector2) { //скалярное произведение
        return (vector1.getX() * vector2.getX() +
                vector1.getY() * vector2.getY() +
                vector1.getZ() * vector2.getZ());
    }

    public static Vector3f crossProduct(final Vector3f vector1, final Vector3f vector2) { //векторное произведение
        return new Vector3f(
                vector1.getY() * vector2.getZ() - vector1.getZ() * vector2.getY(),
                vector1.getZ() * vector2.getX() - vector1.getX() * vector2.getZ(),
                vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX());
    }
}
