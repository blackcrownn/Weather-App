package com.folksdev.weather.controller;

import com.folksdev.weather.controller.validation.CityNameConstraint;
import com.folksdev.weather.dto.WeatherDto;
import com.folksdev.weather.service.WeatherService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
@Validated
@Tag(name = "open weather apı v1", description = "open weather apı for fitering by city for current temparature")
public class WeatherApi {
    private final WeatherService weatherService;

    public WeatherApi(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(
            method = "GET",
            summary = "open weather api summary",
            description = "open weather apı for fitering by city for current temparature",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = WeatherDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "BAD request",
                            content = @Content(schema = @Schema(hidden = true))
                    )
            }
    )
    @RateLimiter(name = "basic")
    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(@PathVariable("city") @CityNameConstraint @NotBlank String city) {
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }


}
