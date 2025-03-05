package workspace;

enum WorkspaceType {

    OPEN_SPACE("Public workspace"),
    PRIVATE("Private workspace"),
    CABIN("Cabin workspace");

    private final String description;

    WorkspaceType(String description) {
        this.description = description;
    }

    String getWorkspaceType() {
        return description;
    }
}
