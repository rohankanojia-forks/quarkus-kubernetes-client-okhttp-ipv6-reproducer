package org.acme;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;

public class PodWatcher implements Watcher<Pod> {
    @Override
    public void eventReceived(Action action, Pod pod) {
        System.out.println(action.name() + " " + pod.getMetadata().getName() + "/" + pod.getMetadata().getNamespace());
    }

    @Override
    public void onClose(WatcherException e) {
        System.out.println("Error : " + e.getMessage());
    }
}
