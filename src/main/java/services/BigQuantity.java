package services;

import logics.Fill;

public abstract class BigQuantity extends Fill {
    public abstract void fill();

    public void performBigQuantityFilling(){
        fill();
    }
}
