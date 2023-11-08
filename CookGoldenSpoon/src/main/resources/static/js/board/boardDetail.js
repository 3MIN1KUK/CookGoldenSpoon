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