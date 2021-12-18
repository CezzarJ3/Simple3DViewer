package com.vsu.cgcourse.render_engine;

import com.vsu.cgcourse.math.Matrix4f;
import com.vsu.cgcourse.math.Vector4f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphicConveyorTest {

//    @Test
//    void rotateMatrixX() {
//
//        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
//        Vector4f vector2 = new Vector4f(5, 6, 7, 8);
//        Vector4f vector3 = new Vector4f(9, 10, 11, 12);
//        Vector4f vector4 = new Vector4f(13, 14, 15, 16);
//        Matrix4f matrix = new Matrix4f(vector1, vector2, vector3, vector4);
//        Matrix4f result = Matrix4f.transposition(matrix);
//
//        Vector4f vector5 = new Vector4f(1, 5, 9, 13);
//        Vector4f vector6 = new Vector4f(2, 6, 10, 14);
//        Vector4f vector7 = new Vector4f(3, 7, 11, 15);
//        Vector4f vector8 = new Vector4f(4, 8, 12, 16);
//        Matrix4f expectedResult = new Matrix4f(vector5, vector6, vector7, vector8);
//        Assertions.assertTrue(result.equals(expectedResult));
//
//
//        new Matrix4f(new float[][]{
//                {1, 0, 0, 0},
//                {0, (float) Math.cos(Math.toRadians(a)), (float) Math.sin(Math.toRadians(a)), 0},
//                {0, (float) -Math.sin(Math.toRadians(a)), (float) Math.cos(Math.toRadians(a)), 0},
//                {0, 0, 0, 1}
//        });
//    }

    @Test
    void rotateScaleTranslate() {
    }


    @Test
    void lookAt() {
    }

    @Test
    void multiplyMatrix4ByVector3() {
    }
}