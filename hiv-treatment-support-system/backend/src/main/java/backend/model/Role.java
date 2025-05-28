package backend.model;

public enum Role {
    USER("USER", 1),
    ADMIN("ADMIN", 0);

    private final String description;
    private final int level;

    Role(String description, int level) {
        this.description = description;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }
}