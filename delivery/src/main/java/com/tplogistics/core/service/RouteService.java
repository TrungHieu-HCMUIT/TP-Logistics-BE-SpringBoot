package com.tplogistics.core.service;

import com.tplogistics.controller.dto.request.create.RouteCreateRequest;
import com.tplogistics.core.domain.entity.Route;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    UUID createRoute(RouteCreateRequest request);
    Route findRoute(UUID id);
    List<Route> findRouteByLocationName(String fromLocationNameKeyword, String toLocationNameKeyword);
}
