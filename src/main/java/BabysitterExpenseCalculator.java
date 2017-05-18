import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculator {

    private static final LocalTime START_TIME_CUTOFF = LocalTime.of(17, 0);
    private static final LocalTime END_TIME_CUTOFF = LocalTime.of(4, 0);

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalTime().isBefore(START_TIME_CUTOFF) && startTime.toLocalTime().isAfter(END_TIME_CUTOFF)) {
            throw new IllegalArgumentException("Start time must be between 5pm and 4am");
        }
        if (endTime.toLocalTime().isBefore(START_TIME_CUTOFF) && endTime.toLocalTime().isAfter(END_TIME_CUTOFF)) {
            throw new IllegalArgumentException("End time must be between 5pm and 4am");
        }
        return 0;
    }
}
