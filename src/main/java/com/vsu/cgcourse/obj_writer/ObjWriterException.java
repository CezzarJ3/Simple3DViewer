package com.vsu.cgcourse.obj_writer;

public class ObjWriterException extends RuntimeException {
    public ObjWriterException(String errorMessage, int lineInd) {
        super("Error parsing OBJ file on line: " + lineInd + ". " + errorMessage);
    }
}
