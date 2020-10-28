package market.object;

public class Join extends User {
   private String passwordCheck;
   
   public void addUser() {
      
      inputEmail();
      
      if(!getEmail().equals("q")) {
   
         inputPassword();
         
         if(!getPassword().equals("q")) {
         
            inputNickname();
            
            if(!getNickname().equals("q")) {
               if (db.addUser(getEmail(), getNickname(), getPassword()))// DB에 insert(email, password, nickname)
                  System.out.println("가입이 완료되었습니다.");
               else
                  System.out.println("가입에 실패했습니다.");
            }
         }
      }
      

   }
   
   private void inputEmail() {
      while (true) {
         setEmail(scanStr("이메일을 입력하세요 : "));
         if (!db.checkEmail(getEmail())) // email 중복확인(존재하지 않으면 break)
            break;
         System.out.println("이미 존재하는 이메일입니다.");
         
         if(getEmail().equals("q")) {
            break;
         }
      }
   }
   
   private void inputPassword() {
      while (true) {
         setPassword(scanStr("패스워드를 입력하세요 : "));
         passwordCheck = scanStr("다시한번 입력하세요 : ");
         if (getPassword().equals(passwordCheck))
            break;
         System.out.println("패스워드가 일치하지 않습니다.");
         
         if(getPassword().equals("q")) {
            break;
         }
      }
   }
   
   private void inputNickname() {
      while (true) {
         setNickname(scanStr("사용하실 닉네임을 입력하세요 : "));
         if (!db.checkNickname(getNickname())) // nickname 중복확인
            break;
         System.out.println("이미 존재하는 닉네임 입니다.");
      
         if(getNickname().equals("q")) {
            break;
         }
      }
   }
}