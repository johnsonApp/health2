package  com.jht.health.training;


public class CalorieTraining extends BaseTraining {

    private static final  float RUNNING_STYLE = 4.1f; //MPH ; MPH is 4MPH; 1 MPH = 1.6 km/h

    private long mTargetCalorie = 0;
    private static double mCalorieConsumed = 0;               // kcal/min

    public CalorieTraining() {
    }

    public CalorieTraining(int calorie) {
        setTargetCalorie(calorie);
    }

    public double calculateCalorieValue(int weight) {
        return calculateCalorieValue(getSpeed(), 1/*getIncline()*/, weight);
    }

    //calculate Calorie speed km/h ; incline ; weight kg;
    public static double calculateCalorieValue(long speed, int incline, int weight) {
        float speedMPH = speed / 1.6f;
        if(speedMPH >= RUNNING_STYLE) {
            mCalorieConsumed = ((speedMPH * 26.8 * 0.2) + (incline/100 * speedMPH * 26.8 * 0.9) + 3.5f) * weight * 0.005f;
        }else {
            mCalorieConsumed = ((speedMPH * 26.8 * 0.1) + (incline/100 * speedMPH * 26.8 * 1.8) + 3.5f) * weight * 0.005f;
        }
        return mCalorieConsumed;
    }

    public static double getCalorieConsumed () {
        return  mCalorieConsumed;
    }
    public void setTargetCalorie(long calorie) {
        mTargetCalorie = calorie;
    }
}