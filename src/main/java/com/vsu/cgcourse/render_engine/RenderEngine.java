package com.vsu.cgcourse.render_engine;

import java.util.ArrayList;

import com.vsu.cgcourse.math.Matrix4f;
import com.vsu.cgcourse.math.Vector3f;
import javafx.scene.canvas.GraphicsContext;
import com.vsu.cgcourse.model.Mesh;

import com.vsu.cgcourse.math.Point2f;

import static com.vsu.cgcourse.render_engine.GraphicConveyor.*;

public class RenderEngine {

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Mesh mesh,
            final int width,
            final int height) {
        Matrix4f modelMatrix = rotateScaleTranslate();
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();

        Matrix4f modelViewProjectionMatrix = modelMatrix;

//        System.out.println("ModelM " + modelViewProjectionMatrix);
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(modelViewProjectionMatrix, viewMatrix);
//        System.out.println("ModelViewMatrix " + viewMatrix);
//        System.out.println("ModelV " + modelViewProjectionMatrix);
        modelViewProjectionMatrix = Matrix4f.multiplicationByAMatrix(modelViewProjectionMatrix, projectionMatrix);
//        System.out.println("ModelProjection " + projectionMatrix);
//        System.out.println("ModelP " + modelViewProjectionMatrix);

        final int nPolygons = mesh.polygonVertexIndices.size();
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            final int nVerticesInPolygon = mesh.polygonVertexIndices.get(polygonInd).size();

            ArrayList<Point2f> resultPoints = new ArrayList<>();
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                Vector3f vertex = mesh.vertices.get(mesh.polygonVertexIndices.get(polygonInd).get(vertexInPolygonInd));
                Point2f resultPoint = vertexToPoint(multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertex), width, height);
                resultPoints.add(resultPoint);
            }

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