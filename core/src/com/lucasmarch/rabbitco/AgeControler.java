package com.lucasmarch.rabbitco;

public class AgeControler implements IEventAge {

    private Animal animal;
    private int age;
    private IAge ageState;

    public AgeControler(Animal animal){
        this.animal = animal;
        ageState = new Baby();
    }

    @Override
    public void grow() {
        this.age +=1;
        if(this.age >= animal.getAgeMaturity()){
            changeState(ageState.becomeAdult());
        }
    }

    public boolean isBaby(){
        return this.ageState instanceof Baby;
    }

    public void changeState(IAge ageS){
        this.ageState = ageS;
    }
}
