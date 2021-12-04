package com.vsu.cgcourse.obj_reader;

import com.vsu.cgcourse.math.Vector3f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ObjReaderTest {

    @Test
    public void testParseVertex01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 3);

        Vector3f expectedResult = new Vector3f(1.01f, 1.02f, 1.03f);
        Assertions.assertTrue(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex02() { //здесь берем неправлые значения
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.01", "1.02", "1.03"));
        Vector3f result = ObjReader.parseVertex(wordsInLineWithoutToken, 1);
        Vector3f expectedResult = new Vector3f(1.011f, 1.02f, 1.150f);
        Assertions.assertFalse(result.equals(expectedResult));
    }

    @Test
    public void testParseVertex03() { //проверка работы exception
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("ab", "o", "ba"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseVertex04() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("1.0", "2.0"));
        try {
            ObjReader.parseVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseTextureVertex01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("2", "f"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Failed to parse float value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }
    @Test
    public void testParseTextureVertex02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("2"));
        try {
            ObjReader.parseTextureVertex(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few texture vertex arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseNormal01() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7.1", "2.3", "4.0", "6"));
        try {
            ObjReader.parseNormal(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too many normal arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }
    @Test
    public void testParseNormal02() {
        ArrayList<String> wordsInLineWithoutToken = new ArrayList<>(Arrays.asList("7.1", "2.3"));
        try {
            ObjReader.parseNormal(wordsInLineWithoutToken, 10);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Too few normal arguments.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }
    }

    @Test
    public void testParseFace() {
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();

        String wordInLine = "4508/4508 4509/4509 4510/4510 5312/5312";
        ArrayList<String> words = new ArrayList<>(Arrays.asList(wordInLine.split(" ")));

        try {
            ObjReader.parseFace(words, onePolygonVertexIndices, onePolygonTextureVertexIndices, onePolygonNormalIndices, 10, 5312, 5312, 5312);
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Invalid value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }

    }

    // отрицательные индексы
    @Test
    public void testParseFaceWord() {
        ArrayList<Integer> onePolygonVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonTextureVertexIndices = new ArrayList<>();
        ArrayList<Integer> onePolygonNormalIndices = new ArrayList<>();

        String wordInLine = "4510/-12/4510";
        String[] words = wordInLine.split(" ");
        try {
            for(String s : words) {
                ObjReader.parseFaceWord(s, onePolygonVertexIndices, onePolygonTextureVertexIndices, onePolygonNormalIndices, 10, 4510, 4510, 4510);
            }
        } catch (ObjReaderException exception) {
            String expectedError = "Error parsing OBJ file on line: 10. Invalid value.";
            Assertions.assertEquals(expectedError, exception.getMessage());
        }

    }

}