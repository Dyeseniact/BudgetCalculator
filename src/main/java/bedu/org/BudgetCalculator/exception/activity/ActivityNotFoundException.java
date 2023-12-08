package bedu.org.BudgetCalculator.exception.activity;

import bedu.org.BudgetCalculator.exception.RuntimeException;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(long activityId) {
        super("ERR_DATA_NOT_FOUND", "Activity not found with ID: ", activityId);
    }
}
