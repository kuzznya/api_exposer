package com.github.kuzznya.exposer.core;

import com.github.kuzznya.exposer.core.config.ExposerConfiguration;
import com.github.kuzznya.exposer.core.config.ExposerConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
public class ExposerConfig implements ExposerConfigurer {
    @Override
    public ExposerConfiguration configureExposer() {
        return ExposerConfiguration.builder()
                .bean("TestService2")
                    .route("/test")
                        .route("/v1")
                            .bean("TestService")
                            .endpoint(RequestMethod.GET, "getValue").and()
                            .endpoint(RequestMethod.POST, "setValue").register()
                            .and()
                        .route("/v2")
                            .endpoint(RequestMethod.GET, "setValue")
                                .bean("TestService").param("value", "?val")
                                .register()
                            .and()
                        .endpoint(RequestMethod.GET, "joinTwoArgs")
                            .param("arg1", "?val").param("arg2", "tst")
                            .register()
                        .route("/listsize")
                            .endpoint(RequestMethod.GET, "getListSize").register()
                            .add()
                        .add()
                .configure();
    }
}
