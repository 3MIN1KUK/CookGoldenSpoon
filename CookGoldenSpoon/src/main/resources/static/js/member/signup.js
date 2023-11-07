const checkObj = {
  "memberId" : false,
  "memberEmail" : false,
  "authKey" : true,
  "memberPw" : false,
  "memberPwConfirm" : false,
  "memberNickname" : false,
};

// 아이디 유효성 검사
const memberId = document.getElementById("memberId");
const idMessage = document.getElementById("idMessage");

// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

// 인증키 검사
const authKey = document.getElementById("authKey");
const authMessage = document.getElementById("authMessage");

// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const pwMessage = document.getElementById("pwMessage");

// 비밀번호 확인 유효성 검사
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwConfirmMessage = document.getElementById("pwConfirmMessage");

// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");



/* 회원 가입 버튼이 클릭 되었을 때 */
document.getElementById("signUpFrm").addEventListener("submit", e => {

  for(let key in checkObj){

      if(!checkObj[key]){
          let str
          switch(key){
              case "memberId" : str = "아이디가  유효하지 않습니다"; break;
              
              case "memberEmail" : str = "이메일이 유효하지 않습니다"; break;

              case "authKey" : str = "인증번호가 유효하지 않습니다"; break;

              case "memberPw" : str = "비밀번호가 유효하지 않습니다"; break;
              
              case "memberPwConfirm" : str = "비밀번호가 일치하지 않습니다"; break;
              
              case "memberNickname" : str = "닉네임이 유효하지 않습니다"; break;
          }

          alert(str);

          // key == input id 속성 값
          // 유효하지 않은 input 태그로 focus 맞춤
          document.getElementById(key).focus();
          e.preventDefault(); // form 제출 X
          return;
      }
  }
});