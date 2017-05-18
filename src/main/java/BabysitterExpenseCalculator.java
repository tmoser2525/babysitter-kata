import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculator {
    private static final int BEFORE_BEDTIME_HOURLY_RATE = 12;
    private final List<BabysittingExpenseCalculatorValidator> validators;

    public BabysitterExpenseCalculator() {
        this.validators = Arrays.asList(new BusinessHoursValidator(), new TimesInOrderValidator(), new MaximumWorkLengthValidator());
    }

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime bedTime) {
        validators.forEach(validator -> validator.validateTimes(startTime, endTime));

        int beforeBedtimeExpense = (int)startTime.until(bedTime, ChronoUnit.HOURS) * BEFORE_BEDTIME_HOURLY_RATE;
        return beforeBedtimeExpense;
    }
}

