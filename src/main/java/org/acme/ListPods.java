package org.acme;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ListPods {
    private KubernetesClient client;

    public ListPods(KubernetesClient client) {
        this.client = client;
    }

    public PodList list() {
        return client.pods()
                .inAnyNamespace()
                .list();
    }
}
