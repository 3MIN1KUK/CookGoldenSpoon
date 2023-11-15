const curPassword = document.getElementById("curPassword");
const newPassword = document.getElementById("newPassword");
const newPasswordCheck = document.getElementById("newPasswordCheck");

const newPwText = document.getElementById("newPwText");
const newPwTextCheck = document.getElementById("newPwTextCheck");

const checkObj = {
  "newPassword" : false,
  "newPasswordCheck" : false,
};

curPassword.addEventListener("input", e=>{
  e.target.value = e.target.value.trim();
});

// 비밀번호 유효성 검사
newPassword.addEventListener("input", () => {

  // 비밀번호가 입력되지 않은 경우
  if(newPassword.value.trim().length == 0){
      newPassword.value = ""; // 띄어쓰지 못넣게 하기

      newPwText.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요.";
      newPwText.classList.remove("confirm", "error"); // 검정 글씨

      checkObj.newPassword = false; // 빈칸 == 유효 X
      return;
  }
  // 영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이
  const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

  // 입력한 비밀번호가 유효한 경우
  if(regEx.test(newPassword.value)){
      checkObj.newPassword = true; 
      
      newPwText.innerText = "유효한 비밀번호 형식입니다";
      newPwText.classList.add("confirm");
      newPwText.classList.remove("error");
  
      // 비밀번호가 유효하게 작성된 상태에서
      // 비밀번호 확인이 입력되어 있을 때
      if(newPasswordCheck.value.trim().length != 0){
          // 비밀번호 == 비밀번호 확인  (같을 경우)
          if(newPassword.value == newPasswordCheck.value){
            newPwTextCheck.innerText = "비밀번호가 일치합니다";
            newPwTextCheck.classList.add("confirm");
            newPwTextCheck.classList.remove("error");
              checkObj.newPasswordCheck = true;
              
          } else{ // 다를 경우
            newPwTextCheck.innerText = "비밀번호가 일치하지 않습니다";
            newPwTextCheck.classList.add("error");
            newPwTextCheck.classList.remove("confirm");
              checkObj.newPasswordCheck = false;
          }
      }

      
  } else{ // 유효하지 않은 경우
      
      newPwText.innerText = "비밀번호 형식이 유효하지 않습니다";
      newPwText.classList.add("error");
      newPwText.classList.remove("confirm");
      checkObj.newPassword = false; 
  }
});



// 비밀번호 확인 유효성 검사
newPasswordCheck.addEventListener('input', ()=>{

  if(checkObj.newPassword){ // 비밀번호가 유효하게 작성된 경우에

      // 비밀번호 == 비밀번호 확인  (같을 경우)
      if(newPassword.value == newPasswordCheck.value){
        newPwTextCheck.innerText = "비밀번호가 일치합니다";
        newPwTextCheck.classList.add("confirm");
        newPwTextCheck.classList.remove("error");
          checkObj.newPasswordCheck = true;
          
      } else{ // 다를 경우
        newPwTextCheck.innerText = "비밀번호가 일치하지 않습니다";
        newPwTextCheck.classList.add("error");
        newPwTextCheck.classList.remove("confirm");
          checkObj.newPasswordCheck = false;
      }

  } else { // 비밀번호가 유효하지 않은 경우
      checkObj.newPasswordCheck = false;
  }
});

document.getElementById("pwChangeFrm").addEventListener("submit", e => {

  for(let key in checkObj){

    if(!checkObj[key]){
      let str
      switch(key){
        case "newPassword" : str = "비밀번호가 유효하지 않습니다"; break;
        
        case "newPasswordCheck" : str = "비밀번호가 일치하지 않습니다"; break;
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