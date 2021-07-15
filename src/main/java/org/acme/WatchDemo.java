package org.acme;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;

public class WatchDemo {
    private KubernetesClient client;

    public WatchDemo(KubernetesClient client) {
        this.client = client;
    }

    public Watch runWatch(Watcher<Pod> watcher) {
        return client.pods().inAnyNamespace().watch(watcher);
    }
}
