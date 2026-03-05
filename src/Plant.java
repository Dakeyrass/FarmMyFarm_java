import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

public abstract class Plant {
    public String type;
    public double buyPrice;
    public double sellPrice;
    public String emoji;

    public abstract void growthDuration(Button btn);
}

class Mais extends Plant{
    public Mais(){
        this.type = "Mais";
        this.buyPrice = 50;
        this.sellPrice = 50;
        this.emoji = "\uD83C\uDF3D";
    }
    public void growthDuration(Button btn){
        btn.setStyle("-fx-background-color: brown");
        PauseTransition timelapse = new PauseTransition(Duration.seconds(3));
        timelapse.setOnFinished(e -> btn.setStyle("-fx-background-color : #e4ed6b;"));
        timelapse.play();
    }
}
class Potatoe extends Plant{
    public Potatoe(){
        this.type = "Potatoe";
        this.buyPrice = 50;
        this.sellPrice = 50;
        this.emoji = "\uD83E\uDD54";
    }
    public void growthDuration(Button btn){
        btn.setStyle("-fx-background-color: brown");
        PauseTransition timelapse = new PauseTransition(Duration.seconds(5));
        timelapse.setOnFinished(e -> btn.setStyle("-fx-background-color : orange"));
        timelapse.play();
    }
}