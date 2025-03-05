package workspace;

public class Workspace {

    private int id;
    private double price;
    private WorkspaceType workspaceType;
    private boolean isAvailable;

    protected Workspace setId(int id) {
        this.id = id;
        return this;
    }

    protected int getId() {
        return id;
    }

    protected Workspace setPrice(double price) {
        this.price = price;
        return this;
    }

    protected Workspace setWorkspaceType(WorkspaceType workspaceType) {
        this.workspaceType = workspaceType;
        return this;
    }

    public Workspace setAvailability(boolean availability) {
        isAvailable = availability;
        return this;
    }

    protected boolean getAvailability() {
        return isAvailable;
    }

    @Override
    public String toString() {
        String availability = "Reserved";
        if (isAvailable) {
            availability = "Available";
        }

        return String.format("ID: %d, Type: %s, Price: %.2f, Availability: %s", id, workspaceType.getWorkspaceType(),
                price, availability);
    }
}