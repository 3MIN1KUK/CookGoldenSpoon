const like = document.getElementById("like");
const bookmark = document.getElementById("bookmark");
if(like != null){
    like.addEventListener("click", e=>{
        if(!loginCheck){
            alert("로그인 후 이용해주세요");
            return;
        }
        
        let check;
    
        if(e.target.previousElementSibling.classList.contains("fa-regular")){
            check = 0;
        } else {
            check = 1;
        }
    
        const dataObj = {"recipeNo" : recipeNo, "check" : check};
    
        fetch("/recipe/like", {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(dataObj)
        })
        .then(resp => resp.text())
        .then( count => {
            if(count == -1){
                alert("좋아요 처리 실패");
                return;
            }
            e.target.previousElementSibling.classList.toggle("fa-regular");
            e.target.previousElementSibling.classList.toggle("fa-solid");
    
        })
        .catch(e=>console.log(e));
    });
}

if(bookmark != null){
    bookmark.addEventListener("click", e=>{
        if(!loginCheck){
            alert("로그인 후 이용해주세요");
            return;
        }
        
        let check;
    
        if(e.target.previousElementSibling.classList.contains("fa-regular")){
            check = 0;
        } else {
            check = 1;
        }
    
        const dataObj = {"recipeNo" : recipeNo, "check" : check};
    
        fetch("/recipe/bookmark", {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(dataObj)
        })
        .then(resp => resp.text())
        .then( count => {
            if(count == -1){
                alert("좋아요 처리 실패");
                return;
            }
            e.target.previousElementSibling.classList.toggle("fa-regular");
            e.target.previousElementSibling.classList.toggle("fa-solid");
    
        })
        .catch(e=>console.log(e));
    });
}

const stars = document.getElementsByClassName("stars");

for(let i = 0; i<stars.length ; i++){
    stars[i].addEventListener("click", e=>{

        if(!loginCheck){
            alert("로그인 후 이용해주세요");
            return;
        }
        const dataObj = {"recipeNo" : recipeNo, "recipeStar" : i+1, "check" : recipeStarCheck};

        fetch("/recipe/stars",{
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(dataObj)
        })
        .then(resp => resp.text())
        .then(result =>{
            if(result > 0){
                for(let u = 0; u<5; u++){
                    if(u <= i){
                        stars[u].classList.remove("fa-regular");
                        stars[u].classList.add("fa-solid");
                    }else{
                        stars[u].classList.remove("fa-solid");
                        stars[u].classList.add("fa-regular");
                    }
                }
            }


        })
        .catch(e=>console.log(e));
    });
}

// 댓글

// 댓글 목록 조회
const selectCommentList = () => {
    // 기본적으로 form태그는 GET/POST만 지원
    
    fetch("/recipeComment?recipeNo="+recipeNo)  // GET방식은 주소에 파라미터를 담아서 전달
    .then(response => response.json()) // 응답 객체 -> 파싱
    .then(cList => { // cList : 댓글 목록(객체 배열)
        // console.log(cList);

        // 화면에 출력되어 있는 댓글 목록 삭제
        const commentList = document.getElementById("commentList");
        commentList.innerHTML = "";

        // cList에 저장된 요소를 하나씩 접근
        for(let comment of cList){

            // 행
            const commentRow = document.createElement("div");
            commentRow.classList.add("recipe-review");

            // 답글일 경우 child-comment 클래스 추가
            if(comment.recipeParentNo != 0)  commentRow.classList.add("child-comment");


            // 삭제된 댓글이지만 자식 댓글 때문에 조회된 경우
            if(comment.recipeCommentDelFl == 'Y') commentRow.innerText = "삭제된 댓글 입니다";

            // 삭제되지 않은 댓글인 경우 (댓글, 버튼 생성)
            else{
                // 작성자
                const commentWriterImg = document.createElement("div");
                commentWriterImg.classList.add("recipe-writer-image");

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
                const div2 = document.createElement("div");
                div2.classList.add("flex");
                
                const recipeCommentContent = document.createElement("div");
                recipeCommentContent.classList.add("recipe-review-content");
                recipeCommentContent.innerText = comment.recipeCommentContent;

                const recipeCommentEnrollDate =document.createElement("div");
                recipeCommentEnrollDate.classList.add("recipeCommentEnrollDate");
                recipeCommentEnrollDate.innerText = comment.recipeCommentEnrollDate;

                div2.append(recipeCommentContent, recipeCommentEnrollDate);
                div1.append(div2);
            
                
                // 로그인이 되어있는 경우 답글 버튼 추가
                if(loginCheck){
                    // 답글 버튼
                    const childCommentBtn = document.createElement("button");
                    childCommentBtn.setAttribute("onclick", "insertRecipeComment("+comment.recipeCommentNo+", this)");
                    childCommentBtn.classList.add("buttons");
                    childCommentBtn.innerText = "답글";

                    // 로그인한 회원번호와 댓글 작성자의 회원번호가 같을 때만 버튼 추가
                    if( loginMemberNo == comment.memberNo   ){

                        // 수정 버튼
                        const updateBtn = document.createElement("button");
                        updateBtn.classList.add("buttons");
                        updateBtn.innerText = "수정";

                        // 수정 버튼에 onclick 이벤트 속성 추가
                        updateBtn.setAttribute("onclick", "updateRecipeComment("+comment.recipeCommentNo+", this)");                        


                        // 삭제 버튼
                        const deleteBtn = document.createElement("button");
                        deleteBtn.classList.add("buttons");
                        deleteBtn.innerText = "삭제";
                        // 삭제 버튼에 onclick 이벤트 속성 추가
                        deleteBtn.setAttribute("onclick", "deleteRecipeComment("+comment.recipeCommentNo+")");                       

                        div1.append(updateBtn, deleteBtn);

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



const commentEnrollBtn = document.getElementById("commentEnrollBtn");
const commentTextarea = document.getElementById("commentTextarea");

commentEnrollBtn.addEventListener("click", ()=>{
    if(!loginCheck){
        alert("로그인 후 이용해주세요");
        return;
    }
    if(commentTextarea.value.trim().length == 0){
        alert("내용을 입력해주세요");
        return;
    }
    let data = {"recipeCommentContent" : commentTextarea.value, "memberNo" : loginMemberNo, "recipeNo" : recipeNo};
    console.log(data);
    fetch("/recipeComment/enrollComment",{
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