package springboot.data.entities;

import java.io.Serializable;

public class UserConnectionId implements Serializable {
    String userId;
    String providerId;
    int rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserConnectionId)) return false;

        UserConnectionId that = (UserConnectionId) o;

        if (rank != that.rank) return false;
        if (!providerId.equals(that.providerId)) return false;
        if (!userId.equals(that.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result += 31 * result + providerId.hashCode();
        result += 31 * result + rank;
        return result;
    }
}

