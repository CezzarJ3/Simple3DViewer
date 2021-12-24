package com.vsu.cgcourse.render_engine;

import java.awt.*;
import java.util.ArrayList;

import com.vsu.cgcourse.math.Matrix4f;
import com.vsu.cgcourse.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import com.vsu.cgcourse.model.Mesh;

import com.vsu.cgcourse.math.Point2f;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static com.vsu.cgcourse.render_engine.GraphicConveyor.*;

public class RenderEngine {

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Mesh mesh,
            final int width,
            final int height,
            final float sx,
            final float sy,
            final float sz,
            final float rx,
            final float ry,
            final float rz,
            final float tx,
            final float ty,
            final float tz) {
        Matrix4f modelMatrix = rotateScaleTranslate(sx, sy, sz, rx, ry, rz, tx, ty, tz);
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();

        Matrix4f modelViewProjectionMatrix = modelMatrix;

//        System.out.println("ModelM " + modelViewProjectionMatrix);
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(viewMatrix, modelViewProjectionMatrix);
//        System.out.println("ModelViewMatrix " + viewMatrix);
//        System.out.println("ModelV " + modelViewProjectionMatrix);
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(projectionMatrix, modelViewProjectionMatrix);
//        System.out.println("ModelProjection " + projectionMatrix);
//        System.out.println("ModelP " + modelViewProjectionMatrix);

        final int nPolygons = mesh.polygonVertexIndices.size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.polygonVertexIndices.get(polygonInd).size();

            ArrayList<Point2f> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = mesh.vertices.get(mesh.polygonVertexIndices.get(polygonInd).get(vertexInPolygonInd));
                Point2f resultPoint = vertexToPoint(Matrix4f.multiplicationByVector3f(modelViewProjectionMatrix, vertex), width, height);
                resultPoints.add(resultPoint);
            }

            Vector3f lightVector = Vector3f.subtractVectors(camera.getTarget(), camera.getPosition());
            lightVector = Vector3f.normalization(lightVector);

            Vector3f vector1 = Matrix4f.multiplicationByVector3f(modelMatrix, mesh.vertices.get(mesh.polygonVertexIndices.get(polygonInd).get(0)));
            Vector3f vector2 = Matrix4f.multiplicationByVector3f(modelMatrix, mesh.vertices.get(mesh.polygonVertexIndices.get(polygonInd).get(1)));
            Vector3f vector3 = Matrix4f.multiplicationByVector3f(modelMatrix, mesh.vertices.get(mesh.polygonVertexIndices.get(polygonInd).get(2)));
            Vector3f polygonNormal = Vector3f.crossProduct(Vector3f.subtractVectors(vector2, vector1), Vector3f.subtractVectors(vector3, vector1));
            polygonNormal = Vector3f.normalization(polygonNormal);

            float constantLightCoefficient = Math.abs(Vector3f.dotProduct(lightVector, polygonNormal));
//            System.out.println("coefficient" + constantLightCoefficient);

            double[] resultPointsX = new double[resultPoints.size()];
            double[] resultPointsY = new double[resultPoints.size()];
            for (int pointInd = 0; pointInd < resultPoints.size(); pointInd++) {
                resultPointsX[pointInd] = resultPoints.get(pointInd).getX();
                resultPointsY[pointInd] = resultPoints.get(pointInd).getY();
            }

            graphicsContext.setFill(new Color(0.4f * (constantLightCoefficient), 0.72f * (constantLightCoefficient), 0.46f * (constantLightCoefficient), 1f));
            graphicsContext.fillPolygon(resultPointsX, resultPointsY, resultPoints.size());

            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).getX(),
                        resultPoints.get(vertexInPolygonInd - 1).getY(),
                        resultPoints.get(vertexInPolygonInd).getX(),
                        resultPoints.get(vertexInPolygonInd).getY());
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).getX(),
                        resultPoints.get(nVerticesInPolygon - 1).getY(),
                        resultPoints.get(0).getX(),
                        resultPoints.get(0).getY());
        }
    }
}