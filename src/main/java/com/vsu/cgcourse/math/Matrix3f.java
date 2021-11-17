package com.vsu.cgcourse.math;

public class Matrix3f {

    private Vector3f vector1, vector2, vector3;

    public Matrix3f(Vector3f vector1, Vector3f vector2, Vector3f vector3) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.vector3 = vector3;
    }

    public Vector3f getVector1() {
        return vector1;
    }

    public Vector3f getVector2() {
        return vector2;
    }

    public Vector3f getVector3() {
        return vector3;
    }

    public boolean equals(Matrix3f other) {
        // todo: потом это будет глобальная константа
        return vector1.equals(other.getVector1()) && vector2.equals(other.getVector2()) && vector3.equals(other.getVector3());
    }

    @Override
    public String toString() {
        return "Matrix3f" + "\n" +
                '{' + vector1.getX() + " " + vector2.getX() + " " + vector3.getX() + '}' + "\n" +
                '{' + vector1.getY() + " " + vector2.getY() + " " + vector3.getY() + '}' + "\n" +
                '{' + vector1.getZ() + " " + vector2.getZ() + " " + vector3.getZ() + '}';
    }

    public static void printMatrix(Matrix3f matrix){
        System.out.println(matrix.toString());
    }

    public static Matrix3f sumMatrix(final Matrix3f matrix1, final Matrix3f matrix2){
        return new Matrix3f(
                Vector3f.sumVectors(matrix1.getVector1(),matrix2.getVector1()),
                Vector3f.sumVectors(matrix1.getVector2(),matrix2.getVector2()),
                Vector3f.sumVectors(matrix1.getVector3(),matrix2.getVector3())
        );
    }

    public static Matrix3f subtractMatrix(final Matrix3f matrix1, final Matrix3f matrix2){
        return new Matrix3f(
                Vector3f.subtractVectors(matrix1.getVector1(),matrix2.getVector1()),
                Vector3f.subtractVectors(matrix1.getVector2(),matrix2.getVector2()),
                Vector3f.subtractVectors(matrix1.getVector3(),matrix2.getVector3())
        );
    }

    public static Matrix3f nullMatrix3f(){
        Vector3f vector1 = new Vector3f(0,0,0);
        Vector3f vector2 = new Vector3f(0,0,0);
        Vector3f vector3 = new Vector3f(0,0,0);
        return new Matrix3f(vector1,vector2,vector3);
    }

    public static Matrix3f identityMatrix3f(){
        Vector3f vector1 = new Vector3f(1,0,0);
        Vector3f vector2 = new Vector3f(0,1,0);
        Vector3f vector3 = new Vector3f(0,0,1);
        return new Matrix3f(vector1,vector2,vector3);
    }

    public static Matrix3f transposition(final Matrix3f matrix) {
        Vector3f vector1 = new Vector3f(
                matrix.getVector1().getX(),
                matrix.getVector2().getX(),
                matrix.getVector3().getX());
        Vector3f vector2 = new Vector3f(
                matrix.getVector1().getY(),
                matrix.getVector2().getY(),
                matrix.getVector3().getY());
        Vector3f vector3 = new Vector3f(
                matrix.getVector1().getZ(),
                matrix.getVector2().getZ(),
                matrix.getVector3().getZ());
        return new Matrix3f(vector1,vector2,vector3);
    }

    public static Vector3f multiplicationByAColumnVector(final Matrix3f matrix, final Vector3f vector) {
        return new Vector3f(
                matrix.getVector1().getX() * vector.getX() +
                        matrix.getVector2().getX() * vector.getY() +
                        matrix.getVector3().getX() * vector.getZ(),
                matrix.getVector1().getY() * vector.getX() +
                        matrix.getVector2().getY() * vector.getY() +
                        matrix.getVector3().getY() * vector.getZ(),
                matrix.getVector1().getZ() * vector.getX() +
                        matrix.getVector2().getZ() * vector.getY() +
                        matrix.getVector3().getZ() * vector.getZ()
        );
    }
}
