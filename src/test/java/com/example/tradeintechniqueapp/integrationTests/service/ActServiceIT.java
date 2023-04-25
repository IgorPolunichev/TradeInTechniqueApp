package com.example.tradeintechniqueapp.integrationTests.service;

import com.example.tradeintechniqueapp.integrationTests.annotation.IT;
import com.example.tradeintechniqueapp.service.ActService;
import lombok.RequiredArgsConstructor;

@IT
@RequiredArgsConstructor
public class ActServiceIT {

    private final ActService actService;
}
