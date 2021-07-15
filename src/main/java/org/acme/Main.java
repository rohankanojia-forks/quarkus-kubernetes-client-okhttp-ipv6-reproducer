package org.acme;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.kubernetes.client.Watch;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;

import java.util.concurrent.TimeUnit;

@QuarkusMain
public class Main {

    public static void main(String ... args) {
        System.out.println("Running main method");
        testKubernetesClient();
    }

    private static void testKubernetesClient() {
        try (KubernetesClient client = new DefaultKubernetesClient()) {
            System.out.println("KubernetesClient Initialized [OK]");
            CreateDeployment c = new CreateDeployment(client);
            GetClusterVersion g = new GetClusterVersion(client);
            ListPods l = new ListPods(client);
            WatchDemo w = new WatchDemo(client);

            VersionInfo v = g.versionInfo();
            System.out.printf("[OK] Kubernetes Version %s.%s\n", v.getMajor(), v.getMinor());
            c.createDeployment();
            System.out.println("[OK] Deployment Created");
            PodList podList = l.list();
            System.out.println("[OK] Pods listed in all namespaces");
            podList.getItems().forEach(p -> System.out.println(p.getMetadata().getNamespace() + "/" + p.getMetadata().getName()));
            System.out.println("[OK] Running Watch:");
            Watch watch = w.runWatch(new PodWatcher());
            TimeUnit.MINUTES.sleep(30);
        } catch (KubernetesClientException kubernetesClientException) {
            System.out.println("Kubernetes Exception : " + kubernetesClientException.getMessage());
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted!");
        }
    }
}
