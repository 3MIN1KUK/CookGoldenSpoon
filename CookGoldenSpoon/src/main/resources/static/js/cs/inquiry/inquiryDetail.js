/* 문의사항 답글 등록 */
const replyBtn = document.getElementById("replyBtn");
if(replyBtn != null){
   replyBtn.addEventListener('click', () => {
      let url = `/editInquiry/${inquiryNo}/update`;
      location.href = url;
   });
}


const goToBtn = document.getElementById("goToBtn");
if(goToBtn != null){

   const goToFn = () => {
      const paramMap = new URL(location.href).searchParams;
      const obj = {};
      // http://localhost/cs/inquiry/24
      // http://localhost/cs/inquiry?cp=1
      obj.cp = paramMap.get("cp");
      obj.key = paramMap.get("key");
      obj.query = paramMap.get("query");
      const tempParams = new URLSearchParams();
      for(let key in obj){ // 객체 전용 향상된 for문
         if(obj[key] != null) tempParams.append(key, obj[key]);
      }
      location.href = `/cs/inquiry?${paramMap.toString()}`;
   }
   goToBtn.addEventListener("click", goToFn);
}
