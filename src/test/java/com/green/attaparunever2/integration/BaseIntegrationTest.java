package com.green.attaparunever2.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Disabled  // 개별 실행 방지
@AutoConfigureMockMvc
@Transactional  // 테스트 후 롤백
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mvc;  // HTTP 요청을 테스트할 때 사용

    @Autowired
    protected ObjectMapper objectMapper;  // JSON 직렬화/역직렬화
}
