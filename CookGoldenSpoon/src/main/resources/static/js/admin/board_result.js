
function createBoardList(){
  fetch("/admin/createBoardList",{
    method : "POST",
    headers : {"Content-type" : "application/json"},
    body : searchBoard
  })
  .then(res => res.json())
  .then(result =>{
    console.log(result);
    boardList = result.boardList;
  })
}




function deleteBoard(boardNo){
  console.log(boardNo);
  if( confirm("해당 게시글을 삭제 하시겠습니까?") ){
    fetch("/admin/boardDelete", {
      method : "PUT",
      headers : {"Content-type" : "application/json"},
      body : boardNo
    })
    .then(resp => resp.text())
    .then(result => {
      if (result > 0){
        alert("게시글이 삭제되었습니다");
      }
      else{
        alert("삭제 실패");
      }
    })
    .catch(err => console.log(err));
  }
}