package com.lucasmarch.rabbitco;

public class Baby implements IAge{

    @Override
    public IAge becomeBaby() {
        return this;
    }

    @Override
    public IAge becomeAdult() {
        return new Adult();
    }
}
