package com.tplogistics.core.service.impl;

import com.tplogistics.controller.dto.request.create.RouteCreateRequest;
import com.tplogistics.core.domain.entity.Route;
import com.tplogistics.core.error_handling.custom_error.InvalidRequest;
import com.tplogistics.core.error_handling.custom_error.NotFoundException;
import com.tplogistics.core.service.LocationService;
import com.tplogistics.core.service.RouteService;
import com.tplogistics.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final LocationService locationService;

    @Override
    public UUID createRoute(RouteCreateRequest request) {
        UUID fromLocationUUID = UUID.fromString(request.getFromLocationId());
        UUID toLocationUUID = UUID.fromString(request.getToLocationId());

        var fromLocation = locationService.findLocation(fromLocationUUID);
        var toLocation = locationService.findLocation(toLocationUUID);

        Route route = Route.builder()
                .fromLocation(fromLocation)
                .toLocation(toLocation)
                .length(request.getLength())
                .tripBasedCost(request.getTripBasedCost())
                .tonBasedLimit(request.getTonBasedLimit())
                .isEnabled(true)
                .build();
        Route result = routeRepository.save(route);
        return result.getRouteId();
    }

    @Override
    public Route findRoute(UUID id) {
        var result = routeRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Route not found");
        }
        return result.get();
    }

    @Override
    public List<Route> findRouteByLocationName(String fromLocationNameKeyword, String toLocationNameKeyword) {
        if (fromLocationNameKeyword.isBlank() && toLocationNameKeyword.isBlank()) {
            throw new InvalidRequest("Invalid location name");
        }

        List<Route> routes = new ArrayList<>();

        if (fromLocationNameKeyword.isBlank()) { // Find by toLocationName keyword
            routes = routeRepository.findByIgnoreCaseToLocation_NameContaining(toLocationNameKeyword);
        }
        else if (toLocationNameKeyword.isBlank()) { // Find by fromLocationName keyword
            routes = routeRepository.findByIgnoreCaseFromLocation_NameContaining(fromLocationNameKeyword);
        }
        else { // Find by both
            routes = routeRepository.findByIgnoreCaseFromLocation_NameContainingAndIgnoreCaseToLocation_NameContaining(fromLocationNameKeyword, toLocationNameKeyword);
        }

        return routes;
    }
}
