import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public abstract class Plant {
    public String type;
    public String emoji;
    public int quantity = 0;
    public int seeds = 5;

    public abstract void growthDuration(Button btn);
}
//ALL PLANTS
class Mais extends Plant{
    public Mais(){
        this.type = "Mais";
        this.quantity = 0;
        this.seeds = 5;
    }
    public void growthDuration(Button btn){
        btn.setStyle("-fx-background-color: brown");
        PauseTransition timelapse = new PauseTransition(Duration.seconds(3));
        timelapse.setOnFinished(e -> {
            btn.setStyle("-fx-background-color : #e4ed6b");
            btn.setText("\uD83C\uDF3D");
            ((FieldState) btn.getUserData()).status = "ready";
        });
        timelapse.play();
    }
}
class Potatoe extends Plant{
    public Potatoe(){
        this.type = "Potatoe";
        this.quantity = 0;
        this.seeds = 5;
    }
    public void growthDuration(Button btn){
            btn.setStyle("-fx-background-color: brown");
            PauseTransition timelapse = new PauseTransition(Duration.seconds(5));
            timelapse.setOnFinished(e -> {
                btn.setStyle("-fx-background-color : orange");
                btn.setText("\uD83E\uDD54\u200B");
                ((FieldState) btn.getUserData()).status = "ready";//on caste pour accéder à status sinon il veut pas (obj trop générique)
            });
            timelapse.play();
    }
}