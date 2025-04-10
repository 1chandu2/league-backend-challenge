package org.example.servicetest;

import org.example.service.MatrixService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

public class MatrixServiceTest {
    private final MatrixService matrixService = new MatrixService();

    private final String validMatrixCsv = "1,2,3\n4,5,6\n7,8,9";
    private final String invalidMatrixCsv = "1,2\n3,4,5"; // Uneven columns
    private final String nonSquareMatrixCsv = "1,2,3\n4,5,6"; // 2x3 matrix

    @Test
    void testEcho() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", validMatrixCsv.getBytes());
        String result = matrixService.echo(file);
        assertEquals("1,2,3\n4,5,6\n7,8,9", result);
    }

    @Test
    void testInvert() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", validMatrixCsv.getBytes());
        String result = matrixService.invert(file);
        assertEquals("1,4,7\n2,5,8\n3,6,9", result);
    }

    @Test
    void testFlatten() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", validMatrixCsv.getBytes());
        String result = matrixService.flatten(file);
        assertEquals("1,2,3,4,5,6,7,8,9", result);
    }

    @Test
    void testSum() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", validMatrixCsv.getBytes());
        Integer result = matrixService.sum(file);
        assertEquals(45, result);
    }

    @Test
    void testMultiply() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "matrix.csv", "text/csv", validMatrixCsv.getBytes());
        Integer result = matrixService.multiply(file);
        assertEquals(362880, result);
    }

    @Test
    void testInvalidMatrixUnevenColumns() {
        MockMultipartFile file = new MockMultipartFile("file", "bad.csv", "text/csv", invalidMatrixCsv.getBytes());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.echo(file));
        assertTrue(exception.getMessage().contains("Matrix is not square (number of rows and columns are not same)."));
    }

    @Test
    void testInvalidMatrixNotSquare() {
        MockMultipartFile file = new MockMultipartFile("file", "bad.csv", "text/csv", nonSquareMatrixCsv.getBytes());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> matrixService.echo(file));
        assertTrue(exception.getMessage().contains("Matrix is not square (number of rows and columns are not same)."));
    }
}
