import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tmoser on 5/17/17.
 */
public class BabysitterExpenseCalculator {
    private final List<BabysittingExpenseCalculatorValidator> validators;

    public BabysitterExpenseCalculator() {
        this.validators = Arrays.asList(new BusinessHoursValidator(), new TimesInOrderValidator(), new MaximumWorkLengthValidator());
    }

    public int calculateExpense(LocalDateTime startTime, LocalDateTime endTime) {
        validators.forEach(validator -> validator.validateTimes(startTime, endTime));
        return 0;
    }
}

