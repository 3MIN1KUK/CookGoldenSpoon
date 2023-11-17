const checkObj = {
  "memberId" : false,
  "memberEmail" : false,
  "authKey" : false,
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



// 인증키 검사
const sendAuthKeyBtn = document.getElementById("email-btn");
const authKeyMessage = document.getElementById("authMessage");

// 인증번호 보내기 버튼을 클릭하면
// authKeyMessage에 5분 타이머를 클릭
let authTimer;
let authMin = 4;
let authSec = 59;

// 인증번호를 보낸 이메일을 저장할 변수
let tempEmail;

// 인증번호 받기 버튼 클릭 시
sendAuthKeyBtn.addEventListener("click", function(){
    authMin = 4;
    authSec = 59;

    checkObj.authKey = false;

    if(checkObj.memberEmail){ // 중복이 아닌 이메일인 경우


        /* fetch() API - POST방식, 단일 데이터 요청 */
        fetch("/email/signup", {
            method : "POST",
            headers : {"Content-Type" : "application/text"},
            body : memberEmail.value // 전달되는 데이터가 한 개
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                console.log("인증 번호가 발송되었습니다.")
                tempEmail = memberEmail.value;
            }else{
                console.log("인증번호 발송 실패")
            }
        })
        .catch(err => {
            console.log("이메일 발송 중 에러 발생");
            console.log(err);
        });
        

        alert("인증번호가 발송 되었습니다.");

        
        authKeyMessage.innerText = "05:00";
        authKeyMessage.classList.remove("confirm");

        authTimer = window.setInterval(()=>{

            authKeyMessage.innerText = "0" + authMin + ":" + (authSec<10 ? "0" + authSec : authSec);
            
            // 남은 시간이 0분 0초인 경우
            if(authMin == 0 && authSec == 0){
                checkObj.authKey = false;
                clearInterval(authTimer);
                return;
            }

            // 0초인 경우
            if(authSec == 0){
                authSec = 60;
                authMin--;
            }


            authSec--; // 1초 감소

        }, 1000)

    } else{
        alert("중복되지 않은 이메일을 작성해주세요.");
        memberEmail.focus();
    }

});  


/* 인증번호 확인 */
const authKey = document.getElementById("authKey");
const checkAuthKeyBtn = document.getElementById("email-confirm-btn");

checkAuthKeyBtn.addEventListener("click", function(){

    if(authMin > 0 || authSec > 0){ // 시간 제한이 지나지 않은 경우에만 인증번호 검사 진행
        /* fetch API */
        const obj = {"inputKey":authKey.value, "email":tempEmail}

        fetch("/email/checkAuthKey",  {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(obj)
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                clearInterval(authTimer);
                authKeyMessage.innerText = "인증되었습니다.";
                authKeyMessage.classList.add("confirm");
                checkObj.authKey = true;

                authKey.disabled = true;
            } else{
                alert("인증번호가 일치하지 않습니다.")
                checkObj.authKey = false;
            }
        })
        .catch(err => console.log(err));


    } else{
        alert("인증 시간이 만료되었습니다. 다시 시도해주세요.")
    }

});


document.getElementById("findPwFrm").addEventListener("submit", e => {

  for(let key in checkObj){

      if(!checkObj[key]){
          let str
          switch(key){

              case "memberId" : str = "아이디가  유효하지 않습니다"; break;
              
              case "memberEmail" : str = "이메일이 유효하지 않습니다"; break;

              case "authKey" : str = "인증번호가 유효하지 않습니다"; break;

          }

          alert(str);

          document.getElementById(key).focus();
          e.preventDefault(); // form 제출 X
          return;
      }
  }
});