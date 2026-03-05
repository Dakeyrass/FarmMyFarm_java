import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class FarmController {
    @FXML
    private GridPane land;
    @FXML
    public String typePlant;
    @FXML
    public void initialize() { // ✅ initialize() remplace le constructeur
        setTypePlant("Potatoe");
        addPlant();
    }
    @FXML
    public void setTypePlant(String typePlant) {
        this.typePlant = typePlant;
    }
    @FXML
    public void addPlant(){
        //on change la couleur des fields trouvés dans le gridpane land
        for(Node node : land.getChildren()){
            if(node instanceof Button){ //vérification si le node est un btn
                Button btn = (Button) node; //on transforme le node en btn
                btn.setOnAction(event -> {
                    if(typePlant.equals("Mais")){
                        Plant plant = new Mais();
                        plant.growthDuration(btn);
                    } else if(typePlant.equals("Potatoe")){
                        Plant plant = new Potatoe();
                        plant.growthDuration(btn);
                    }
                });
            }
        }
    }
}
