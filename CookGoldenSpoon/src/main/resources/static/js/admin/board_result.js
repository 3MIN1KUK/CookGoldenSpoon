
function objectToQueryString(obj) {
  return Object.keys(obj)
    .map(key => {
      // 값이 null이 아닌 경우에만 추가
      if (obj[key] !== null && obj[key] !== undefined) {
        return encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]);
      } else {
        return ''; // 값이 null이면 빈 문자열 반환
      }
    })
    .filter(queryPart => queryPart !== '') // 빈 문자열 제거
    .join('&');
}

// 게시글 목록 추가
function createBoardList(thisTr){
  const boardArea = document.getElementById('boardArea');
  boardArea.innerHTML = "";
  const queryString = objectToQueryString(searchBoard);
  console.log(queryString);
  fetch("/admin/createBoardList?" + queryString,{
    method : "GET",
    headers : {"Content-type" : "application/json"}
  })
  .then(res => res.json())
  .then(result =>{
    boardList = result.boardList;
    console.log(boardList);
    let boardListHTML = "";
    for(let board of boardList){
      const boardTr = document.createElement("tr");

      const tdBoardNo = document.createElement("td");
      tdBoardNo.classList.add("text-center", "boardNo", "c-po");
      tdBoardNo.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      tdBoardNo.innerHTML = board.boardNo;

      const tdBoardTitle = document.createElement("td");
      tdBoardTitle.classList.add("text-center", "c-po");
      tdBoardTitle.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      tdBoardTitle.innerHTML = board.boardTitle;

      const tdMemberNickname = document.createElement("td");
      tdMemberNickname.classList.add("text-center", "c-po");
      tdMemberNickname.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      tdMemberNickname.innerHTML = board.memberNickname;

      const tdBoardCode = document.createElement("td");
      tdBoardCode.classList.add("text-center", "c-po");
      tdBoardCode.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      if(board.boardCode == 1) tdBoardCode.innerHTML = "게시판";
      if(board.boardCode == 2) tdBoardCode.innerHTML = "게시판";
      if(board.boardCode == 3) tdBoardCode.innerHTML = "게시판";

      const tdboardEnrollDate = document.createElement("td");
      tdboardEnrollDate.classList.add("text-center", "c-po");
      tdboardEnrollDate.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      tdboardEnrollDate.innerHTML = board.boardEnrollDate;

      const tdboardHits = document.createElement("td");
      tdboardHits.classList.add("text-center", "c-po");
      tdboardHits.setAttribute("onclick", `location.href='/board/${board.boardCode}/${board.boardNo}'`);
      tdboardHits.innerHTML = board.boardHits;
      
      const tdDeleteBtn = document.createElement("td");
      tdDeleteBtn.classList.add("btn-box");

      const deleteBtn = document.createElement("button");
      deleteBtn.classList.add("delete-btn", "btn", "btn-outline-danger");
      deleteBtn.setAttribute("onclick", `deleteBoard(${board.boardNo}, this)`);
      deleteBtn.innerHTML = "삭제";

      tdDeleteBtn.append(deleteBtn);

      boardTr.append(tdBoardNo, tdBoardTitle,
        tdMemberNickname, tdBoardCode,
        tdboardEnrollDate, tdboardHits, tdDeleteBtn);
      boardArea.append(boardTr);
    }
  })
  .catch(err => console.log(err));
}


// 게시글 삭제
function deleteBoard(boardNo, thisTr){
  if( confirm("해당 게시글을 삭제 하시겠습니까?") ){
    console.log(searchBoard);
    fetch("/admin/boardDelete", {
      method : "PUT",
      headers : {"Content-type" : "application/json"},
      body : boardNo
    })
    .then(resp => resp.text())
    .then(result => {
      if (result > 0){
        alert("게시글이 삭제되었습니다");
        thisTr.parentElement.parentElement.remove();
        createBoardList(thisTr);
      }
      else{
        alert("삭제 실패");
      }
    })
    .catch(err => console.log(err));
  }
}



function changeBoardDelFl( boardNo, thisBtn){
  const boardDelFl = thisBtn.value;

  console.log(boardDelFl);
  console.log(thisBtn);


  const data = {
    "boardNo" : boardNo,
  }
  
  if(boardDelFl == 'N'){
    data.boardDelFl = 'Y';
  }
  
  if(boardDelFl == 'Y'){
    data.boardDelFl = 'N';
  }
  
  console.log(data);
  fetch("/admin/changeBoardDelFl",{
    method : "PUT",
    headers : {"Content-Type" : "application/json"},
    body : JSON.stringify(data)
  })
  .then(resp => resp.text())
  .then(result =>{
    if(result > 0){

      if(boardDelFl == 'N'){
        alert("삭제 처리 성공")
        thisBtn.classList.remove("btn-outline-danger")
        thisBtn.classList.add("btn-outline-success")
        thisBtn.value = 'Y'
        thisBtn.innerHTML = '복구';
      }

      if(boardDelFl == 'Y'){
        alert("복구 처리 성공")
        thisBtn.classList.remove("btn-outline-success")
        thisBtn.classList.add("btn-outline-danger")
        thisBtn.value = 'N'
        thisBtn.innerHTML = '삭제';
      }
    }
    else{
      if(boardDelFl == 'N'){
        alert("삭제 처리 실패")
      } else{
        alert("복구 처리 실패")
      }
    }
  })

}