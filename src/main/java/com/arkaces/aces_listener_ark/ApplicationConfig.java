package com.arkaces.aces_listener_qredit;

import qredit_java_client.*;
import com.arkaces.aces_server.aces_listener.config.AcesListenerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Import({AcesListenerConfig.class})
@Slf4j
public class ApplicationConfig {

    @Bean
    public QreditClient qreditClient(Environment environment) {
        QreditNetworkFactory qreditNetworkFactory = new QreditNetworkFactory();
        String qreditNetworkConfigPath = environment.getProperty("qreditNetworkConfigPath");
        qreditNetwork qreditNetwork = qreditNetworkFactory.createFromYml(qreditNetworkConfigPath);

        HttpQreditClientFactory httpQreditClientFactory = new HttpQreditClientFactory();

        log.info("Bootstrapping QREDIT client with network peers");
        return httpQreditClientFactory.create(qreditNetwork);
    }

    @Bean
    public Integer scanTransactionDepth(Environment environment) {
        return environment.getProperty("scanTransactionDepth", Integer.class);
    }

}
