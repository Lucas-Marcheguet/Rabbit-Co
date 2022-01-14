package com.lucasmarch.rabbitco;

public class Hungry implements IHunger {
    @Override
    public IHunger becomeHungry() {
        return this;
    }

    @Override
    public IHunger becomeSatiated() {
        return new Satiated();
    }
}
