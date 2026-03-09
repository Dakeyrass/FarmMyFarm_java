public class FieldState {
    Plant plant;
    String status; //occupied/ready/null

    public FieldState(Plant plant){
        this.plant = plant;
        this.status = "occupied"; //quand on instancie Fieldstate il peut pas être à null vu que y'a une plante
    }
}
