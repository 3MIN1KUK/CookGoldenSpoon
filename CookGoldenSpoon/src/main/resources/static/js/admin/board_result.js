
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

function createBoardList(thisTr){
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
      boardTr.append(tdBoardNo);
      thisTr.append(boardTr);
      /* location.href='/board/3/10' */
      // <tr>
      //   <td class="text-center boardNo c-po" onclick="location.href=@`{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}`"></td>
      //   <td class="text-center c-po" th:onclick="|location.href='@{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}'|" ></td>
      //   <td class="text-center c-po" th:onclick="|location.href='@{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}'|"></td>
      //   <td class="text-center c-po" th:onclick="|location.href='@{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}'|">
      //     <th:block th:if="*{boardCode == 1}">게시판</th:block>
      //   </td>
      //   <td th:text="*{boardEnrollDate}" class="text-center c-po" th:onclick="|location.href='@{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}'|"></td>
      //   <td th:text="*{boardHits}" class="text-center c-po" th:onclick="|location.href='@{/board/{boardCode}/{boardNo}(boardCode=*{boardCode}, boardNo=*{boardNo})}'|"></td>
      //     <td class="btn-box">
      //       <button class="delete-btn btn btn-outline-danger" th:onclick="|deleteBoard(*{boardNo}, this)|">삭제</button>
      //     </td>
      // </tr>
    }
  })
}



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