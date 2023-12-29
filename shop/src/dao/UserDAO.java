package dao;

import Utils.FileManager;
import Utils.InnputManger;
import vo.User;

import java.util.ArrayList;

public class UserDAO {
    ArrayList<User> userList;
    private User log;
    InnputManger im;
    FileManager fm;


    public UserDAO(){
      userList = new ArrayList<>();
      im = new InnputManger();
      fm = new FileManager();
    }

  public User getLog() {
    return log;
  }

  public void setLog(User log) {
    this.log = log;
  }

  public void addUserFromData(String data){
      if(data.isEmpty()) return;
      String[] temp = data.split("\n");
      for (int i = 0; i < temp.length; i++) {
        String[] info = temp[i].split("/");
        userList.add(new User(info[0], info[1], info[2]));
      }
    }

    public String saveAsFileData(){
      if(userList.isEmpty()) return "";
      String data = "";
      for (User u : userList) {
        data += u.savetoData();
     }
      return data;
    }

    boolean hasUserData(){ // 데이터 유무확인
      if(userList.isEmpty()){
        System.out.println("[ No User Data ]");
        return true;
      }
      return false;
    }

    public void addOneUser(){ // 회원가입
      String NewId = im.getStringValue("[ 추가 ]아이디");
      User u = getOneUserbyId(NewId);
      if(u != null){
        System.out.println("중복된 아이디 입니다");
        return;
      }
      String NewPw = im.getStringValue("[ 추가 ]비밀번호");
      String NewName = im.getStringValue("[ 추가 ]이름");
      User NewUser = new User(NewId, NewPw, NewName);
      userList.add(NewUser);
      System.out.println("[ 회원가입 완료! ]");
    }

    User getOneUserbyId(String id){ // 아이디 중복검사
      if(userList.isEmpty()) return null;
      for (User u : userList) {
        if(u.getId().equals(id)){
          return u;
        }
      }
      return null;
    }

    int getUserIdx(User user){
      if(hasUserData()) return -1;
      for (User u : userList) {
        if(u.getId().equals(user.getId())){
          return userList.indexOf(user);
        }
      }
      return -1;
    }

    public int loginUser(){ // 로그인
      if(hasUserData()) return -1;
      String Id = im.getStringValue("[ 로그인 ]아이디");
      User u = getOneUserbyId(Id);
      if(u == null){
        System.out.println("없는 아이디 입니다");
        return -1;
      }
      String Pw = im.getStringValue("[ 로그인 ]비밀번호");
      if(Pw.equals(u.getPw())){
        System.out.println("[ 로그인 성공 ]");
        log = u;
        return getUserIdx(u);
      }
      else {
        System.out.println("[ 로그인 실패 ]");
        return -1;
      }
    }

}
