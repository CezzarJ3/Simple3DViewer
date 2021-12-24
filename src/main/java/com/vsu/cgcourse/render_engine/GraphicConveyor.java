package com.vsu.cgcourse.render_engine;

import com.vsu.cgcourse.math.Matrix4f;
import com.vsu.cgcourse.math.Vector3f;
import com.vsu.cgcourse.math.Point2f;

public class GraphicConveyor {

    //todo реализовать матрицу модели M=TRS

    public static Matrix4f rotateScaleTranslate(float sx, float sy, float sz, float rx, float ry, float rz, float tx, float ty, float tz) {
        float[][] matrix = new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}};

        Matrix4f scaleMatrix = scaleMatrix(sx, sy, sz);
        Matrix4f rotateMatrixX = rotateMatrixX(rx);
        Matrix4f rotateMatrixY = rotateMatrixY(ry);
        Matrix4f rotateMatrixZ = rotateMatrixZ(rz);
        Matrix4f translateMatrix = translateMatrix(tx, ty, tz);

        Matrix4f rotateMatrix = Matrix4f.multiplicationByAMatrix(rotateMatrixY, rotateMatrixX);
        rotateMatrix = Matrix4f.multiplicationByAMatrix(rotateMatrixZ, rotateMatrix);

        Matrix4f result = Matrix4f.multiplicationByAMatrix(rotateMatrix, scaleMatrix);

//        System.out.println(result);
//        System.out.println(translateMatrix);
        result = Matrix4f.multiplicationByAMatrix(translateMatrix, result);
//        System.out.println(result);
//        System.out.println("---");

        return result;
    }

    public static Matrix4f scaleMatrix(float sx, float sy, float sz) {
        return new Matrix4f(new float[][]{
                {sx, 0, 0, 0},
                {0, sy, 0, 0},
                {0, 0, sz, 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix4f rotateMatrixZ(float a) {
        return new Matrix4f(new float[][]{
                {(float) Math.cos(Math.toRadians(a)), (float) Math.sin(Math.toRadians(a)), 0, 0},
                {(float) -Math.sin(Math.toRadians(a)), (float) Math.cos(Math.toRadians(a)), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix4f rotateMatrixX(float a) {
        return new Matrix4f(new float[][]{
                {1, 0, 0, 0},
                {0, (float) Math.cos(Math.toRadians(a)), (float) Math.sin(Math.toRadians(a)), 0},
                {0, (float) -Math.sin(Math.toRadians(a)), (float) Math.cos(Math.toRadians(a)), 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix4f rotateMatrixY(float a) {
        return new Matrix4f(new float[][]{
                {(float) Math.cos(Math.toRadians(a)), 0, (float) Math.sin(Math.toRadians(a)), 0},
                {0, 1, 0, 0},
                {(float) -Math.sin(Math.toRadians(a)), 0, (float) Math.cos(Math.toRadians(a)), 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix4f translateMatrix(float tx, float ty, float tz) {
        return new Matrix4f(new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        });
    }

    //todo заметка отвечает за матрицу view eye - origin, target - target
    public static Matrix4f lookAt(Vector3f eye, Vector3f target) {
        return lookAt(eye, target, new Vector3f(0F, 1.0F, 0F));
    }

    public static Matrix4f lookAt(Vector3f eye, Vector3f target, Vector3f up) {
        Vector3f resultX;
        Vector3f resultY;
        Vector3f resultZ;

        //todo заметка получение координат камеры
        resultZ = Vector3f.subtractVectors(target, eye);
        resultX = Vector3f.crossProduct(up, resultZ);
        resultY = Vector3f.crossProduct(resultZ, resultX);

        resultX = Vector3f.normalization(resultX);
        resultY = Vector3f.normalization(resultY);
        resultZ = Vector3f.normalization(resultZ);

        //todo заметка перемещение в начало координат+проецирование это матрица view из двух этапов
//        float[][] matrix = new float[][]{
//                {resultX.getX(), resultY.getX(), resultZ.getX(), 0},
//                {resultX.getY(), resultY.getY(), resultZ.getY(), 0},
//                {resultX.getZ(), resultY.getZ(), resultZ.getZ(), 0},
//                {-Vector3f.dotProduct(resultX, eye), -Vector3f.dotProduct(resultY, eye), -Vector3f.dotProduct(resultZ, eye), 1}};

        float[][] matrix = new float[][]{
                {resultX.getX(), resultX.getY(), resultX.getZ(), -Vector3f.dotProduct(resultX, eye)},
                {resultY.getX(), resultY.getY(), resultY.getZ(), -Vector3f.dotProduct(resultY, eye)},
                {resultZ.getX(), resultZ.getY(), resultZ.getZ(), -Vector3f.dotProduct(resultZ, eye)},
                {0, 0, 0, 1}
        };

//        float[][] matrix = new float[][]{
//                {resultX.getX(), resultX.getY(), resultX.getZ(), 0},
//                {resultY.getX(), resultY.getY(), resultY.getZ(), 0},
//                {resultZ.getX(), resultZ.getY(), resultZ.getZ(), 0},
//                {-Vector3f.dotProduct(resultX, eye), -Vector3f.dotProduct(resultY, eye), -Vector3f.dotProduct(resultZ, eye), 1}
//        };

        return new Matrix4f(matrix);
    }

    //todo заметка матрица проекции
    public static Matrix4f perspective(
            final float fov,
            final float aspectRatio,
            final float nearPlane,
            final float farPlane) {
        Matrix4f result = Matrix4f.nullMatrix4f();
//        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov * 0.5F)));
//        result.getVector1().setX(tangentMinusOnDegree / aspectRatio);
//        result.getVector2().setY(tangentMinusOnDegree);
//        result.getVector3().setZ((farPlane + nearPlane) / (farPlane - nearPlane));
//        result.getVector3().setW(2 * (nearPlane * farPlane) / (nearPlane - farPlane));
//        result.getVector3().setW(1.0F);

        float tangentMinusOnDegree = (float) (1.0F / (Math.tan(fov * 0.5F)));
        result.getVector1().setX(tangentMinusOnDegree / aspectRatio);
        result.getVector2().setY(tangentMinusOnDegree);
        result.getVector3().setZ((farPlane + nearPlane) / (farPlane - nearPlane));
        result.getVector4().setZ(1.0F);
        result.getVector3().setW(2 * (nearPlane * farPlane) / (nearPlane - farPlane));

        return result;
    }

    public static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix, final Vector3f vertex) {
        final float x = (vertex.getX() * matrix.getVector1().getX()) + (vertex.getY() * matrix.getVector2().getX()) + (vertex.getZ() * matrix.getVector3().getX()) + matrix.getVector4().getX();
        final float y = (vertex.getX() * matrix.getVector1().getY()) + (vertex.getY() * matrix.getVector2().getY()) + (vertex.getZ() * matrix.getVector3().getY()) + matrix.getVector4().getY();
        final float z = (vertex.getX() * matrix.getVector1().getZ()) + (vertex.getY() * matrix.getVector2().getZ()) + (vertex.getZ() * matrix.getVector3().getZ()) + matrix.getVector4().getZ();
        final float w = (vertex.getX() * matrix.getVector1().getW()) + (vertex.getY() * matrix.getVector2().getW()) + (vertex.getZ() * matrix.getVector3().getW()) + matrix.getVector4().getW();
//        System.out.println("Vector4: " + matrix.getVector4());
//        System.out.println("W: " + w);
        return new Vector3f(x / w, y / w, z / w);
    }

//    public static Vector3f multiplyMatrix4ByVector3(final Matrix4f matrix, final Vector3f vertex) {
//        final float x = (matrix.getVector1().getX() * vertex.getX()) + (matrix.getVector1().getY() * vertex.getY()) + (matrix.getVector1().getZ() * vertex.getZ()) + matrix.getVector1().getW();
//        final float y = (matrix.getVector2().getX() * vertex.getX()) + (matrix.getVector2().getY() * vertex.getY()) + (matrix.getVector2().getZ() * vertex.getZ()) + matrix.getVector2().getW();
//        final float z = (matrix.getVector3().getX() * vertex.getX()) + (matrix.getVector3().getY() * vertex.getY()) + (matrix.getVector3().getZ() * vertex.getZ()) + matrix.getVector3().getW();
//        final float w = (matrix.getVector4().getX() * vertex.getX()) + (matrix.getVector4().getY() * vertex.getY()) + (matrix.getVector4().getZ() * vertex.getZ()) + matrix.getVector4().getW();
////        System.out.println("Vector4: " + matrix.getVector4());
////        System.out.println("W: " + w);
////
////        System.out.println("Vector: " + new Vector3f(x / w, y / w, z / w));
//        return new Vector3f(x / w, y / w, z / w);
//    }

    public static Point2f vertexToPoint(final Vector3f vertex, final int width, final int height) {
        return new Point2f(vertex.getX() * width + width / 2.0F, -vertex.getY() * height + height / 2.0F);
    }

    public static Matrix4f getModelViewProjectionMatrix(Camera camera, float sx, float sy, float sz, float rx, float ry, float rz, float tx, float ty, float tz) {
        Matrix4f modelMatrix = rotateScaleTranslate(sx, sy, sz, rx, ry, rz, tx, ty, tz);
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();
        Matrix4f modelViewProjectionMatrix = modelMatrix;
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(viewMatrix, modelViewProjectionMatrix);
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(projectionMatrix, modelViewProjectionMatrix);

        return modelViewProjectionMatrix;
    }
}
