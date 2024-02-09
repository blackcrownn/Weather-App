package com.folksdev.weather.repository;
import com.folksdev.weather.model.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity,String> {
    Optional<WeatherEntity> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
