package org.acme;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.Collections;

public class CreateDeployment {
    private KubernetesClient client;

    public CreateDeployment(KubernetesClient client) {
        this.client = client;
    }

    public Deployment createDeployment() {
        return client.apps().deployments()
                .inNamespace("default")
                .create(new DeploymentBuilder()
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
                        .build());
    }
}
