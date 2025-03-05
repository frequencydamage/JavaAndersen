package reservation;

import workspace.Workspace;

class Reservation {

    private int id;
    private Workspace workspace;

    public Reservation setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Reservation setWorkspace(Workspace workspace) {
        this.workspace = workspace;
        return this;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public String toString() {
        return String.format("Your reservation id: %d for a working space: %s", id, workspace.toString());
    }
}