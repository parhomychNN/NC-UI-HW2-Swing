package book;

public enum AgeRestriction {
    PLUS7("7+"), PLUS12("12+"), PLUS16("16+"), PLUS18("18+");

    private String description;

    AgeRestriction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
