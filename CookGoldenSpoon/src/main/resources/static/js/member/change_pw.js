const checkObj = {
  "memberPw" : false,
  "memberPwConfirm" : false,
};

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
  
      // 비밀번호가 유효하게 작성된 상태에서
      // 비밀번호 확인이 입력되어 있을 때
      if(memberPwConfirm.value.trim().length != 0){
          // 비밀번호 == 비밀번호 확인  (같을 경우)
          if(memberPw.value == memberPwConfirm.value){
            pwConfirmMessage.innerText = "비밀번호가 일치합니다";
            pwConfirmMessage.classList.add("confirm");
            pwConfirmMessage.classList.remove("error");
              checkObj.memberPwConfirm = true;
              
          } else{ // 다를 경우
            pwConfirmMessage.innerText = "비밀번호가 일치하지 않습니다";
            pwConfirmMessage.classList.add("error");
            pwConfirmMessage.classList.remove("confirm");
              checkObj.memberPwConfirm = false;
          }
      }

      
  } else{ // 유효하지 않은 경우
      
      pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다";
      pwMessage.classList.add("error");
      pwMessage.classList.remove("confirm");
      checkObj.memberPw = false; 
  }
});



// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', ()=>{

  if(checkObj.memberPw){ // 비밀번호가 유효하게 작성된 경우에

      // 비밀번호 == 비밀번호 확인  (같을 경우)
      if(memberPw.value == memberPwConfirm.value){
        pwConfirmMessage.innerText = "비밀번호가 일치합니다";
        pwConfirmMessage.classList.add("confirm");
        pwConfirmMessage.classList.remove("error");
          checkObj.memberPwConfirm = true;
          
      } else{ // 다를 경우
        pwConfirmMessage.innerText = "비밀번호가 일치하지 않습니다";
        pwConfirmMessage.classList.add("error");
        pwConfirmMessage.classList.remove("confirm");
          checkObj.memberPwConfirm = false;
      }

  } else { // 비밀번호가 유효하지 않은 경우
      checkObj.memberPwConfirm = false;
  }
});


document.getElementById("changePwFrm").addEventListener("submit", e => {

  for(let key in checkObj){

      if(!checkObj[key]){
          let str
          switch(key){

            case "memberPw" : str = "비밀번호가 유효하지 않습니다"; break;
              
            case "memberPwConfirm" : str = "비밀번호가 일치하지 않습니다"; break;
            
          }

          alert(str);

          document.getElementById(key).focus();
          e.preventDefault(); // form 제출 X
          return;
      }
  }
});