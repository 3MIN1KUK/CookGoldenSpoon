// 작성하기
const insertBtn = document.querySelector("#insertBtn");
if(insertBtn != null){
   insertBtn.addEventListener('click', () => {
      location.href = `/editBoard/${boardCode}/insert`;
   });
}

// -----------------------------------------------------------------------------

// 게시판 검색
// const searchBoardBtn = document.getElementById("searchBoardBtn");
// const searchBoard = document.getElementById("searchBoard");
// const searchKey = document.getElementById("searchKey");

// searchBoardBtn.addEventListener("click", () => {
//    location.href ="/board/${boardCode}?key=" + searchKey.value + "%query=" + searchBoard.value;
// });
// searchBoard.addEventListener("keyup", e=>{
//    if(e.key == "Enter"){
//       location.href ="/board/${boardCode}?key=" + searchKey.value + "%query=" + searchBoard.value;
//    }
// });

// 검색창 이전 기록 남겨두기
const options = document.querySelectorAll("#searchKey>option");
const searchQuery = document.getElementById("searchBoard");

(() => {
   const params = new URL(location.href).searchParams;

   const key = params.get("key");
   const query = params.get("query");
   console.log(options);
   if(key != null && query != null){
      searchQuery.value = query;

      for(let op of options){
         if(op.value == key){
            op.selected = true;
            break;
         }
      }
   }
})();
