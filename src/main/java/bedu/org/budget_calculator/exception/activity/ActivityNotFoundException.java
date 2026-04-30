package bedu.org.budget_calculator.exception.activity;

import bedu.org.budget_calculator.exception.ResourceNotFoundException;

public class ActivityNotFoundException extends ResourceNotFoundException {

    public ActivityNotFoundException(long activityId) {
        super("Activity", activityId);
    }
}

