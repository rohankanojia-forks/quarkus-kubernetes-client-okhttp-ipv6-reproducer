package org.acme;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateDeploymentTest {
    @Test
    void testCreateDeployment() {
        // Given
        KubernetesClient client = new DefaultKubernetesClient();
        CreateDeployment c = new CreateDeployment(client);

        // When
        Deployment deployment = c.createDeployment();

        // Then
        assertNotNull(deployment);
        assertNotNull(deployment.getMetadata().getCreationTimestamp());
        assertEquals("nginx-deployment", deployment.getMetadata().getGenerateName());
        assertTrue(client.apps().deployments().inNamespace(deployment.getMetadata().getNamespace())
                .withName(deployment.getMetadata().getName())
                .delete());
    }

}
