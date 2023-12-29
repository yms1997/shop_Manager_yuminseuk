package Controller;

import Utils.FileManager;
import Utils.InnputManger;
import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;
import vo.Cart;
import vo.User;

public class ShopController {
  ItemDAO iDAO;
  UserDAO uDAO;
  CartDAO cDAO;
  InnputManger im;
  FileManager fm;
  int log;

  private final String name = "11번가";

  public ShopController(){
    iDAO = new ItemDAO();
    uDAO = new UserDAO();
    cDAO = new CartDAO();
    im = new InnputManger();
    fm = new FileManager();
    fm.loadFromFile(iDAO, uDAO, cDAO);
  }
  //	System.out.println("[홈하면]");
  //	System.out.println("[1] 로그인 [2] 회원가입 [0]종료");
  //
  //	System.out.println("[관리자 메뉴]");
  //	System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [5.파일 저장] [6.파일 로드] [0.로그아웃] ");

  //	System.out.println("[사용자 메뉴]");
  //	System.out.println("[1] 쇼핑 [2] 주문확인 [3] 탈퇴(주문정보) [0] 로그아웃");

 private void MainMenu(){
    while(true){
      System.out.println("==[ " + name + " 홈화면 ]==");
      System.out.println("[1]로그인 [2]회원가입 [0]종료");
      int sel = im.getIntValue("[ 메뉴입력 ]", 0, 2);
      if(sel == 0){
        System.out.println("[ 프로그램 종료 ]");
        return;
      } else if (sel == 1) {
        log = uDAO.loginUser();
        if(log > 0){
          UserMenu();
        } else if (log == 0) {
          AdminMenu();
        }
      }
      else {
        uDAO.addOneUser();
      }
    }
  }

  private void AdminMenu(){
    while(true){
      System.out.println("[ 관리자 메뉴 ]");
      System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [5.파일 저장] [6.파일 로드] [0.로그아웃] ");
      int sel = im.getIntValue("[ 메뉴입력 ]", 0, 6);
      if(sel == 0){
        uDAO.setLog(null);
        return;
      } else if (sel == 1) {
        itemControll();
      } else if (sel == 2) {
        CategoryControll();
      } else if (sel == 3) {

      } else if (sel == 4) {
        
      } else if (sel == 5) {
        fm.saveFromFile(iDAO, uDAO, cDAO);
      } else if (sel == 6) {
        fm.loadFromFile(iDAO, uDAO, cDAO);
      }
    }
  }
  
  private void itemControll(){
    while(true){
      System.out.println("[1.추가] [2.삭제] [3.수정(가격)] [0.뒤로가기]");
      int sel = im.getIntValue("[ 메뉴입력 ]", 0, 3);
      if(sel == 0){
        return;
      } else if (sel == 1) {
        iDAO.addOneItem();
      } else if (sel == 2) {
        iDAO.deleteOneItem();
      } else if (sel == 3) {
        iDAO.changePrice();
      }
    }
  }

  private void CategoryControll(){
    while(true){
      System.out.println("[1.수정(카테고리)] [2.카테고리별 출력] [0.뒤로가기]");
      int sel = im.getIntValue("[ 메뉴입력 ]", 0, 2);
      if(sel == 0){
        return;
      } else if (sel == 1) {
        iDAO.changeCategory();
      } else if (sel == 2) {
        iDAO.printCategory();
      }
    }
  }

  private void UserControll(){

  }

  private void UserMenu(){
    while(true){
      System.out.println("[ " + uDAO.getLog().getName() + " 사용자 메뉴 ]");
      System.out.println("[1] 쇼핑 [2] 주문확인 [3] 탈퇴(주문정보) [0] 로그아웃");
      int sel = im.getIntValue("[ 메뉴입력 ]", 0, 3);
      if(sel == 0){
        uDAO.setLog(null);
        return;
      } else if (sel == 1) {
        while(true){
          iDAO.printItemList();
          String itemName = im.getStringValue("[ 구매 ]이름");
          if(iDAO.getOneItembyName(itemName) == null){
            continue;
          }
          cDAO.addCartList(uDAO.getLog().getName(), itemName);
          System.out.println("[ " + itemName + " 주문 완료! ]");
          break;
        }
      } else if (sel == 2) {
        
      } else if (sel == 3) {
        
      }
    }
  }

  public void run(){
    MainMenu();
  }
}
