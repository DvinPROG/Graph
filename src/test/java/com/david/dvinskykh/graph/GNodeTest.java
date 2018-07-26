package com.david.dvinskykh.graph;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

public class GNodeTest {

    @Test
    public void pathsCyclicDependence() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        a.setChildren(new GNode[]{b});
        b.setChildren(new GNode[]{a});
        List<List<GNode>> paths = GNodeUtils.paths(a);
        assertThat(paths, Matchers.contains(Arrays.asList(a, b, a)));
    }

    @Test
    public void pathsDirectDependency() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        GNode c = new GNodeImpl("C");
        a.setChildren(new GNode[]{b});
        b.setChildren(new GNode[]{c});
        List<List<GNode>> paths = GNodeUtils.paths(a);
        assertThat(paths, Matchers.contains(Arrays.asList(a, b, c)));
    }

    @Test
    public void pathsDestruction() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        GNode c = new GNodeImpl("C");
        GNode d = new GNodeImpl("D");
        a.setChildren(new GNode[]{b});
        b.setChildren(new GNode[]{c, d});
        List<List<GNode>> paths = GNodeUtils.paths(a);
        assertThat(paths, Matchers.contains(Arrays.asList(a, b, c), Arrays.asList(a, b, d)));
    }

    @Test
    public void pathsSingle() {
        GNode a = new GNodeImpl("A");
        List<List<GNode>> paths = GNodeUtils.paths(a);
        assertThat(paths, Matchers.contains(Collections.singletonList(a)));
    }

    @Test
    public void walkGraphDirectDependency() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        a.setChildren(new GNode[]{b});
        List<GNode> nodes = GNodeUtils.walkGraph(a);
        assertThat(nodes, IsIterableContainingInAnyOrder.containsInAnyOrder(a, b));
        assertThat(nodes, IsCollectionWithSize.hasSize(2));
    }

    @Test
    public void walkGraphCyclicDependence() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        GNode c = new GNodeImpl("C");
        GNode d = new GNodeImpl("D");
        a.setChildren(new GNode[]{b});
        b.setChildren(new GNode[]{c, d});
        c.setChildren(new GNode[]{a});
        List<GNode> nodes = GNodeUtils.walkGraph(a);
        assertThat(nodes, IsIterableContainingInAnyOrder.containsInAnyOrder(a, b, c, d));
        assertThat(nodes, IsCollectionWithSize.hasSize(4));
    }

    @Test
    public void walkGraphDestruction() {
        GNode a = new GNodeImpl("A");
        GNode b = new GNodeImpl("B");
        GNode c = new GNodeImpl("C");
        GNode d = new GNodeImpl("D");
        a.setChildren(new GNode[]{b});
        b.setChildren(new GNode[]{c, d});
        List<GNode> nodes = GNodeUtils.walkGraph(a);
        assertThat(nodes, IsIterableContainingInAnyOrder.containsInAnyOrder(a, b, c, d));
        assertThat(nodes, IsCollectionWithSize.hasSize(4));
    }

    @Test
    public void walkGraphSingle() {
        GNode a = new GNodeImpl("A");
        List<GNode> nodes = GNodeUtils.walkGraph(a);
        assertThat(nodes, IsIterableContainingInAnyOrder.containsInAnyOrder(a));
        assertThat(nodes, IsCollectionWithSize.hasSize(1));
    }
}