package com.vsu.cgcourse.math;

public class Matrix4f {
    private Vector4f vector1, vector2, vector3, vector4;

    public Matrix4f(Vector4f vector1, Vector4f vector2, Vector4f vector3, Vector4f vector4) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.vector3 = vector3;
        this.vector4 = vector4;
    }

    public Vector4f getVector1() {
        return vector1;
    }

    public Vector4f getVector2() {
        return vector2;
    }

    public Vector4f getVector3() {
        return vector3;
    }

    public Vector4f getVector4() {
        return vector4;
    }

    public boolean equals(Matrix4f other) {
        // todo: потом это будет глобальная константа
        return vector1.equals(other.getVector1()) && vector2.equals(other.getVector2()) && vector3.equals(other.getVector3()) && vector4.equals(other.getVector4());
    }

    @Override
    public String toString() {
        return "Matrix4f" + "\n" +
                '{' + vector1.getX() + " " + vector2.getX() + " " + vector3.getX() + " " + vector4.getX() + '}' + "\n" +
                '{' + vector1.getY() + " " + vector2.getY() + " " + vector3.getY() + " " + vector4.getY() + '}' + "\n" +
                '{' + vector1.getZ() + " " + vector2.getZ() + " " + vector3.getZ() + " " + vector4.getZ() + '}' + "\n" +
                '{' + vector1.getW() + " " + vector2.getW() + " " + vector3.getW() + " " + vector4.getW() + '}';
    }

    public static void printMatrix(Matrix4f matrix) {
        System.out.println(matrix.toString());
    }

    public static Matrix4f sumMatrix(final Matrix4f matrix1, final Matrix4f matrix2) {
        return new Matrix4f(
                Vector4f.sumVectors(matrix1.getVector1(), matrix2.getVector1()),
                Vector4f.sumVectors(matrix1.getVector2(), matrix2.getVector2()),
                Vector4f.sumVectors(matrix1.getVector3(), matrix2.getVector3()),
                Vector4f.sumVectors(matrix1.getVector4(), matrix2.getVector4())
        );
    }

    public static Matrix4f subtractMatrix(final Matrix4f matrix1, final Matrix4f matrix2) {
        return new Matrix4f(
                Vector4f.subtractVectors(matrix1.getVector1(), matrix2.getVector1()),
                Vector4f.subtractVectors(matrix1.getVector2(), matrix2.getVector2()),
                Vector4f.subtractVectors(matrix1.getVector3(), matrix2.getVector3()),
                Vector4f.subtractVectors(matrix1.getVector4(), matrix2.getVector4())
        );
    }

    public static Matrix4f nullMatrix4f() {
        Vector4f vector1 = new Vector4f(0, 0, 0, 0);
        Vector4f vector2 = new Vector4f(0, 0, 0, 0);
        Vector4f vector3 = new Vector4f(0, 0, 0, 0);
        Vector4f vector4 = new Vector4f(0, 0, 0, 0);
        return new Matrix4f(vector1, vector2, vector3, vector4);
    }

    public static Matrix4f identityMatrix4f() {
        Vector4f vector1 = new Vector4f(1, 0, 0, 0);
        Vector4f vector2 = new Vector4f(0, 1, 0, 0);
        Vector4f vector3 = new Vector4f(0, 0, 1, 0);
        Vector4f vector4 = new Vector4f(0, 0, 0, 1);
        return new Matrix4f(vector1, vector2, vector3, vector4);
    }

    public static Matrix4f transposition(final Matrix4f matrix) {
        Vector4f vector1 = new Vector4f(
                matrix.getVector1().getX(),
                matrix.getVector2().getX(),
                matrix.getVector3().getX(),
                matrix.getVector4().getX());
        Vector4f vector2 = new Vector4f(
                matrix.getVector1().getY(),
                matrix.getVector2().getY(),
                matrix.getVector3().getY(),
                matrix.getVector4().getY());
        Vector4f vector3 = new Vector4f(
                matrix.getVector1().getZ(),
                matrix.getVector2().getZ(),
                matrix.getVector3().getZ(),
                matrix.getVector4().getZ());
        Vector4f vector4 = new Vector4f(
                matrix.getVector1().getW(),
                matrix.getVector2().getW(),
                matrix.getVector3().getW(),
                matrix.getVector4().getW());
        return new Matrix4f(vector1, vector2, vector3, vector4);
    }

    public static Vector4f multiplicationByAColumnVector(final Matrix4f matrix, final Vector4f vector) {
        return new Vector4f(
                matrix.getVector1().getX() * vector.getX() +
                        matrix.getVector2().getX() * vector.getY() +
                        matrix.getVector3().getX() * vector.getZ() +
                        matrix.getVector4().getX() * vector.getW(),
                matrix.getVector1().getY() * vector.getX() +
                        matrix.getVector2().getY() * vector.getY() +
                        matrix.getVector3().getY() * vector.getZ() +
                        matrix.getVector4().getY() * vector.getW(),
                matrix.getVector1().getZ() * vector.getX() +
                        matrix.getVector2().getZ() * vector.getY() +
                        matrix.getVector3().getZ() * vector.getZ() +
                        matrix.getVector4().getZ() * vector.getW(),
                matrix.getVector1().getW() * vector.getX() +
                        matrix.getVector2().getW() * vector.getY() +
                        matrix.getVector3().getW() * vector.getZ() +
                        matrix.getVector4().getW() * vector.getW()
        );
    }
}
