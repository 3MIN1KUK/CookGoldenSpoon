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