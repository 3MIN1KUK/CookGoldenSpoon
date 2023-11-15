const checkObj = {
  "memberId" : false,
  "memberEmail" : false,
  "authKey" : true,
  "memberPw" : false,
  "memberPwConfirm" : false,
  "memberNickname" : false,
};


// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

memberEmail.addEventListener("input", () => {

  if(memberEmail.value.trim().length == 0){
    memberEmail.value = "";

    emailMessage.innerText = "유효한 이메일을 입력해주세요";
    emailMessage.classList.remove("confirm", "error");

    checkObj.memberEmail = "false";
    return;
  }

  let regEx = /[-A-Za-z0-9!#$%&'*+\/=?^_`{|}~]+(?:\.[-A-Za-z0-9!#$%&'*+\/=?^_`{|}~]+)*@(?:[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?\.)+[A-Za-z0-9](?:[-A-Za-z0-9]*[A-Za-z0-9])?/i;

  if(regEx.test(memberEmail.value)){
    emailMessage.innerText = "유효한 이메일 입니다"
    emailMessage.classList.add("confirm"); // 초록색 글씨
    emailMessage.classList.remove("error"); // 빨간글씨 제거
    checkObj.memberEmail = true;
  }
  else{
    checkObj.memberEmail = "false";
    emailMessage.innerText = "유효하지 않은 이메일 형식입니다"

    emailMessage.classList.add("error");
    emailMessage.classList.remove("confirm");
  }

});