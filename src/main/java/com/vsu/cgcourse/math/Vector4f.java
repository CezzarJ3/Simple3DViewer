package com.vsu.cgcourse.math;

public class Vector4f {

    private float x, y, z, w;

    public Vector4f(float x, float y, float z, float w) {
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
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

    public static Vector4f scalarMultiplication(final Vector4f vector, float scalar) {
        return new Vector4f(
                vector.getX() * scalar,
                vector.getY() * scalar,
                vector.getZ() * scalar,
                vector.getW() * scalar);
    }

    public static Vector4f scalarDivision(final Vector4f vector, float scalar) throws Exception {
        if (scalar != 0) {
            return new Vector4f(
                    vector.getX() / scalar,
                    vector.getY() / scalar,
                    vector.getZ() / scalar,
                    vector.getW() / scalar);
        } else throw new Exception("На ноль делить нельзя!");
    }

    public static float vectorLength(final Vector4f vector) {
        return (float) Math.sqrt(
                vector.getX() * vector.getX() +
                        vector.getY() * vector.getY() +
                        vector.getZ() * vector.getZ() +
                        vector.getW() * vector.getW());
    }

    public static Vector4f normalization(final Vector4f vector) {
        float length = (float) Math.sqrt(
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

    public static float dotProduct(final Vector4f vector1, final Vector4f vector2) { //скалярное произведение
        return (
                vector1.getX() * vector2.getX() +
                        vector1.getY() * vector2.getY() +
                        vector1.getZ() * vector2.getZ() +
                        vector1.getW() * vector2.getW());
    }
}

