import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculator {
    private static final int BEFORE_BEDTIME_HOURLY_RATE = 12;
    private static final int BEDTIME_TO_MIDNIGHT_RATE = 8;
    public static final int HOURS_PER_DAY = 24;
    private final List<BabysittingExpenseCalculatorValidator> validators;

    public BabysitterExpenseCalculator() {
        this.validators = Arrays.asList(new BusinessHoursValidator(), new TimesInOrderValidator(), new MaximumWorkLengthValidator());
    }

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime bedTime) {
        validators.forEach(validator -> validator.validateTimes(startTime, endTime));

        int beforeBedtimeExpense = calculateExpenseBeforeBedtime(startTime, bedTime);
        int bedtimeUntilMidnightExpense = calculateExpenseFromBedtimeUntilMidnight(bedTime, endTime);
        return beforeBedtimeExpense + bedtimeUntilMidnightExpense;
    }

    private int calculateExpenseFromBedtimeUntilMidnight(LocalDateTime bedTime, LocalDateTime endTime) {
        if (isTimeBeforeMidnight(bedTime)) {
            if(isTimeBeforeMidnight(endTime)) {
                return (int)bedTime.until(endTime, ChronoUnit.HOURS) * BEDTIME_TO_MIDNIGHT_RATE;
            }
            return (int)bedTime.until(nextMidnightFromDate(bedTime), ChronoUnit.HOURS) * BEDTIME_TO_MIDNIGHT_RATE;
        }
        return 0;
    }

    private boolean isTimeBeforeMidnight(LocalDateTime bedTime) {
        return bedTime.getHour() < HOURS_PER_DAY && bedTime.getHour() >= BabysittingExpenseCalculatorValidator.START_TIME_CUTOFF.getHour();
    }

    private LocalDateTime nextMidnightFromDate(LocalDateTime bedTime) {
        return LocalDateTime.of(bedTime.getYear(),bedTime.getMonth(),bedTime.getDayOfMonth() + 1, 0, 0);
    }

    private int calculateExpenseBeforeBedtime(LocalDateTime startTime, LocalDateTime bedTime) {
        return (int)startTime.until(bedTime, ChronoUnit.HOURS) * BEFORE_BEDTIME_HOURLY_RATE;
    }
}

