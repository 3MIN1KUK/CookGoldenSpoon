const insertBtn = document.getElementById("insertBtn");

if(insertBtn != null){
   insertBtn.addEventListener('click', () => {
      location.href = `/editInquiry/insert`;
   });
}