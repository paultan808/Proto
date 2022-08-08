package gov.nyc.assessment.domain;

public class UserId {
    private final String userId;

    public UserId(String userId) {
        this.userId = userId;
    }

    public UserId(long unsignedUserId) {
        this.userId = Long.toUnsignedString(unsignedUserId);
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return userId;
    }

    @Override
    public int hashCode() {
        return 159 + this.userId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserId && this.userId.equals(((UserId) o).userId);
    }
}
