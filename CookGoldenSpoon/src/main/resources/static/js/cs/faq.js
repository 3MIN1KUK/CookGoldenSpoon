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
