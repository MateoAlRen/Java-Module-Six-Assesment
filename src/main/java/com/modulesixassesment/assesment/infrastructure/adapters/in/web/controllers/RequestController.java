package com.modulesixassesment.assesment.infrastructure.adapters.in.web.controllers;

import com.modulesixassesment.assesment.aplication.usecases.request.CreateRequestService;
import com.modulesixassesment.assesment.domain.port.in.evaluation.EvaluationRequestUseCase;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.RequestRequestDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.RequestResponseDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper.RequestWebMapper;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/requests")
@Tag(name = "Requests", description = "Operations related to creating and evaluating requests")
public class RequestController {

    private final CreateRequestService registerRequest;
    private final EvaluationRequestUseCase evaluationRequestUseCase;
    private final RequestWebMapper requestWebMapper;

    @Operation(
            summary = "Create a new request",
            description = "Allows an affiliate or admin to create a new request in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{ \"id\": 1, \"description\": \"Request description\", \"status\": \"PENDING\" }"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden: user does not have permissions",
                            content = @Content
                    )
            }
    )
    @Timed(value = "example.endpoint.time", description = "Time response /requests")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_AFFILIATED') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<RequestResponseDTO> createRequest(
            @RequestBody RequestRequestDTO request
    ) {
        var req = registerRequest.save(requestWebMapper.toModel(request));
        return ResponseEntity.ok(requestWebMapper.toResponse(req));
    }

    @Operation(
            summary = "Evaluate a request",
            description = "Allows an analyst or admin to evaluate an existing request",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request evaluated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RequestResponseDTO.class),
                                    examples = @ExampleObject(
                                            value = "{ \"id\": 1, \"description\": \"Request description\", \"status\": \"APPROVED\" }"
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Request not found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden: user does not have permissions",
                            content = @Content
                    )
            }
    )
    @Timed(value = "example.endpoint.time", description = "Time response /evaluate")
    @PostMapping("/{id}/evaluate")
    @PreAuthorize("hasRole('ROLE_ANALYST') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<RequestResponseDTO> evaluateRequest(@PathVariable Long id) {
        var req = evaluationRequestUseCase.evaluate(id);
        return ResponseEntity.ok(requestWebMapper.toResponse(req));
    }
}

