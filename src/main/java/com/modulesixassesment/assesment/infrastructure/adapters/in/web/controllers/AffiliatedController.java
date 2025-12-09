package com.modulesixassesment.assesment.infrastructure.adapters.in.web.controllers;

import com.modulesixassesment.assesment.domain.port.in.affiliated.RegisterAffiliatedUseCase;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.AffiliatedRequestDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto.AffiliatedResponseDTO;
import com.modulesixassesment.assesment.infrastructure.adapters.in.web.mapper.AffiliatedWebMapper;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/afiliated")
@RequiredArgsConstructor
@Tag(name = "Affiliates", description = "Operations related to managing affiliates")
public class AffiliatedController {

    private final RegisterAffiliatedUseCase registerAffiliatedUseCase;
    private final AffiliatedWebMapper affiliatedWebMapper;

    @Operation(
            summary = "Create a new affiliate",
            description = "Allows an administrator to register a new affiliate in the system",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Affiliate successfully created",
                            content = @Content(schema = @Schema(implementation = AffiliatedResponseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden: user does not have permissions",
                            content = @Content
                    )
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Timed(value = "example.endpoint.time", description = "Time response /affiliated")
    public ResponseEntity<AffiliatedResponseDTO> createAffiliated(
            @Valid @RequestBody AffiliatedRequestDTO request) {
        var affiliated = registerAffiliatedUseCase.save(affiliatedWebMapper.toModel(request));
        return ResponseEntity.ok(affiliatedWebMapper.toDto(affiliated));
    }

}
