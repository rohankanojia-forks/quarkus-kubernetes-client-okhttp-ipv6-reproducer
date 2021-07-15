package org.acme;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;

public class GetClusterVersion {
    private KubernetesClient client;

    public GetClusterVersion(KubernetesClient client) {
        this.client = client;
    }

    public VersionInfo versionInfo() {
        return client.getVersion();
    }
}
