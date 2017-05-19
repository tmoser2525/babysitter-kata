import java.time.LocalDateTime;

/**
 * Created by tmoser on 5/18/17.
 */
class BusinessHoursValidator implements BabysittingExpenseCalculatorValidator {
    @Override
    public void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalTime().isBefore(START_TIME_CUTOFF) && startTime.toLocalTime().isAfter(END_TIME_CUTOFF)) {
            throw new IllegalArgumentException("Start time must be between 5pm and 4am");
        }
        if (endTime.toLocalTime().isBefore(START_TIME_CUTOFF) && endTime.toLocalTime().isAfter(END_TIME_CUTOFF)) {
            throw new IllegalArgumentException("End time must be between 5pm and 4am");
        }
    }
}
