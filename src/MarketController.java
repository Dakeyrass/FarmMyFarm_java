import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MarketController {
    @FXML
    private Label labelMaisMarket;
    @FXML
    private Label labelPotatoeMarket;
    @FXML
    private Label moneyLabelMarket;
    @FXML
    private Label labelPotatoeSeedM;
    @FXML
    private Label labelMaisSeedM;

    private Inventory inventory;

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        //on récupère les stocks/labels pour qu'ils s'affichent ici
        labelMaisMarket.setText("\uD83C\uDF3D Mais : " + inventory.maisSingleton.quantity);
        labelPotatoeMarket.setText("\uD83E\uDD54 Potatoe : " + inventory.potatoeSingleton.quantity);
        labelMaisSeedM.setText("\uD83C\uDF3D Mais: " + inventory.maisSingleton.seeds);
        labelPotatoeSeedM.setText("\uD83E\uDD54 Potatoe :" + inventory.potatoeSingleton.seeds);
        updateMoney();
    }
    private void updateMoney(){
        moneyLabelMarket.setText(String.valueOf(inventory.getMoney()));
    }

    //ALL BUYING METHODS
    public void buySeedMais(){
        if(inventory.getMoney() >= 3){
            inventory.maisSingleton.seeds += 1;
            labelMaisSeedM.setText("\uD83C\uDF3D Mais: " + inventory.maisSingleton.seeds);
            inventory.withdrawMoney(3);
            updateMoney();
        }
    }

    //ALL SELLING METHODS
    public void sellMais(){
        if(inventory.maisSingleton.quantity > 0){
            inventory.maisSingleton.quantity -= 1; //maj compteur
            labelMaisMarket.setText("\uD83C\uDF3D Mais : " + inventory.maisSingleton.quantity);//maj label
            inventory.addMoney(5); // prix du maïs
            updateMoney();//on met à jour le label
        }
    }
    public void sellPotatoe(){
        if(inventory.potatoeSingleton.quantity > 0){
            inventory.potatoeSingleton.quantity -= 1;
            labelPotatoeMarket.setText("\uD83E\uDD54 Potatoes : " + inventory.potatoeSingleton.quantity);
            inventory.addMoney(5);
            updateMoney();
        }
    }
}
