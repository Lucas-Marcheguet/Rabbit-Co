package com.lucasmarch.rabbitco;

public class Satiated implements IHunger{
    @Override
    public IHunger becomeHungry() {
        return new Hungry();
    }

    @Override
    public IHunger becomeSatiated() {
        return this;
    }
}
