package com.vsu.cgcourse.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VectorTests {

    @Test
    void testSumVectors2f() {
        Vector2f vector1 = new Vector2f(1, 2);
        Vector2f vector2 = new Vector2f(3, 4);
        Vector2f result = Vector2f.sumVectors(vector1,vector2);
        Vector2f expectedResult = new Vector2f(4, 6);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSumVectors3f() {
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        Vector3f result = Vector3f.sumVectors(vector1,vector2);
        Vector3f expectedResult = new Vector3f(5, 7, 9);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSumVectors4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f result = Vector4f.sumVectors(vector1,vector2);
        Vector4f expectedResult = new Vector4f(6, 8, 10, 12);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSubtractVectors2f() {
        Vector2f vector1 = new Vector2f(1, 2);
        Vector2f vector2 = new Vector2f(3, 4);
        Vector2f result = Vector2f.subtractVectors(vector1,vector2);
        Vector2f expectedResult = new Vector2f(-2, -2);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSubtractVectors3f() {
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        Vector3f result = Vector3f.subtractVectors(vector1,vector2);
        Vector3f expectedResult = new Vector3f(-3, -3, -3);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testSubtractVectors4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        Vector4f result = Vector4f.subtractVectors(vector1,vector2);
        Vector4f expectedResult = new Vector4f(-4, -4, -4, -4);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarMultiplication2f() {
        Vector2f vector = new Vector2f(1, 2);
        double scalar = 2;
        Vector2f result = Vector2f.scalarMultiplication(vector, (float) scalar);
        Vector2f expectedResult = new Vector2f(2, 4);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarMultiplication3f() {
        Vector3f vector = new Vector3f(1, 2, 3);
        double scalar = 2;
        Vector3f result = Vector3f.scalarMultiplication(vector, (float) scalar);
        Vector3f expectedResult = new Vector3f(2, 4, 6);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarMultiplication4f() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        double scalar = 2;
        Vector4f result = Vector4f.scalarMultiplication(vector, (float) scalar);
        Vector4f expectedResult = new Vector4f(2, 4, 6, 8);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarDivision2f() throws Exception {
        Vector2f vector = new Vector2f(1, 2);
        double scalar = 2;
        Vector2f result = Vector2f.scalarDivision(vector, (float) scalar);
        Vector2f expectedResult = new Vector2f(0.5f, 1);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarDivision3f() throws Exception {
        Vector3f vector = new Vector3f(1, 2, 3);
        double scalar = 2;
        Vector3f result = Vector3f.scalarDivision(vector, (float) scalar);
        Vector3f expectedResult = new Vector3f(0.5f, 1, 1.5f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testScalarDivision4f() throws Exception {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        double scalar = 2;
        Vector4f result = Vector4f.scalarDivision(vector, (float) scalar);
        Vector4f expectedResult = new Vector4f(0.5f, 1, 1.5f, 2);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testVectorLength2f() {
        Vector2f vector = new Vector2f(1, 2);
        double result = Vector2f.vectorLength(vector);
        double expectedResult = 2.23606797749979;
        Assertions.assertTrue(result == expectedResult);
    }

    @Test
    void testVectorLength3f() {
        Vector3f vector = new Vector3f(1, 2, 3);
        double result = Vector3f.vectorLength(vector);
        double expectedResult = 3.7416573867739413;
        Assertions.assertTrue(result == expectedResult);
    }

    @Test
    void testVectorLength4f() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        double result = Vector4f.vectorLength(vector);
        double expectedResult = 5.477225575051661;
        Assertions.assertTrue(result == expectedResult);
    }

    @Test
    void testNormalization2f() {
        Vector2f vector = new Vector2f(1, 2);
        Vector2f result = Vector2f.normalization(vector);
        Vector2f expectedResult = new Vector2f(0.4472135954999579f, 0.8944271909999159f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testNormalization3f() {
        Vector3f vector = new Vector3f(1, 2, 3);
        Vector3f result = Vector3f.normalization(vector);
        Vector3f expectedResult = new Vector3f( 0.2672612419124244f, 0.5345224838248488f, 0.8017837257372732f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testNormalization4f() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        Vector4f result = Vector4f.normalization(vector);
        Vector4f expectedResult = new Vector4f(0.18257418583505536f, 0.3651483716701107f, 0.5477225575051661f, 0.7302967433402214f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    void testDotProduct2f() {
        Vector2f vector1 = new Vector2f(1, 2);
        Vector2f vector2 = new Vector2f(3, 4);
        double result = Vector2f.dotProduct(vector1, vector2);
        double expectedResult = 11.0;
        Assertions.assertTrue(result == expectedResult);
    }

    @Test
    void testDotProduct3f() { //тест на скаярное произведение
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        double result = Vector3f.dotProduct(vector1, vector2);
        double expectedResult = 32.0;
        Assertions.assertTrue(result == expectedResult);
    }

    @Test
    void testDotProduct4f() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
        double result = Vector4f.dotProduct(vector1, vector2);
        double expectedResult = 70.0;
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testCrossProduct3f() { //тест на векторное произведение
        Vector3f vector1 = new Vector3f(1, 2, 3);
        Vector3f vector2 = new Vector3f(4, 5, 6);
        Vector3f result = Vector3f.crossProduct(vector1,vector2);
        Vector3f expectedResult = new Vector3f(-3.0f, 6.0f, -3.0f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

}