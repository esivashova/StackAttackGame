package com.stackattack.modules;

import com.stackattack.StackAttackGame;

public interface Module {

    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = 1;

    public void load(StackAttackGame g, Module batch);
    public int run();
    public void unload();
}