package services;

import logics.Fill;
import logics.FillingTable;

public abstract class SmallQuantity extends Fill {
    public abstract void fill();
    public void performSmallQuantityFilling(){
        fill();
    }
}
