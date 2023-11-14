// 댓글

// 댓글 목록 조회
const selectCommentList = () => {
    // 기본적으로 form태그는 GET/POST만 지원
    
    fetch("/boardComment/select?boardNo="+boardNo)  // GET방식은 주소에 파라미터를 담아서 전달
    .then(response => response.json()) // 응답 객체 -> 파싱
    .then(cList => { // cList : 댓글 목록(객체 배열)
        // console.log(cList);

        // 화면에 출력되어 있는 댓글 목록 삭제
        const commentList = document.getElementById("commentList");
        commentList.innerHTML = "";
        if(cList.length == 0){
            const h1 = document.createElement("h1");
            h1.innerText = "댓글이 없습니다";
            h1.style.padding = "2rem";
            commentList.append(h1);
            console.log("하하");
        }
        // cList에 저장된 요소를 하나씩 접근
        for(let comment of cList){

            // 행
            const commentRow = document.createElement("div");
            commentRow.classList.add("board2-review");

            // 답글일 경우 child-comment 클래스 추가
            if(comment.boardParentNo != 0)  {
                commentRow.classList.add("child-comment");
                const i = document.createElement("i");
                i.classList.add("fa-solid", "fa-arrow-turn-up", "fa-rotate-90", "reply");
                commentRow.append(i);
            }


            // 삭제된 댓글이지만 자식 댓글 때문에 조회된 경우
            if(comment.boardCommentDelFl == 'Y') commentRow.innerText = "삭제된 댓글 입니다";

            // 삭제되지 않은 댓글인 경우 (댓글, 버튼 생성)
            else{
                // 작성자
                const commentWriterImg = document.createElement("div");
                commentWriterImg.classList.add("board2-writer-image");

                // 프로필 이미지
                const profileImg = document.createElement("img");

                if( comment.memberProfile != null ){ // 프로필 이미지가 있을 경우
                    profileImg.setAttribute("src", comment.memberProfile);
                }else{ // 없을 경우 == 기본이미지
                    profileImg.setAttribute("src", userDefaultImage);
                }
                profileImg.classList.add("memberProfile");
                
                commentWriterImg.append(profileImg);

                // 작성자 닉네임
                const memberNickname = document.createElement("div");
                memberNickname.innerText = comment.memberNickname;
                memberNickname.classList.add("commentNickname");
                
                // 작성 내용, 날짜
                const div1 = document.createElement("div");
                div1.classList.add("contentContainer");
                
                const boardCommentContent = document.createElement("div");
                boardCommentContent.classList.add("board2-review-content");
                boardCommentContent.innerText = comment.boardCommentContent;

                const boardCommentEnrollDate =document.createElement("div");
                boardCommentEnrollDate.classList.add("board2CommentEnrollDate");
                boardCommentEnrollDate.innerText = comment.boardCommentEnrollDate;


                const csComment = document.createElement("div");
                const csCommentBtn = document.createElement("button");
                csCommentBtn.setAttribute("id", "csBtn");
                csCommentBtn.setAttribute("onclick", `csComment(${comment.boardNo})`);
                csCommentBtn.innerHTML = "신고하기";

                csComment.append(csCommentBtn);

                div1.append(csComment, boardCommentEnrollDate, boardCommentContent);
            
                
                // 로그인이 되어있는 경우 답글 버튼 추가
                if(loginCheck){
                    // 답글 버튼
                    const childCommentBtn = document.createElement("button");
                    childCommentBtn.setAttribute("onclick", "showInsertBoardComment("+comment.boardCommentNo+", this)");
                    childCommentBtn.classList.add("buttons");
                    childCommentBtn.innerText = "답글";

                    // 로그인한 회원번호와 댓글 작성자의 회원번호가 같을 때만 버튼 추가
                    if( loginMemberNo == comment.memberNo   ){

                        // 수정 버튼
                        const updateBtn = document.createElement("button");
                        updateBtn.classList.add("buttons");
                        updateBtn.innerText = "수정";

                        // 수정 버튼에 onclick 이벤트 속성 추가
                        updateBtn.setAttribute("onclick", "showUpdateboardComment("+comment.boardCommentNo+", this)");                        

                        // 삭제 버튼
                        const deleteCommentBtn = document.createElement("button");
                        deleteCommentBtn.classList.add("buttons");
                        deleteCommentBtn.innerText = "삭제";
                        // 삭제 버튼에 onclick 이벤트 속성 추가
                        deleteCommentBtn.setAttribute("onclick", "deleteBoardComment("+comment.boardCommentNo+")");                       

                        div1.append(updateBtn, deleteCommentBtn);

                    } // if 끝
                    
                    div1.append(childCommentBtn)
                }
                commentRow.append(commentWriterImg, memberNickname, div1);
            }
            commentList.append(commentRow);
        }


    })
    .catch(err => console.log(err));

}

// 댓글 등록

const commentEnrollBtn = document.getElementById("commentEnrollBtn");
const commentTextarea = document.getElementById("commentTextarea");

commentEnrollBtn.addEventListener("click", ()=>{
    if(!loginCheck){
        alert("로그인 후 이용해주세요");
        commentTextarea.focus();
        return;
    }
    if(commentTextarea.value.trim().length == 0){
        alert("내용을 입력해주세요");
        commentTextarea.focus();
        return;
    }
    let data = {"boardCommentContent" : commentTextarea.value, "memberNo" : loginMemberNo, "boardNo" : boardNo};
    fetch("/boardComment/enrollComment",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result =>{
        if(result > 0){
            alert("댓글 등록 성공");
            commentTextarea.value = "";

            selectCommentList();
        } else{
            alert("댓글등록실패");
        }
    })
    .catch(e=>console.log(e));
});

function deleteBoardComment(boardCommentNo){

    if(confirm("댓글을 삭제하시겠습니까?")){

        fetch("/boardComment/deleteComment", {
            method : "DELETE",
            headers : {"Content-Type" : "application/json"},
            body : boardCommentNo
        })
        .then(resp => resp.text())
        .then(result=>{
            if(result>0){
                alert("삭제 성공")
                selectCommentList();
            } else {
                alert("삭제 실패");
            }
        })
        .catch(e=>console.log(e));
    }

};


// 댓글 수정

let beforeCommentRow;

function showUpdateboardComment(boardCommentNo, btn){

    // 댓글 수정 하나만 열리게
    const temp = document.getElementsByClassName("updateTextarea");

    if(temp.length > 0){ // 수정이 한 개 이상 열려 있는 경우

        temp[0].parentElement.parentElement.innerHTML = beforeCommentRow;
        // comment-row                       // 백업한 댓글
        // 백업 내용으로 덮어 씌워 지면서 textarea 사라짐
            
    }

    // 1. 댓글 수정을 할 div를 선택
    const commentRow = btn.previousElementSibling;

    // 2. 행 내용 삭제 전 현재 상태를 저장(백업)
    beforeCommentRow = btn.parentElement.parentElement.innerHTML;

    // 3. 댓글에 작성되어 있던 내용만 얻어오기 -> 새롭게 생성된 textarea 추가될 예정
    let beforeContent = btn.previousElementSibling.innerHTML;

    // 4. 댓글 행 내부 내용을 모두 삭제
    commentRow.outerHTML = "";
    
    // 5. textarea 요소 생성 + 클래스 추가  +  **내용 추가**
    const textarea = document.createElement("textarea");
    textarea.classList.add("board2-review-content", "updateTextarea");
    // XSS 방지 처리 해제
    beforeContent =  beforeContent.replaceAll("&amp;", "&");
    beforeContent =  beforeContent.replaceAll("&lt;", "<");
    beforeContent =  beforeContent.replaceAll("&gt;", ">");
    beforeContent =  beforeContent.replaceAll("&quot;", "\"");
    textarea.innerHTML = beforeContent;
    btn.before(textarea);
    textarea.focus();
    textarea.setSelectionRange(textarea.value.length, textarea.value.length);
    
    const newUpateBtn = document.createElement("button");
    newUpateBtn.setAttribute("onclick", "updateboardComment("+boardCommentNo+", this)");
    newUpateBtn.innerText = "수정";
    newUpateBtn.classList.add("buttons");
    
    const cancelBtn = document.createElement("button");
    cancelBtn.setAttribute("onclick", "cencelboardComment(this)");
    cancelBtn.innerText = "취소";
    cancelBtn.classList.add("buttons");
    btn.after(newUpateBtn, cancelBtn);
    btn.remove();
}

function cencelboardComment(btn){
    btn.parentElement.parentElement.innerHTML = beforeCommentRow;
}

function updateboardComment(boardCommentNo, btn){

    const newboardComment = btn.previousElementSibling.value;

    const data = {
        "boardCommentNo" : boardCommentNo,
        "boardCommentContent" : newboardComment
    }

    fetch("/boardComment/updateComment", {
        method : "put",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(resp=>resp.text())
    .then(result=>{
        if(result > 0){
            alert("수정 성공");
            selectCommentList();
        } else {
            alert("수정 실패")
        }
    })
    .catch(e=>console.log(e));
}

function showInsertBoardComment(parentNo, btn){

    const temp = document.getElementsByClassName("board2CommentReply");

    if(temp.length > 0){
        temp[0].parentElement.parentElement.remove()
    }

    const div1 = document.createElement("div");
    div1.classList.add("board2-review-form");

    const div2 = document.createElement("div");
    div2.classList.add("board2CommentInput");
    
    const replyBtn = document.createElement("button");
    replyBtn.innerText = "등록";
    replyBtn.setAttribute("onclick", "insertChildComment("+parentNo+", this)");

    const cancleBtn = document.createElement("button");
    cancleBtn.innerText = "취소";
    cancleBtn.setAttribute("onclick", "insertCancel(this)");

    const textarea = document.createElement("textarea");
    textarea.classList.add("board2CommentReply")
    textarea.classList.add("commentTextarea");
    textarea.setAttribute("cols", "44");
    textarea.setAttribute("rows", "3");

    div2.append(textarea);
    div1.append(div2, cancleBtn, replyBtn);

    btn.parentElement.parentElement.after(div1);
    textarea.focus();
}

function insertCancel(btn){
    btn.parentElement.remove();
}

// 답글 등록
function insertChildComment(parentNo, btn){
    // 부모 댓글 번호, 답글 등록 버튼
    if(!loginCheck){
        alert("로그인 후 이용해주세요");
        return;
    }
    // 답글 내용
    const commentContent = btn.previousElementSibling.previousElementSibling.children[0];
    // 답글 내용이 작성되지 않은 경우
    if(commentContent.value.trim().length == 0){
        alert("답글 작성 후 등록 버튼을 클릭해주세요.");
        commentContent.value = "";
        commentContent.focus();
        return;
    }

    const data = {"boardCommentContent" : commentContent.value,
        "memberNo" : loginMemberNo,
        "boardNo" : boardNo,
        "boardParentNo" : parentNo}; // JS객체

    fetch("/boardComment/enrollComment",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data) // JS객체 -> JSON 파싱
    })
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){ // 등록 성공
            alert("답글 등록 성공");
            selectCommentList();

        } else { // 실패
            alert("답글 등록 실패");
        }
    })
    .catch(e => console.log(e));
}




// /* 신고 팝업창 */
// const csBtn = document.getElementById("csBtn");

// csBtn.addEventListener("click", ()=>{
//   window.open("/board/csCustomer", "_blank", "width=600, height=300, left=700, top=400");
// });

// function csComment(boardNo){
//     window.open("/board/csCustomer", "_blank", "width=600, height=300, left=700, top=400");
// }

// 객체 쿼리스트링 변환
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

/* 신고 팝업창 */

function csComment(memberNo, boardCommentNo, thisComment){
    boards.type = "board";
    boards.reporterNo = memberNo;
    boards.reporterNickname = thisComment.value;
    boards.reportCommentNo = boardCommentNo;
    
    const queryString = objectToQueryString(boards);
    console.log(boards);
    var popup = window.open("/board/csCustomer?" + queryString, "_blank", "width=600, height=300, left=700, top=400");
    if(popup){
        popup.onload = function(){
            
            
        }
    }

}

function submitFrom(popup, reportContent, reportTitle, memberNo){
    if(confirm("제출하시겠습니까?")){
        popup.window.close();
    }
}




