package kr.texturized.muus.application.service;

import kr.texturized.muus.infrastructure.mapper.BuskingViewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for busking search.
 */
@Service
@RequiredArgsConstructor
public class BuskingViewService {

    private final BuskingViewMapper buskingViewMapper;
}
