const checkObj = {
  "memberId" : false,
  "memberPw" : false,
};


// 아이디 유효성 검사
const memberId = document.getElementById("memberId");
const idMessage = document.getElementById("idMessage");
memberId.addEventListener("input", () => {

  if(memberId.value.trim().length == 0){
    memberId.value = "";

    idMessage.innerText = "4 ~ 16 자리의 영어 또는 숫자만 입력해주세요";
    idMessage.classList.remove("confirm", "error");

    checkObj.memberId = "false";
    return;
  }

  const regEx = /^[a-zA-Z0-9]{4,16}$/;

  if(regEx.test(memberId.value)){
    checkObj.memberId = "true";
    idMessage.innerText = "유효한 아이디 형식입니다";

    idMessage.classList.add("confirm");
    idMessage.classList.remove("error");
  }

  else{
    checkObj.memberId = "false";
    idMessage.innerText = "아이디 형식이 유효하지 않습니다"

    idMessage.classList.add("error");
    idMessage.classList.remove("confirm");
  }

});


// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const pwMessage = document.getElementById("pwMessage");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwConfirmMessage = document.getElementById("pwConfirmMessage");

memberPw.addEventListener("input", () => {

  // 비밀번호가 입력되지 않은 경우
  if(memberPw.value.trim().length == 0){
      memberPw.value = ""; // 띄어쓰지 못넣게 하기

      pwMessage.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요.";
      pwMessage.classList.remove("confirm", "error"); // 검정 글씨

      checkObj.memberPw = false; // 빈칸 == 유효 X
      return;
  }

  // 영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이
  const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

  // 입력한 비밀번호가 유효한 경우
  if(regEx.test(memberPw.value)){
      checkObj.memberPw = true; 
      
      pwMessage.innerText = "유효한 비밀번호 형식입니다";
      pwMessage.classList.add("confirm");
      pwMessage.classList.remove("error");
  
  } else{ // 유효하지 않은 경우
      
      pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다";
      pwMessage.classList.add("error");
      pwMessage.classList.remove("confirm");
      checkObj.memberPw = false; 
  }
});