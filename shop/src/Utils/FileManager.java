package Utils;

import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
  final String CUR_PATH = "C:\\git_workspace\\shop_Manager_yuminseuk\\shop_Manager_yuminseuk\\shop\\src\\Utils\\";

  String loadData(String fileName){
    String data = "";
    File f = new File(CUR_PATH + fileName);
    if(!f.exists()){
      try {
        f.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    try (FileReader fr = new FileReader(CUR_PATH + fileName); BufferedReader br = new BufferedReader(fr)) {
      while (true) {
        String line = br.readLine();
        if (line == null) {
          break;
        }
        data += line + "\n";
      }
      if(data != null && data.length() > 0){
        data = data.substring(0, data.length() - 1);
      }
      System.out.println(fileName + " 로드 완료");
    } catch (IOException e) {
      System.out.println(fileName + " 로드 실패");
      e.printStackTrace();
    }
    return data;
  }

  void SaveData(String fileName, String data){
    try(FileWriter fw = new FileWriter(CUR_PATH + fileName)){
      fw.write(data);
      System.out.println(fileName + "저장 성공");
    } catch (IOException e) {
      System.out.println(fileName + "저장 실패");
      e.printStackTrace();
    }
  }

  public void saveFromFile(ItemDAO iDAO, UserDAO uDAO, CartDAO cDAO){
    String userData = uDAO.saveAsFileData();
    String itemData = iDAO.saveAsFileData();
    SaveData("user.txt", userData);
    SaveData("item.txt", itemData);
  }

  public void loadFromFile(ItemDAO iDAO, UserDAO uDAO, CartDAO cDAO){
    String userData = loadData("user.txt");
    String itemData = loadData("item.txt");
    uDAO.addUserFromData(userData);
    iDAO.addItemFromData(itemData);
  }


  // cart.txt
	// user.txt
	// item.txt

}
