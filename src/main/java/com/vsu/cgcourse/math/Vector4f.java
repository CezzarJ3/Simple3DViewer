package com.vsu.cgcourse.math;

public class Vector4f {

    private double x, y, z, w;

    public Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public String toString() {
        return "Vector4f = {" +
                "x = " + x +
                ", y = " + y +
                ", z = " + z +
                ", w = " + w +
                "}";
    }

    public boolean equals(Vector4f other) {
        // todo: потом это будет глобальная константа
        final double eps = 1e-7;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps && Math.abs(w - other.w) < eps;
    }

    public static void printVector(Vector4f vector) {
        System.out.println(vector.toString());
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

    public double getW() {
        return w;
    }

    public static Vector4f sumVectors(final Vector4f vector1, final Vector4f vector2) {
        return new Vector4f(
                vector1.getX() + vector2.getX(),
                vector1.getY() + vector2.getY(),
                vector1.getZ() + vector2.getZ(),
                vector1.getW() + vector2.getW());
    }

    public static Vector4f subtractVectors(final Vector4f vector1, final Vector4f vector2) {
        return new Vector4f(
                vector1.getX() - vector2.getX(),
                vector1.getY() - vector2.getY(),
                vector1.getZ() - vector2.getZ(),
                vector1.getW() - vector2.getW());
    }

    public static Vector4f scalarMultiplication(final Vector4f vector, double scalar) {
        return new Vector4f(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar,
                vector.getW() * scalar);
    }

    public static Vector4f scalarDivision(final Vector4f vector, double scalar) throws Exception {
        if (scalar != 0) {
            return new Vector4f(
                    vector.getX() / scalar,
                    vector.getY() / scalar,
                    vector.getZ() / scalar,
                    vector.getW() / scalar);
        } else throw new Exception("На ноль делить нельзя!");
    }

    public static double vectorLength(final Vector4f vector) {
        return Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY() +
                        vector.getZ() * vector.getZ() +
                        vector.getW() * vector.getW());
    }

    public static Vector4f normalization(final Vector4f vector) {
        double length = Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY() +
                        vector.getZ() * vector.getZ() +
                        vector.getW() * vector.getW());
        return new Vector4f(
                vector.getX() / length,
                vector.getY() / length,
                vector.getZ() / length,
                vector.getW() / length);
    }

    public static double dotProduct(final Vector4f vector1, final Vector4f vector2) { //скалярное произведение
        return (
                vector1.getX() * vector2.getX() +
                        vector1.getY() * vector2.getY() +
                        vector1.getZ() * vector2.getZ() +
                        vector1.getW() * vector2.getW());
    }
}

