import java.time.LocalDateTime;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculator {

    private static final int START_TIME_CUTOFF = 17;

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.getHour() < START_TIME_CUTOFF) {
            throw new IllegalArgumentException("Start time must be later than 5pm");
        }
        return 0;
    }
}
