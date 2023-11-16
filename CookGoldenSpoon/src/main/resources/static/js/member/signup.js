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


    if(regEx.test(memberId.value) ){
      fetch("/member/checkId?id="+memberId.value)
      .then( response => response.text() )
      .then( result => {
  
          if(result == 0){ // 중복 X
            idMessage.innerText = "사용 가능한 아이디 입니다"
            idMessage.classList.add("confirm"); // 초록색 글씨
            idMessage.classList.remove("error"); // 빨간글씨 제거
              checkObj.memberId = true;
          }else{ // 중복 O
            idMessage.innerText = "이미 사용중인 아이디 입니다"
            idMessage.classList.add("error"); // 초록색 글씨
            idMessage.classList.remove("confirm"); // 빨간글씨 제거
              checkObj.memberId = false;
          }
      })
    }

  }

  else{
    checkObj.memberId = "false";
    idMessage.innerText = "아이디 형식이 유효하지 않습니다"

    idMessage.classList.add("error");
    idMessage.classList.remove("confirm");
  }

});

//----------------------------------------------------------------


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
    if(regEx.test(memberEmail.value) ){
      fetch("/member/checkEmail?email="+memberEmail.value)
      .then( response => response.text() )
      .then( result => {
  
          if(result == 0){ // 중복 X
              emailMessage.innerText = "사용 가능한 이메일 입니다"
              emailMessage.classList.add("confirm"); // 초록색 글씨
              emailMessage.classList.remove("error"); // 빨간글씨 제거
              checkObj.memberEmail = true;
          }else{ // 중복 O
              emailMessage.innerText = "이미 사용중인 이메일 입니다"
              emailMessage.classList.add("error"); // 초록색 글씨
              emailMessage.classList.remove("confirm"); // 빨간글씨 제거
              checkObj.memberEmail = false;
          }
      })
    }
  }
  else{
    checkObj.memberEmail = "false";
    emailMessage.innerText = "유효하지 않은 이메일 형식입니다"

    emailMessage.classList.add("error");
    emailMessage.classList.remove("confirm");
  }

});

//----------------------------------------------------------------



// 인증키 검사
const authKey = document.getElementById("authKey");
const authMessage = document.getElementById("authMessage");

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


// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input", () => {

  if(memberNickname.value.trim().length == 0){
    memberNickname.value = "";

    nicknameMessage.innerText = "2 ~ 10 자리의 영어 또는 한글만 입력해주세요";
    nicknameMessage.classList.remove("confirm", "error");

    checkObj.memberNickname = "false";
    return;
  }

  const regEx = /^[가-힣\w\d]{2,10}$/;

  if(regEx.test(memberNickname.value)){
    if(regEx.test(memberNickname.value) ){
      fetch("/member/checkNickname?nickname="+memberNickname.value)
      .then( response => response.text() )
      .then( result => {
  
          if(result == 0){ // 중복 X
            nicknameMessage.innerText = "사용 가능한 닉네임 입니다"
            nicknameMessage.classList.add("confirm"); // 초록색 글씨
            nicknameMessage.classList.remove("error"); // 빨간글씨 제거
              checkObj.memberNickname = true;
          }else{ // 중복 O
            nicknameMessage.innerText = "이미 사용중인 닉네임 입니다"
            nicknameMessage.classList.add("error"); // 초록색 글씨
            nicknameMessage.classList.remove("confirm"); // 빨간글씨 제거
              checkObj.memberNickname = false;
          }
      })
    }
  }
  else{
    checkObj.memberNickname = "false";
    nicknameMessage.innerText = "유효하지 않은 닉네임 형식입니다"

    nicknameMessage.classList.add("error");
    nicknameMessage.classList.remove("confirm");
  }

});

//----------------------------------------------------------------



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