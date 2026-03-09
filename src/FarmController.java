import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.animation.PauseTransition;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FarmController {
    @FXML
    private GridPane land;
    @FXML
    private Plant selectedPlant;
    @FXML
    private Label labelMais;
    @FXML
    private Label labelPotatoe;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label labelMaisSeed;
    @FXML
    private Label labelPotatoeSeed;

    private Inventory vgtblInventory;
    private Plant maisSingleton = new Mais();
    private Plant potatoeSingleton = new Potatoe();

    //choix des légumes à planter
    @FXML
    public void chooseMais() {
        selectedPlant = maisSingleton;
    }

    @FXML
    public void choosePotatoe() {
        selectedPlant = potatoeSingleton;
    }

    //initialize
    @FXML
    public void initialize() {
        vgtblInventory = new Inventory(labelMais, labelPotatoe,labelMaisSeed, labelPotatoeSeed, maisSingleton, potatoeSingleton);
        moneyLabel.setText(String.valueOf(vgtblInventory.getMoney()));//on affiche l'argent au démarrage
        labelMaisSeed.setText("\uD83C\uDF3D Mais: " + maisSingleton.seeds);//on affiche la quantité de seeds au démarrage
        labelPotatoeSeed.setText("\uD83E\uDD54 Potatoe: " + potatoeSingleton.seeds);
        addPlant();
    }

    @FXML
    public void addPlant(){
        //on change l'etat des fields trouvés dans le gridpane land
        for (Node node : land.getChildren()){
            if (node instanceof Button){ //vérification si le node est un btn
                Button btn = (Button) node; //si le node est bien un bouton on le traite comme tel et stocke le dans btn
                btn.setOnAction(event -> {
                    Object stockBtn = btn.getUserData(); //on le stocke
                    if (selectedPlant != null && stockBtn == null && selectedPlant.seeds > 0) {//stockage de l'état sur le bouton directement
                        btn.setUserData(new FieldState(selectedPlant));//on stocke le new State de selectedPlant dans le btn
                        selectedPlant.seeds -= 1;
                        vgtblInventory.updateSeeds();
                        selectedPlant.growthDuration(btn);
                    }
                    if(stockBtn instanceof FieldState fstate && "ready".equals(fstate.status)){
                        harvestPlant(btn);
                    }
                });
            }
        }
    }

    @FXML
    public int harvestPlant(Button btn){
        FieldState fstate = (FieldState) btn.getUserData();//on stocke dans une variable locale et on caste
        vgtblInventory.updateInventory(fstate.plant);//on incrémente la quantity ici
        btn.setStyle("-fx-background-color : green");
        btn.setText(" ");
        btn.setUserData(null);
        return fstate.plant.quantity;
    }

    @FXML
    private void openMarket(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/market.fxml"));
            Parent root = loader.load();

            MarketController mController = loader.getController();//on recup MarketController
            mController.setInventory(vgtblInventory);//on lui transmet vgtblInventory

            Stage stage = new Stage();
            stage.setTitle("Market");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            //quand le market se ferme on rafraichit les labels
            stage.setOnHidden(e -> {
                labelMais.setText("\uD83C\uDF3D Mais : " + maisSingleton.quantity);
                labelPotatoe.setText("\uD83E\uDD54 Potatoe : " + potatoeSingleton.quantity);
                moneyLabel.setText(String.valueOf(vgtblInventory.getMoney()));
                labelMaisSeed.setText("\uD83C\uDF3D Mais: " + maisSingleton.seeds);
                labelPotatoeSeed.setText("\uD83E\uDD54 Potatoe: " + potatoeSingleton.seeds);
            });
            stage.show();
        } catch (Exception error){
            error.printStackTrace();//affiche l'erreur dans la console
        }
    }

}
