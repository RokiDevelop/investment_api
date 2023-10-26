package com.kiryukhin.portfolio_service.controllers.model.responses;

import java.util.List;
import java.util.Map;

public record ResponseGetPortfolioInfoDto(
        String username,
        String portfolio_id,
        List<String> assets
        ) {
}
