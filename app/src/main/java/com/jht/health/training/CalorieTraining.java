package  com.jht.health.training;


public class CalorieTraining extends BaseTraining {
    private int mTargetCalorie = 0;

    public CalorieTraining() {
    }

    public CalorieTraining(int calorie) {
        setTargetCalorie(calorie);
    }

    private void setTargetCalorie(int calorie) {
        mTargetCalorie = calorie;
    }
}