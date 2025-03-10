package org.novak.java.model.workspace;

public enum WorkspaceType {

    OPEN_SPACE("Public workspace"),
    PRIVATE("Private workspace"),
    CABIN("Cabin workspace");

    private final String description;

    WorkspaceType(String description) {
        this.description = description;
    }

    public String getWorkspaceType() {
        return description;
    }
}
