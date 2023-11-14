// 작성하기
const insertBtn = document.querySelector("#insertBtn");
if(insertBtn != null){
   insertBtn.addEventListener('click', () => {
      location.href = `/editBoard/${boardCode}/insert`;
   });
}

// -----------------------------------------------------------------------------

// 검색창
const options = document.querySelectorAll("#searchkey > option");
const searchQuery = document.getElementById("searchBoard");

(() => {
   const params = new URL(location.href).searchParams;

   const key = params.get("key");
   const query = params.get("query");

   if(key != null && query != null){
      searchQuery.value = query;

      for(let op of options){
         op.selected = true;
         break;
      }
   }
})();
