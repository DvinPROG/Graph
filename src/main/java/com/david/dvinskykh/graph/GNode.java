package com.david.dvinskykh.graph;

public interface GNode {
    String getName();

    GNode[] getChildren();

    void setChildren(GNode[] array);
}
