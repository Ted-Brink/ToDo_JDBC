// Vet inte om sequensern behövs

package se.tedbrink.data;

public class PersonSequencer {

    private static int personId = 0;

    public static int nextPersonId() {
        return ++personId;
    }

    static void reset() {
        personId = 0;
    }
}