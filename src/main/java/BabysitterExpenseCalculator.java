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
    private static final int MIDNIGHT_TO_END_TIME_RATE = 16;
    private final List<BabysittingExpenseCalculatorValidator> validators;

    public BabysitterExpenseCalculator() {
        this.validators = Arrays.asList(new BusinessHoursValidator(), new TimesInOrderValidator(), new MaximumWorkLengthValidator());
    }

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime bedTime) {
        validators.forEach(validator -> validator.validateTimes(startTime, endTime));

        int beforeBedtimeExpense = calculateExpenseBeforeBedtime(startTime, bedTime);
        int bedtimeUntilMidnightExpense = calculateExpenseFromBedtimeUntilMidnight(bedTime, endTime);
        int midnightUntilEndTimeExpense = calculateExpenseFromMidnightUntilEndTime(bedTime, endTime);
        return beforeBedtimeExpense + bedtimeUntilMidnightExpense + midnightUntilEndTimeExpense;
    }

    private int calculateExpenseFromMidnightUntilEndTime(LocalDateTime bedTime, LocalDateTime endTime) {
        if (!isTimeBeforeMidnight(endTime)) {
            if(!isTimeBeforeMidnight(bedTime)) {
                return (int)bedTime.until(endTime, ChronoUnit.HOURS) * MIDNIGHT_TO_END_TIME_RATE;
            }
            return (int)previousMidnightFromDate(endTime).until(endTime, ChronoUnit.HOURS) * MIDNIGHT_TO_END_TIME_RATE;
        }
        return 0;
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

    private boolean isTimeBeforeMidnight(LocalDateTime time) {
        return time.getHour() >= BabysittingExpenseCalculatorValidator.START_TIME_CUTOFF.getHour();
    }

    private LocalDateTime nextMidnightFromDate(LocalDateTime date) {
        return LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth() + 1, 0, 0);
    }

    private LocalDateTime previousMidnightFromDate(LocalDateTime date) {
        return LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(), 0, 0);
    }

    private int calculateExpenseBeforeBedtime(LocalDateTime startTime, LocalDateTime bedTime) {
        return (int)startTime.until(bedTime, ChronoUnit.HOURS) * BEFORE_BEDTIME_HOURLY_RATE;
    }
}

