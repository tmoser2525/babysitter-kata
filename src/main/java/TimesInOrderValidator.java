import java.time.LocalDateTime;

/**
 * Created by tmoser on 5/18/17.
 */
class TimesInOrderValidator implements BabysittingExpenseCalculatorValidator {
    @Override
    public void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.compareTo(endTime) > 0) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }
}
