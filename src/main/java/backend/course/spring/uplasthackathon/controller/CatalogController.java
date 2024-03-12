package backend.course.spring.uplasthackathon.controller;

import backend.course.spring.uplasthackathon.dto.request.CatalogRequest;
import backend.course.spring.uplasthackathon.dto.response.CatalogResponse;
import backend.course.spring.uplasthackathon.entity.Catalog;
import backend.course.spring.uplasthackathon.exception.handler.ExceptionResponse;
import backend.course.spring.uplasthackathon.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @PostMapping("/create")
    @Operation(summary = "Admin and Employee endpoint", description = "Для создания каталога!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "400", description = "Catalog already exist exception!"
                    )
            })
    public String createCatalog(@RequestBody CatalogRequest request) {
        return catalogService.createCatalog(request);
    }

    @GetMapping("/all")
    public List<Catalog> getAll() {
        return catalogService.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Admin and Employee endpoint", description = "Для создания каталога!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "404", description = "Catalog not found!"
                    )
            })
    public CatalogResponse getById(@PathVariable Long id) {
        return catalogService.getCatalogById(id);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Admin and Employee endpoint", description = "Для обновления данных каталога!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "404", description = "Catalog not found!"
                    )
            })
    public String updateById(@PathVariable Long id, @RequestBody CatalogRequest request) {
        return catalogService.updateCatalogById(id, request);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Admin and Employee endpoint", description = "Для удаления каталога!",
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "string"),
                            responseCode = "200", description = "Good"),
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionResponse.class)),
                            responseCode = "404", description = "Catalog not found!"
                    )
            })
    public String deleteById(@PathVariable Long id) {
        return catalogService.deleteCatalogById(id);
    }
}
