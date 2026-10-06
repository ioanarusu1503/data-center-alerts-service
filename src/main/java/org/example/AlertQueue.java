package org.example;

import java.util.PriorityQueue;

public class AlertQueue {

    private static class Entry {
        private final Alert alert;
        private final long sequence;

        private Entry(Alert alert, long sequence) {
            this.alert = alert;
            this.sequence = sequence;
        }
    }

    private final PriorityQueue<Entry> heap = new PriorityQueue<>((a, b) -> {
        int bySeverity = b.alert.getSeverity().compareTo(a.alert.getSeverity());
        if (bySeverity != 0) {
            return bySeverity;
        }
        return Long.compare(a.sequence, b.sequence);
    });
    private long nextSequence = 0;

    public void add(Alert alert) {
        heap.add(new Entry(alert, nextSequence++));
    }

    public Alert poll() {
        Entry entry = heap.poll();
        return entry == null ? null : entry.alert;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
