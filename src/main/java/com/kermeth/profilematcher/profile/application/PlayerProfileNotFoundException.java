package com.kermeth.profilematcher.profile.application;

public class PlayerProfileNotFoundException extends RuntimeException {
    public PlayerProfileNotFoundException(String playerId) {
        super("Player profile not found for player id: " + playerId);
    }
}
