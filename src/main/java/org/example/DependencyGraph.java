package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DependencyGraph {
    private final Map<String, Set<String>> adjacency = new HashMap<>();

    public boolean addEdge(String from, String to) {
        return adjacency.computeIfAbsent(from, k -> new LinkedHashSet<>()).add(to);
    }

    public List<String> reachableFrom(String start) {
        List<String> order = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String next : adjacency.getOrDefault(current, Collections.<String>emptySet())) {
                if (visited.add(next)) {
                    order.add(next);
                    queue.add(next);
                }
            }
        }
        return order;
    }
}
