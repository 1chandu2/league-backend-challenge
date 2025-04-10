package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatrixService {

    /**
     * Parses the uploaded CSV file into a 2D integer array and validates it.
     *
     * @param file CSV file
     * @return Parsed matrix as 2D int array
     * @throws IOException If reading file fails
     */
    public int[][] parseMatrix(MultipartFile file) throws IOException {
        List<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int expectedCols = -1;
            while ((line = br.readLine()) != null) {
                int[] row = Arrays.stream(line.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

                if (expectedCols == -1) {
                    expectedCols = row.length;
                } else if (row.length != expectedCols) {
                    throw new IllegalArgumentException("Matrix is not square (number of rows and columns are not same).");
                }

                rows.add(row);
            }

            if (rows.size() != expectedCols) {
                throw new IllegalArgumentException("Matrix is not square (number of rows and columns are not same).");
            }

            return rows.toArray(new int[0][]);
        }
    }

    /**
     * Validates the matrix is square and returns it as-is in matrix format.
     *
     * @param file CSV file containing matrix
     * @return Echoed matrix as a String
     */
    public String echo(MultipartFile file) throws Exception {
        int[][] matrix = parseMatrix(file);
        return formatMatrix(matrix);
    }

    /**
     * Transposes the matrix (i.e., switches rows and columns).
     *
     * @param file CSV file containing matrix
     * @return Transposed matrix as a String
     */
    public String invert(MultipartFile file) throws Exception {
        int[][] matrix = parseMatrix(file);
        int n = matrix.length;
        int[][] inverted = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                inverted[j][i] = matrix[i][j];

        return formatMatrix(inverted);
    }

    /**
     * Flattens the matrix to a single comma-separated line.
     *
     * @param file CSV file containing matrix
     * @return Flattened matrix as a String
     */
    public String flatten(MultipartFile file) throws Exception {
        int[][] matrix = parseMatrix(file);
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    /**
     * Sums all integers in the matrix.
     *
     * @param file CSV file containing matrix
     * @return Sum of matrix elements as a String
     */
    public int sum(MultipartFile file) throws Exception {
        int[][] matrix = parseMatrix(file);
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    /**
     * Multiplies all integers in the matrix.
     *
     * @param file CSV file containing matrix
     * @return Product of matrix elements as a String
     */
    public int multiply(MultipartFile file) throws Exception {
        int[][] matrix = parseMatrix(file);
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .reduce(1, (a, b) -> a * b);
    }

    /**
     * Converts a matrix to a String with each row on a new line.
     *
     * @param matrix Matrix to convert
     * @return String representation
     */
    private String formatMatrix(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(row -> Arrays.stream(row)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
    }
}
