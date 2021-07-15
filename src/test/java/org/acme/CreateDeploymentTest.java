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

        // When
        Deployment deployment = client.apps().deployments()
                .inNamespace("default")
                .create(createDeployment());

        // Then
        assertNotNull(deployment);
        assertNotNull(deployment.getMetadata().getCreationTimestamp());
        assertEquals("nginx-deployment", deployment.getMetadata().getGenerateName());
        assertTrue(client.apps().deployments().inNamespace(deployment.getMetadata().getNamespace())
                .withName(deployment.getMetadata().getName())
                .delete());
    }

    private Deployment createDeployment() {
        return new DeploymentBuilder()
                .withNewMetadata().withGenerateName("nginx-deployment").addToLabels("app", "nginx").endMetadata()
                .withNewSpec()
                .withReplicas(3)
                .withNewSelector()
                .withMatchLabels(Collections.singletonMap("app", "nginx"))
                .endSelector()
                .withNewTemplate()
                .withNewMetadata().addToLabels("app", "nginx").endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName("nginx")
                .withImage("nginx:1.7.9")
                .addNewPort().withContainerPort(80).endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
    }
}
