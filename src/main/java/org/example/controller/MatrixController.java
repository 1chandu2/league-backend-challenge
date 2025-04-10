package org.example.controller;

import org.example.service.MatrixService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController(MatrixService matrixService) {
        this.matrixService = matrixService;
    }

    /**
     * Endpoint to echo the matrix from uploaded CSV file.
     * Returns the matrix in the same format as it was input.
     *
     * @param file CSV file containing a square matrix of integers
     * @return Echoed matrix as a String
     */
    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(matrixService.echo(file));
    }

    /**
     * Endpoint to invert (transpose) the matrix from uploaded CSV file.
     * Rows become columns and vice versa.
     *
     * @param file CSV file containing a square matrix of integers
     * @return Transposed matrix as a String
     */
    @PostMapping("/invert")
    public ResponseEntity<String> invert(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(matrixService.invert(file));
    }

    /**
     * Endpoint to flatten the matrix into a single comma-separated line.
     *
     * @param file CSV file containing a square matrix of integers
     * @return Flattened matrix as a comma-separated string
     */
    @PostMapping("/flatten")
    public ResponseEntity<String> flatten(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(matrixService.flatten(file));
    }

    /**
     * Endpoint to calculate the sum of all integers in the matrix.
     *
     * @param file CSV file containing a square matrix of integers
     * @return Sum of all matrix elements as a String
     */
    @PostMapping("/sum")
    public ResponseEntity<Integer> sum(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(matrixService.sum(file));
    }

    /**
     * Endpoint to calculate the product of all integers in the matrix.
     *
     * @param file CSV file containing a square matrix of integers
     * @return Product of all matrix elements as a String
     */
    @PostMapping("/multiply")
    public ResponseEntity<Integer> multiply(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(matrixService.multiply(file));
    }

}
