package services;

import logics.Fill;


public abstract class SmallQuantity extends Fill {
    public abstract void fill();
    public void performSmallQuantityFilling(){
        fill();
    }
}
