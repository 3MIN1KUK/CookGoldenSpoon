/* 게시글 삭제 */
const deleteBtn = document.getElementById("deleteBtn");
// 만약 화면에 버튼이 없으면 null 반환
if(deleteBtn != null){ // 삭제 버튼이 존재하는 경우
   deleteBtn.addEventListener("click", () => {
      // confirm : 확인 클릭 -> true, 취소 클릭 -> false 반환
      if( confirm("삭제 하시겠습니까?") ) {
         // 상세 조회 페이지 주소 : /Board/{boardCode}/{boardNo}
         // 삭제 요청 주소 : /editBoard/{boardCode}/{boardNo}/delete (GET)
         location.href
          = location.pathname.replace("board", "editBoard") + "/delete";
      }
   });
}

/* 수정 버튼 클릭 시 수정 화면 요청 */
const updateBtn = document.getElementById("updateBtn");
if(updateBtn != null) { // 수정 버튼 존재 시
   updateBtn.addEventListener('click', () => {
      let url = `/editBoard/${boardCode}/${boardNo}/update${location.search}`;
      location.href = url;
   });

}

// -------------------------------------------------------------------------------------------
/* 목록으로 버튼*/

const goToBtn = document.getElementById("goToBtn");

if(goToBtn != null) { // 화면에 목록으로 버튼이 있을 때만 수행
   const goToFn = () => {
      const paramMap = new URL(location.href).searchParams;
      const obj = {}; // 주소에 담겨있는 파라미터를 담은 객체
      obj.cp = paramMap.get("cp");
      obj.key = paramMap.get("key");
      obj.query = paramMap.get("query");
      // 1. 쿼리스트링 조합하기
      const tempParams = new URLSearchParams();
      for(let key in obj){ // 객체 전용 향상된 for문
         if(obj[key] != null) tempParams.append(key, obj[key]);
      }
      // 2) 목록으로 돌아가기
      location.href = `/board/${boardCode}?${tempParams.toString()}`;
   }
   // 이벤트 리스너 추가
   goToBtn.addEventListener("click", goToFn);
}



/* 신고 팝업창 */

let reportType;
let reporterNo;
let reporterNickname;
let reportCommentNo;
let data = {};

// 댓글 신고 값 설정
function csComment(memberNo, boardCommentNo, thisComment){
   data.reportType = "boardComment";
   data.reporterNo = memberNo;
   data.reporterNickname = thisComment.value;
   data.reportContentTo = boardCommentNo;
}

// 게시글 신고 값 설정
function csBoard(){
   data.reportType = "board";
   data.reporterNo = boards.memberNo;
   data.reporterNickname = boards.memberNickname;
   data.reportContentTo = boardNo;
}

var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));




function reportSubmit() {
   
   const reportContent = document.getElementById('reportContent');
   const reportTitle = document.getElementById('reportTitle');

   if(reportContent.value.trim().length == 0 || reportTitle.value.trim().length == 0){
      alert("신고 제목 또는 내용을 입력해주세요")
      return;
   }
   
   data.reportContent = reportContent.value;
   data.reportTitle = reportTitle.value;
   data.reportLocation = window.location.href.replace(window.location.origin, '');

   
   fetch("/report/csCustomer",{
      method : "POST",
      headers : {"Content-type" : "application/json"},
      body : JSON.stringify(data)
   })
   .then(response => response.text())
   .then(result =>{
      console.log(result);
      if(result > 0){
         
         myModal.hide();
         alert("신고 성공!")
         
      } else{
         alert("신고 실패")
         
      }
      
   })
}

// 
const reportCommentSubmit = document.getElementById('report-comment-submit');
reportCommentSubmit.addEventListener('click', reportSubmit);