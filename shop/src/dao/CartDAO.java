package dao;

import Utils.FileManager;
import Utils.InnputManger;
import vo.Cart;

import java.util.ArrayList;

public class CartDAO {
  ArrayList<Cart> cartList;
  InnputManger im;
  FileManager fm;

  public CartDAO(){
    cartList = new ArrayList<>();
    im = new InnputManger();
    fm = new FileManager();
  }

  public void addCartList(String id, String itemName){
    cartList.add(new Cart(id, itemName));
  }



}
