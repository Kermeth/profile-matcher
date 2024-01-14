package com.kermeth.profilematcher.model.player.profile;

public record Device(
        String id,
        String model,
        String carrier,
        String firmware
) {
}
