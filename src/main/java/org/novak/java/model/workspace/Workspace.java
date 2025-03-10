package org.novak.java.model.workspace;

import java.io.Serializable;
import java.util.Objects;

public class Workspace implements Serializable {

    private int id;
    private double price;
    private WorkspaceType workspaceType;
    private boolean isAvailable;

    public Workspace(int id, double price, WorkspaceType workspaceType, boolean isAvailable) {
        this.id = id;
        this.price = price;
        this.workspaceType = workspaceType;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public WorkspaceType getWorkspaceType() {
        return workspaceType;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return id == workspace.id && Double.compare(price,
                workspace.price) == 0 && workspaceType == workspace.workspaceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, workspaceType);
    }

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", price=" + price +
                ", workspaceType=" + workspaceType +
                ", isAvailable=" + isAvailable +
                '}';
    }
}