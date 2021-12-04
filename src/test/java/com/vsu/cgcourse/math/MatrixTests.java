package com.vsu.cgcourse.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixTests {

   @Test
    void testSumMatrix3f() {
       Vector3f vector1 = new Vector3f(5, 6, 7);
       Vector3f vector2 = new Vector3f(8, 9, 10);
       Vector3f vector3 = new Vector3f(11, 12, 13);
       Matrix3f matrix1 = new Matrix3f(vector1, vector2, vector3);

        Vector3f vector4 = new Vector3f(1, 2, 3);
        Vector3f vector5 = new Vector3f(4, 5, 6);
        Vector3f vector6 = new Vector3f(7, 8, 9);
        Matrix3f matrix2 = new Matrix3f(vector4, vector5, vector6);
        Matrix3f result = Matrix3f.sumMatrix(matrix1, matrix2);

        Vector3f vector7 = new Vector3f(6, 8, 10);
        Vector3f vector8 = new Vector3f(12, 14, 16);
        Vector3f vector9 = new Vector3f(18, 20, 22);
        Matrix3f expectedResult = new Matrix3f(vector7, vector8, vector9);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSumMatrix4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f vector3 = new Vector4f(9, 10, 11, 12);
        Vector4f vector4 = new Vector4f(13, 14, 15, 16);
        Matrix4f matrix = new Matrix4f(vector1, vector2, vector3, vector4);
        Matrix4f result = Matrix4f.sumMatrix(matrix, matrix);

        Vector4f vector5 = new Vector4f(2, 4, 6, 8);
        Vector4f vector6 = new Vector4f(10, 12, 14, 16);
        Vector4f vector7 = new Vector4f(18, 20, 22, 24);
        Vector4f vector8 = new Vector4f(26, 28, 30, 32);
        Matrix4f expectedResult = new Matrix4f(vector5, vector6, vector7, vector8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSubtractMatrix3f() {
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        Vector3f vector3 = new Vector3f(7, 8, 9);
        Matrix3f matrix = new Matrix3f(vector1, vector2, vector3);
        Matrix3f result = Matrix3f.subtractMatrix(matrix, matrix);

        Vector3f vector4 = new Vector3f(0, 0, 0);
        Vector3f vector5 = new Vector3f(0, 0, 0);
        Vector3f vector6 = new Vector3f(0, 0, 0);
        Matrix3f expectedResult = new Matrix3f(vector4, vector5, vector6);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSubtractMatrix4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f vector3 = new Vector4f(9, 10, 11, 12);
        Vector4f vector4 = new Vector4f(13, 14, 15, 16);
        Matrix4f matrix = new Matrix4f(vector1, vector2, vector3, vector4);
        Matrix4f result = Matrix4f.subtractMatrix(matrix, matrix);

        Vector4f vector5 = new Vector4f(0, 0, 0, 0);
        Vector4f vector6 = new Vector4f(0, 0, 0, 0);
        Vector4f vector7 = new Vector4f(0, 0, 0, 0);
        Vector4f vector8 = new Vector4f(0, 0, 0, 0);
        Matrix4f expectedResult = new Matrix4f(vector5, vector6, vector7, vector8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testTransposition3f() {
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        Vector3f vector3 = new Vector3f(7, 8, 9);
        Matrix3f matrix = new Matrix3f(vector1, vector2, vector3);
        Matrix3f result = Matrix3f.transposition(matrix);

        Vector3f vector4 = new Vector3f(1, 4, 7);
        Vector3f vector5 = new Vector3f(2, 5, 8);
        Vector3f vector6 = new Vector3f(3, 6, 9);
        Matrix3f expectedResult = new Matrix3f(vector4, vector5, vector6);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testTransposition4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f vector3 = new Vector4f(9, 10, 11, 12);
        Vector4f vector4 = new Vector4f(13, 14, 15, 16);
        Matrix4f matrix = new Matrix4f(vector1, vector2, vector3, vector4);
        Matrix4f result = Matrix4f.transposition(matrix);

        Vector4f vector5 = new Vector4f(1, 5, 9, 13);
        Vector4f vector6 = new Vector4f(2, 6, 10, 14);
        Vector4f vector7 = new Vector4f(3, 7, 11, 15);
        Vector4f vector8 = new Vector4f(4, 8, 12, 16);
        Matrix4f expectedResult = new Matrix4f(vector5, vector6, vector7, vector8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testMultiplicationByAColumnVector3f() {
        Vector3f vector1 = new Vector3f(1, 1, 4);
        Vector3f vector2 = new Vector3f(2, 0, 5);
        Vector3f vector3 = new Vector3f(-3, 2, 3);
        Vector3f vector4 = new Vector3f(1, 2, 1);
        Matrix3f matrix = new Matrix3f(vector1, vector2, vector3);
        Vector3f result = Matrix3f.multiplicationByAColumnVector(matrix, vector4);
        Vector3f expectedResult = new Vector3f(2, 3, 17);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testMultiplicationByAColumnVector4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f vector3 = new Vector4f(9, 10, 11, 12);
        Vector4f vector4 = new Vector4f(13, 14, 15, 16);
        Vector4f vector5 = new Vector4f(1, 2, 3, 4);
        Matrix4f matrix = new Matrix4f(vector1, vector2, vector3, vector4);
        Vector4f result = Matrix4f.multiplicationByAColumnVector(matrix, vector5);
        Vector4f expectedResult = new Vector4f(90, 100, 110, 120);
        Assertions.assertTrue(result.equals(expectedResult));
    }

}