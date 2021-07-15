package org.acme;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetClusterVersionTest {
    @Test
    void testClientConnectsInIpv6Cluster() {
        // Given
        KubernetesClient client = new DefaultKubernetesClient();

        // When
        VersionInfo versionInfo = client.getVersion();

        // Then
        assertNotNull(versionInfo);
        assertEquals("1", versionInfo.getMajor());
        assertNotNull(versionInfo.getMinor());
    }
}
