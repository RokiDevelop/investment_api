package com.kiryukhin.portfolio_service.system.exception;

public class AssetStockNotFoundException extends RuntimeException{
    public AssetStockNotFoundException(String message) {
        super(message);
    }
}
