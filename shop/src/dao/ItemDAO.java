package dao;

import Utils.FileManager;
import Utils.InnputManger;
import vo.Cart;
import vo.Item;

import java.util.ArrayList;

public class ItemDAO {
  ArrayList<Item> itemList;
  InnputManger im;
  FileManager fm;

  public ItemDAO(){
    itemList = new ArrayList<>();
    im = new InnputManger();
    fm = new FileManager();
  }

  public void addItemFromData(String data){
    if(data.isEmpty()) return;
    String[] temp = data.split("\n");
    for (int i = 0; i < temp.length; i++) {
      String[] info = temp[i].split("/");
      itemList.add(new Item(info[0], Integer.parseInt(info[1]), info[2]));
    }
  }

  public String saveAsFileData(){
    if(itemList.isEmpty()) return "";
    String data = "";
    for (Item i : itemList) {
      data += i.savetoData();
    }
    return data;
  }

  public void addOneItem(){
    String NewName = im.getStringValue("[추가]이름");
    Item i = getOneItembyName(NewName);
    if(i != null){
      System.out.println("중복된 아이템입니다");
      return;
    }
    int NewPrice = im.getIntValue("[추가]가격", 100, 100000);
    String NewCategory = im.getStringValue("[추가]분류");
    itemList.add(new Item(NewName, NewPrice, NewCategory));
    System.out.println("[아이템 추가완료!]");
  }

  public Item getOneItembyName(String name){
    if(itemList.isEmpty()) return null;
    for (Item i : itemList) {
      if(i.getName().equals(name)){
        return i;
      }
    }
    return null;
  }

  public void deleteOneItem(){
    String deleteName = im.getStringValue("[삭제]이름");
    Item i = getOneItembyName(deleteName);
    if(i == null){
      System.out.println("없는 아이템입니다");
      return;
    }
    itemList.remove(getItemIdxbyName(i));
    System.out.println("[ 아이템 삭제 완료! ]");
  }

  int getItemIdxbyName(Item item){
    if(itemList.isEmpty()) return -1;
    for (Item i : itemList) {
      if(i.getName().equals(item.getName())){
        return itemList.indexOf(item);
      }
    }
    return -1;
  }

  public void changePrice(){
    String Name = im.getStringValue("[수정]이름");
    Item i = getOneItembyName(Name);
    if(i == null){
      System.out.println("없는 아이템입니다");
      return;
    }
    int changePrice = im.getIntValue("[수정]가격", 100, 100000);
    i.setPrice(changePrice);
    System.out.println("[ " + i + " 변경완료!]");
  }

  public void changeCategory(){
    String Name = im.getStringValue("[수정]이름");
    Item i = getOneItembyName(Name);
    if(i == null){
      System.out.println("없는 아이템입니다");
      return;
    }
    String changeCategory = im.getStringValue("[수정]카테고리");
    i.setCategory(changeCategory);
    System.out.println("[ " + i + " 변경완료!]");
  }

  public void printCategory(){
    String Cate = im.getStringValue("[출력]카테고리이름");
    ArrayList<Item> temp = new ArrayList<>();
    for (Item i : itemList) {
      if(Cate.equals(i.getCategory())){
        temp.add(i);
      }
    }
    for (Item i : temp) {
      System.out.println(i);
    }
  }

  public void printItemList(){
    System.out.println("이름 가격 분류");
    for (Item i : itemList) {
      System.out.println(i);
    }
  }



}
