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

function createCommentList(){
  const commentArea = document.querySelector('#comment-area');
  commentArea.innerHTML = '';
  const queryString = objectToQueryString(searchComment);

  fetch("/admin/createCommentList?" + queryString,{
    method : "GET",
    headers : {"Content-type" : "application/json"}
  })
  .then(res => res.json())
  .then(result =>{
    console.log(result);
    commentList = result.commentList;

    for(let comment of commentList){

      const insertComment = document.createElement('div');
      insertComment.classList.add('comment-out-box')

      const commentTitleArea = document.createElement('div');

      const commentMemberNickname = document.createElement('span');
      commentMemberNickname.classList.add('comment-in-text');
      commentMemberNickname.innerHTML = comment.memberNickname;

      const commentParentTitle = document.createElement('span');
      commentParentTitle.classList.add('comment-in-text');
      commentParentTitle.innerHTML = comment.commentParentTitle;

      const commentEnrollDate = document.createElement('span');
      commentEnrollDate.classList.add('comment-in-text');
      commentEnrollDate.innerHTML = comment.commentEnrollDate;

      const commentType = document.createElement('span');
      commentType.classList.add('comment-in-text');
      commentType.innerHTML = comment.commentType;

      const commentDeleteBtn = document.createElement('button');
      commentDeleteBtn.classList.add('delete-btn', 'btn', 'btn-outline-danger', 'float-end');
      commentDeleteBtn.setAttribute('onclick', `deleteComment(${comment.commentNo}, this)` );
      commentDeleteBtn.setAttribute('value', comment.commentType );
      commentDeleteBtn.innerHTML = '삭제';

      commentTitleArea.append(commentMemberNickname, commentParentTitle, commentEnrollDate, commentType, commentDeleteBtn);

      const commentContent = document.createElement('div');
      commentContent.classList.add('comment-content');
      commentContent.innerHTML = comment.commentContent;

      insertComment.append(commentTitleArea, commentContent);

      commentArea.append(insertComment);

    }
  })
  .catch(err => console.log(err));

}

function deleteComment(commentNo, thisComment){
  if(confirm("해당 댓글을 삭제하시겠습니까?")){

    const data = {
      "commentNo": commentNo,
      "commentType": thisComment.value
    }

    console.log(data);
    
    fetch("/admin/commentDelete",{
      method : "PUT",
      headers : {"Content-type" : "application/json"},
      body : JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result =>{
      if (result > 0){
        alert("댓글이 삭제되었습니다");
        thisComment.parentElement.parentElement.remove();
        createCommentList();
      }
      else{
        alert("삭제 실패");
      }
    })
    .catch(err => console.log(err));

  }
}