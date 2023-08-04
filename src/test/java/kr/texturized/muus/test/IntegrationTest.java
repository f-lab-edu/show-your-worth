package kr.texturized.muus.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import kr.texturized.muus.Main;
import kr.texturized.muus.test.config.TestProfile;
import java.io.IOException;
import java.io.InputStream;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles(TestProfile.TEST)
@Transactional
public class IntegrationTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    protected ResourceLoader resourceLoader;
    protected final String identifier = "{class-name}/{method-name}";

    protected <T> T readValue(final String path, Class<T> clazz) throws IOException {
        final InputStream json = resourceLoader.getResource(path).getInputStream();
        return objectMapper.readValue(json, clazz);
    }

    protected String readJson(final String path) throws IOException {
        final InputStream inputStream = resourceLoader.getResource(path).getInputStream();
        final ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() {
                return inputStream;
            }
        };
        return byteSource.asCharSource(Charsets.UTF_8).read();
    }
}
