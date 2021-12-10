package com.vsu.cgcourse.render_engine;

import com.vsu.cgcourse.math.Matrix4f;
import com.vsu.cgcourse.math.Vector4f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphicConveyorTest {

    @Test
    void scaleMatrixEqualsExpected() {
        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);

        Matrix4f expected = new Matrix4f(
                new Vector4f(2, 0, 0, 0),
                new Vector4f(0, 3, 0, 0),
                new Vector4f(0, 0, 1, 0),
                new Vector4f(0, 0, 0, 1)
        );

        assertTrue(scale.equals(expected));
    }

    @Test
    void rotateXMatrixEqualsExpected() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);

        Matrix4f expected = new Matrix4f(
                new Vector4f(1, 0, 0, 0),
                new Vector4f(0, 0, -1, 0),
                new Vector4f(0, 1, 0, 0),
                new Vector4f(0, 0, 0, 1)
        );

        assertTrue(rotateX.equals(expected));
    }

    @Test
    void rotateYMatrixEqualsExpected() {
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);

        Matrix4f expected = new Matrix4f(
                new Vector4f(0, 0, -1, 0),
                new Vector4f(0, 1, 0, 0),
                new Vector4f(1, 0, 0, 0),
                new Vector4f(0, 0, 0, 1)
        );

        assertTrue(rotateY.equals(expected));
    }

    @Test
    void rotateZMatrixEqualsExpected() {
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);

        Matrix4f expected = new Matrix4f(
                new Vector4f(0, -1, 0, 0),
                new Vector4f(1, 0, 0, 0),
                new Vector4f(0, 0, 1, 0),
                new Vector4f(0, 0, 0, 1)
        );

        assertTrue(rotateZ.equals(expected));
    }

    @Test
    void translateMatrixEqualsExpected() {
        Matrix4f translate = GraphicConveyor.translateMatrix(1, 1, 1);

        Matrix4f expected = new Matrix4f(
                new Vector4f(1, 0, 0, 0),
                new Vector4f(0, 1, 0, 0),
                new Vector4f(0, 0, 1, 0),
                new Vector4f(1, 1, 1, 1)
        );

        assertTrue(translate.equals(expected));
    }

    @Test
    void scaleVector() {
        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);
        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f result = Matrix4f.multiplicationByAColumnVector(scale, vector);

        assertTrue(result.equals(new Vector4f(2, 6, 3, 1)));
    }

    @Test
    void rotateXRotateYMatrix() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);

        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);

        Matrix4f expected = new Matrix4f(new float[][]{
                {0, -1, 0, 0},
                {0, 0, 1, 0},
                {-1, 0, 0, 0},
                {0, 0, 0, 1}
        });

        assertTrue(rotateXRotateY.equals(expected));
    }

    @Test
    void rotateXRotateYRotateZMatrix() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);

        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);

        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);

        Matrix4f rotateXRotateYRotateZ = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Matrix4f expected = new Matrix4f(new float[][]{
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {-1, 0, 0, 0},
                {0, 0, 0, 1}
        });

        assertTrue(rotateXRotateYRotateZ.equals(expected));
    }

    @Test
    void rotateScaleMatrix() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Matrix4f rotate = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);

        Matrix4f rotateScale = Matrix4f.multiplicationByAMatrix(rotate, scale);

        Matrix4f expected = new Matrix4f(new float[][]{
                {0, 0, 1, 0},
                {0, 3, 0, 0},
                {-2, 0, 0, 0},
                {0, 0, 0, 1}
        });

        assertTrue(rotateScale.equals(expected));
    }

    @Test
    void rotateXVector() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f rotateXVector = Matrix4f.multiplicationByAColumnVector(rotateX, vector);

        Vector4f expected = new Vector4f(1, 3, -2, 1);

        assertTrue(rotateXVector.equals(expected));
    }

    @Test
    void rotateYVector() {
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f rotateYVector = Matrix4f.multiplicationByAColumnVector(rotateY, vector);

        Vector4f expected = new Vector4f(3, 2, -1, 1);

        assertTrue(rotateYVector.equals(expected));
    }

    @Test
    void rotateZVector() {
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f rotateZVector = Matrix4f.multiplicationByAColumnVector(rotateZ, vector);

        Vector4f expected = new Vector4f(2, -1, 3, 1);

        assertTrue(rotateZVector.equals(expected));
    }

    @Test
    void rotateScaleVector() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Matrix4f rotate = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);

        Matrix4f rotateScale = Matrix4f.multiplicationByAMatrix(rotate, scale);

        Vector4f vector = new Vector4f(1, 2, 3, 1);
        Vector4f rotateScaleVector = Matrix4f.multiplicationByAColumnVector(rotateScale, vector);

        Vector4f expected = new Vector4f(3, 6, -2, 1);

        assertTrue(rotateScaleVector.equals(expected));
    }

    @Test
    void rotateVector() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Matrix4f rotate = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Vector4f vector = new Vector4f(1, 2, 3, 1);
        Vector4f rotateVector = Matrix4f.multiplicationByAColumnVector(rotate, vector);

        Vector4f expected = new Vector4f(3, 2, -1, 1);

        assertTrue(rotateVector.equals(expected));
    }

    @Test
    void translateVector() {
        Matrix4f translate = GraphicConveyor.translateMatrix(1, 1, 1);
        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f translateVector = Matrix4f.multiplicationByAColumnVector(translate, vector);

        Vector4f expected = new Vector4f(2, 3, 4, 1);

        assertTrue(translateVector.equals(expected));
    }

    @Test
    void translateRotateScaleMatrix() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Matrix4f rotate = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);

        Matrix4f rotateScale = Matrix4f.multiplicationByAMatrix(rotate, scale);

        Matrix4f translate = GraphicConveyor.translateMatrix(1, 1, 1);

        Matrix4f translateRotateScale = Matrix4f.multiplicationByAMatrix(translate, rotateScale);

        Matrix4f expected = new Matrix4f(new float[][]{
                {0, 0, 1, 1},
                {0, 3, 0, 1},
                {-2, 0, 0, 1},
                {0, 0, 0, 1}
        });

        assertTrue(translateRotateScale.equals(expected));
    }

    @Test
    void translateRotateScaleVector() {
        Matrix4f rotateX = GraphicConveyor.rotateMatrixX(90);
        Matrix4f rotateY = GraphicConveyor.rotateMatrixY(90);
        Matrix4f rotateXRotateY = Matrix4f.multiplicationByAMatrix(rotateY, rotateX);
        Matrix4f rotateZ = GraphicConveyor.rotateMatrixZ(90);
        Matrix4f rotate = Matrix4f.multiplicationByAMatrix(rotateZ, rotateXRotateY);

        Matrix4f scale = GraphicConveyor.scaleMatrix(2, 3, 1);
        Matrix4f rotateScale = Matrix4f.multiplicationByAMatrix(rotate, scale);

        Matrix4f translate = GraphicConveyor.translateMatrix(1, 1, 1);
        Matrix4f TRS = Matrix4f.multiplicationByAMatrix(translate, rotateScale);

        Vector4f vector = new Vector4f(1, 2, 3, 1);

        Vector4f TRSVector = Matrix4f.multiplicationByAColumnVector(TRS, vector);

        Vector4f expected = new Vector4f(4, 7, -1, 1);

        assertTrue(TRSVector.equals(expected));
    }
}