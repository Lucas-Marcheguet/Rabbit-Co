package com.lucasmarch.rabbitco;

public class HungerControler implements IEventHunger {

    private int reserveE;
    private Animal animal;
    private IHunger hungerState;

    public HungerControler(Animal animal){
        this.hungerState = new Satiated();
        this.reserveE = animal.baseE;
        this.animal   = animal;
    }

    @Override
    public void eventEat() {
        this.reserveE += 1;
        if(this.reserveE >= animal.tresholdE){
            changeState(hungerState.becomeSatiated());
        }
    }

    @Override
    public void eventNoEat() {
        this.reserveE -= 1;
        if(this.reserveE < animal.tresholdE){
            changeState(hungerState.becomeHungry());
        }
        if(this.reserveE <= 0) {
            animal.die();
        }
    }

    public boolean isHungry(){ return this.hungerState instanceof Hungry; }

    public void changeState(IHunger state){
        this.hungerState = state;
    }

    public int getReserveE(){
        return reserveE;
    }
}
