package com.kermeth.profilematcher.profile.domain;

public record Device(
        Integer id,
        String model,
        String carrier,
        String firmware
) {
}
