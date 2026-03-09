import javafx.scene.control.Label;

public class Inventory {
    private Label labelMais;
    private Label labelPotatoe;
    public Plant maisSingleton;
    public Plant potatoeSingleton;
    private Label labelMaisSeed;
    private Label labelPotatoeSeed;
    public int money = 20;

    public Inventory(Label labelMais, Label labelPotatoe, Label labelMaisSeed, Label labelPotatoeSeed, Plant maisSingleton, Plant potatoeSingleton){
        this.labelMais = labelMais;
        this.labelPotatoe = labelPotatoe;
        this.labelMaisSeed = labelMaisSeed;
        this.labelPotatoeSeed = labelPotatoeSeed;
        this.maisSingleton = maisSingleton;
        this.potatoeSingleton = potatoeSingleton;
    }

    public void updateInventory(Plant plant) {
        plant.quantity += 1;
        if (plant.type.equals("Mais")) {
            labelMais.setText("Mais : " + plant.quantity);
        } else if (plant.type.equals("Potatoe")) {
            labelPotatoe.setText("Potatoe : " + plant.quantity);
        }
    }
    public void updateSeeds(){
        labelMaisSeed.setText("Mais: " + maisSingleton.seeds);
        labelPotatoeSeed.setText("Potatoe: " + potatoeSingleton.seeds);
    }

    public int getMoney(){
        return money;
    }
    public void addMoney(int amount){
        money += amount;
    }
    public void withdrawMoney(int amount){
        money -= amount;
    }
}
