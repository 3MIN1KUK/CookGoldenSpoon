const xBtn = document.getElementById("xBtn"); // x 버튼
const memberProfile = document.getElementsByClassName("memberProfile"); // img 태그
let profileInput = document.getElementById("profileInput"); // input 태그
const profileBtn = document.getElementById("profileBtn");
// 프로필 이미지가 
// -1 : 변경되지 않았을 때
//  0 : 있었는데 없어짐 == x 버튼 클릭
//  1 : 새 이미지 선택 (없다 -> 있음, 있음 -> 다른 이미지)
let statusCheck = -1;

let backupInput;

// profileInput 존재할 때
if(profileInput != null){
  
  const changeProfileFn = e=>{
  
    // 업로드 파일 최대 크기
    const maxSize = 1024*1024;
  
    // 업로드한 파일 정보가 담긴 객체
    const uploadFile = e.target.files[0];
  
    // 파일을 선택한 후 취소
    if(uploadFile == undefined){
  
      // backup한 요소 복제
      const temp = backupInput.cloneNode(true);
  
      // 화면에 원본 input을 temp로 바꾸기
      profileInput.after(temp);
      profileInput.remove();
      profileInput = temp;
  
      // 복제본에 이벤트리스너 추가
      profileInput.addEventListener("change", changeProfileFn);
     return;
    }
  
    // 파일이 크기를 초과하는 경우
    if(uploadFile.size > maxSize){
      alert("1MB 이하의 이미지만 업로드 가능합니다")
      // 이미지 변경이 없었을 때
      if(statusCheck == -1){

        // 최대 크기를 초과해도 input에 value가 남기에 제거해야 한다
        profileInput.value = "";
        statusCheck = -1;
      } else{ // 기존 이미지가 있었을 때
        // backup한 요소 복제
        const temp = backupInput.cloneNode(true);

        // 화면에 원본 input을 temp로 바꾸기
        profileInput.after(temp);
        profileInput.remove();
        profileInput = temp;

        // 복제본에 이벤트리스너 추가
        profileInput.addEventListener("change", changeProfileFn);
      }
      return;
    }
    // 선택된 파일 미리보기 만들기
    // js에서 파일 읽는 객체
    const reader = new FileReader();
  
    // 매개변수에 작성된 파일을 읽어서 파일을 나타내는 URL로 변경
    reader.readAsDataURL(uploadFile);
  
    // 파일을 다 읽은 경우
    reader.onload = e=>{
      const url = e.target.result; // 이미지가 변환된 dataUrl
  
      // 이미지 태그에 이미지 추가
      memberProfile[0].setAttribute("src", url);

      statusCheck =1;
  
      // 파일이 업로드된 input 태그를 복제해서 backup
      backupInput = profileInput.cloneNode(true);
    }
  };
  
  // 이미지 선택 또는 취소 시 동작
  profileInput.addEventListener("change", changeProfileFn);
}
  
  // x 버튼 클릭 시
  xBtn.addEventListener("click", ()=>{
    
    // 미리보기 삭제
    memberProfile[0].removeAttribute("src");
    memberProfile[0].setAttribute("src", defaultImg);
    
    // input 태그 파일 제거
    profileInput.value = "";
    
    // backup 제거
    backupInput = undefined;
    
    statusCheck = 0;
  });
  
  profileBtn.addEventListener("click", e=>{
    let flag = true;
    
    if(loginMemberProfile != null && statusCheck == 0) flag = false;
    if(loginMemberProfile == null && statusCheck == 1) flag = false;
    if(loginMemberProfile != null && statusCheck == 1) flag = false;
    if(flag){
    alert("이미지 변경 후 클릭 해주세요");
    return;
    }
    
    const formData = new FormData();
    formData.append("memberProfile", profileInput.files[0]);
    
    fetch("/myPage/edit/profile", {
      method : "POST",
      headers : {/* "Content-Type" : "multipart/form-data" */},
      body : formData
    })
    .then(resp => resp.text())
    .then(result =>{
      if(result > 0){
        alert("프로필 이미지 변경 성공");
      } else{
        alert("프로필 이미지 변경 실패");
      }
    })
    .catch(e=>console.log(e));
  });

  
  
const inputNickname = document.getElementById("inputNickname");
let validationCheck = 0;

inputNickname.addEventListener("input", e=>{
  e.target.value = e.target.value.trim();
  const validation = document.querySelector(".validation");
  validation.innerText = "";
  const regEx = /^[가-힣\w\d]{2,10}$/;

  const memberNickname = e.target.value;
  if(regEx.test(memberNickname)){
    // 중복검사
    if(e.target.value == loginMemberNickname){
      validation.innerText = "";
      validationCheck = 0;
      return;
    }
    fetch("/myPage/validation?memberNickname=" + memberNickname)
    .then(resp => resp.text())
    .then(result => {
      if(result > 0){
        validation.innerText = "중복된 닉네임입니다";
        validation.style.color = "red";
        validationCheck = 1;
      } else {
        validation.innerText = "사용가능한 닉네임입니다"
        validation.style.color = "green";
        validationCheck = 0;
      }
    })
    .catch();
  } else {
    validation.innerText = "유효하지 않습니다";
    validation.style.color = "red";
    validationCheck = 1;
  }

});

const inputIntro = document.querySelector(".userInfo-intro");
inputIntro.addEventListener("input", e=>{
  console.log(e.target.value.length);
  if(e.target.value.length > 64){
    alert("글자 수를 초과했습니다");
  }
});


const editFrm = document.getElementById("editFrm");


editFrm.addEventListener("submit", e=>{
  if(validationCheck != 0){
    alert("닉네임이 유효하지 않습니다")
    inputNickname.focus();
    e.preventDefault();
  }
})

/* 비밀번호 변경 팝업창 */
const changePwBtn = document.getElementById("changePwBtn");

changePwBtn.addEventListener("click", ()=>{
  window.open("/myPage/edit/pwChange", "_blank", "width=600, height=300, left=700, top=400");
});

/* 탈퇴 */
const secession = document.getElementById("secession");

secession.addEventListener("click", ()=>{
  if(confirm("정말 탈퇴하시겠습니까?")){
    fetch("/myPage/secession",{
      method : "POST",
      headers : {"Content-Type" : "application/json"},
      body : memberNo
    })
    .then(resp => resp.text())
    .then(result =>{
      if(result>0){
        alert("회원 탈퇴 되었습니다.")
        location.href = "/";
      } else {
        alert("회원 탈퇴 실패")
      }
    })
    .catch(e=>console.log(e));
  }
});