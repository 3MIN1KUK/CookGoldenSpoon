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