package edu.gu.hajo.wss.core;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Shop;

/**
 
 * A wrapper for the shop. We should only have
 * one shop (better use CDI more later)
 * @author hajo
 */
 
public enum SingletonShop {

    INSTANCE;
    
    private final IShop shop = Shop.newInstance();

    public IShop getShop() {
        return shop;
    }

}
