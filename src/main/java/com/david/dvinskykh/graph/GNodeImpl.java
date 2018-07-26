package com.david.dvinskykh.graph;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.swing.text.html.Option;
import java.util.Optional;

public class GNodeImpl implements GNode {

    private String name;

    private GNode[] array;

    public GNodeImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GNode[] getChildren() {
        return Optional.ofNullable(array).orElse(new GNode[0]);
    }

    @Override
    public void setChildren(GNode[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return name;
    }
}
