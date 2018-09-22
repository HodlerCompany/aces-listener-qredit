package com.arkaces.aces_listener_qredit;

import qredit_java_client.QreditClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class QreditPeerUpdateWorker {

    private final QreditClient qreditClient;

    @Scheduled(initialDelay = 3600000, fixedDelay = 3600000)
    public void updatePeers() {
        log.info("Updating Qredit client peers " + ZonedDateTime.now(ZoneOffset.UTC));
        qreditClient.updatePeers();
    }

}
