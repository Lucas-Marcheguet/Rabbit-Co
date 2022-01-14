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
        if(this.age == animal.getAgeMaturity()){
            ageState.becomeAdult();
        }
    }

    public int getAge(){
        return this.age;
    }
}
