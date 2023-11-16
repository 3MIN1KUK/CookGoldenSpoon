function changeAuthority(selected, memberNo){
  console.log(selected.value);
  console.log(memberNo);

  const data = {
    "memberAuthority" : selected.value,
    "memberNo" : memberNo
  }

  fetch("/admin/changeAuthority",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result =>{
    if(result > 0){
      alert("권한 수정 성공")
    }
    else{
      alert("권한 수정 실패")
    }
  })
}


const inputs = document.querySelectorAll(".member-search-input");
const searchQuery = document.getElementById("searchQuery");

// 즉시 실행 함수 (해석되자 마자 실행되는 함수, 속도가 빠름)
(()=>{
  // 주소에 있는 파라미터(쿼리스트링) 얻어오기
  const params = new URL(location.href).searchParams;

  const memberNickname = params.get("memberNickname"); // t, c, tc, w 중 하나
  const memberEmail = params.get("memberEmail"); // 검색어
  const memberId = params.get("memberId"); // 검색어

    inputs[0].value = memberNickname;
    inputs[1].value = memberEmail;
    inputs[2].value = memberId;
})();



function changeMemberDelFl( memberNo, thisBtn){
  const memberDelFl = thisBtn.value;
  console.log(memberDelFl);
  console.log(thisBtn);


  const data = {
    "memberNo" : memberNo
  }

  if(memberDelFl == 'N'){
    data.memberDelFl = 'Y';
  }

  if(memberDelFl == 'Y'){
    data.memberDelFl = 'N';
  }
  
  fetch("/admin/changeMemberDelFl",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result =>{
    if(result > 0){

      if(memberDelFl == 'N'){
        alert("탈퇴 처리 성공")
        thisBtn.classList.remove("btn-danger")
        thisBtn.classList.add("btn-success")
        thisBtn.value = 'Y'
        thisBtn.innerHTML = '복구';
      }
      if(memberDelFl == 'Y'){
        alert("복구 처리 성공")
        thisBtn.classList.remove("btn-success")
        thisBtn.classList.add("btn-danger")
        thisBtn.value = 'N'
        thisBtn.innerHTML = '탈퇴';
      }
    }
    else{
      if(memberDelFl == 'N'){
        alert("탈퇴 처리 실패")
      } else{
        alert("복구 처리 실패")
      }
    }
  })

}