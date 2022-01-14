package com.lucasmarch.rabbitco;

public class Adult implements IAge{

    @Override
    public IAge becomeBaby() {
        return new Baby();
    }

    @Override
    public IAge becomeAdult() {
        return this;
    }
}
