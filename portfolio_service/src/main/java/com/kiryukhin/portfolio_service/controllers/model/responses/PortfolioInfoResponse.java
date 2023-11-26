package com.kiryukhin.portfolio_service.controllers.model.responses;

import java.util.List;

public interface PortfolioInfoResponse {

    record GetPortfolioInfoResponse(
            String username,
            String portfolio_id,
            List<String> assets) {
    }
}
