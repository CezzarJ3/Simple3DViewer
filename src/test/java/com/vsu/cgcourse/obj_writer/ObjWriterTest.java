package com.vsu.cgcourse.obj_writer;

import com.vsu.cgcourse.math.Vector3f;
import com.vsu.cgcourse.model.Mesh;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ObjWriterTest {

    @Test
    void write() {
        Locale.setDefault(Locale.ROOT);
        try {
            Mesh mesh = new Mesh();
            ArrayList<Vector3f> vertices = new ArrayList<>();
            vertices.add(new Vector3f(1.34f, 0.56f, -1.2f ));
            mesh.vertices = vertices;
            ObjWriter.write(mesh, "MyModel.obj");

            Path fileName = Path.of("MyModel.obj");
            String result = Files.readString(fileName);
            String expectedResult = "v 1.340000 0.560000 -1.200000\n";

            Assertions.assertEquals(result, expectedResult);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}