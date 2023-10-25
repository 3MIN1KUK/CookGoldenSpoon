/* QnA 질문 */
const qnaQ = document.querySelectorAll('.qna-q');
for(let i = 0 ; i < qnaQ.length ; i++){
  qnaQ[i].addEventListener('click',e=>{
       if(e.target.nextElementSibling.classList.contains("qna-a-act")){
            e.target.nextElementSibling.classList.remove('qna-a-act');
       } else{
            e.target.nextElementSibling.classList.add('qna-a-act');
       }
  });
}

/* 공지사항 검색 */
const searchNoticeBtn = document.getElementById("searchNoticeBtn");
const searchNotice = document.getElementById("searchNotice");
const noticeContainer = document.querySelector(".content-container");
searchNoticeBtn.addEventListener("click", ()=>{
     if(searchNotice.value.trim().length == 0){
          alert("검색어를 입력해주세요");
          return;
     }
     noticeContainer.innerHTML = "";
     fetch("/cs/notice/select?searchNotice=" + searchNotice.value)
     .then(resp => resp.json())
     .then(noticeList => {
          if(noticeList.length == 0){
               alert("검색 결과가 없습니다.");
               return;
          }
          for(let notice of noticeList){
               const div = document.createElement("div");
               div.classList.add("notice");
               const span1 = document.createElement("span");
               const span2 = document.createElement("span");
               const span3 = document.createElement("span");
               const span4 = document.createElement("span");
               const span5 = document.createElement("span");
               span1.innerText = notice.noticeNo;
               span2.innerText = notice.noticeTitle;
               span3.innerText = notice.noticeWriter;
               span4.innerText = notice.enrollDate;
               span5.innerText = notice.hits;
               div.append(span1, span2, span3, span4, span5);
               noticeContainer.append(div);
          }

     })
     .catch(e=>console.log(e));
});

