package com.codingshuttle.youtube.hospitalManagement.controller;

import com.codingshuttle.youtube.hospitalManagement.dto.PatientResponseDto;
import com.codingshuttle.youtube.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * AdminController is responsible for handling HTTP requests related to administrative actions,
 * such as retrieving the list of patients.
 */
@RestController // Marks this class as a RESTful web controller (returns JSON/XML instead of HTML views)
@RequestMapping("/admin") // All endpoints in this controller will start with "/admin"
@RequiredArgsConstructor // Generates a constructor for all final fields (dependency injection)
public class AdminController {
        //http://localhost:8080/api/v1/admin/patients?page=0&size=10
    // Injecting the PatientService to handle business logic for patient operations
    private final PatientService patientService;

    /**
     * Retrieves a paginated list of all patients.
     *
     * @param pageNumber The page number to retrieve (default: 0)
     * @param pageSize   The number of records per page (default: 10)
     * @return ResponseEntity containing the list of patients
     */
    @GetMapping("/patients") // Handles GET requests at "/admin/patients"
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber, // Request parameter for pagination page number
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize    // Request parameter for pagination size
    ) {
        // Fetches all patients from the service layer and returns them in the HTTP response
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }
}
