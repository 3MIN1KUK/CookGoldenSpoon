

// 레시피 검색
const recipeSearchBtn = document.getElementById("recipeSearchBtn");
const recipeSearch = document.getElementById("recipeSearch");
const orderBy = document.getElementById("orderBy");

recipeSearchBtn.addEventListener("click", ()=>{
    location.href = "/recipe/select/search?inputSearch=" + recipeSearch.value + "&orderBy=" + orderBy.value;
});
recipeSearch.addEventListener("keyup", e=>{
    if(e.key == "Enter"){
        location.href = "/recipe/select/search?inputSearch=" + recipeSearch.value + "&orderBy=" + orderBy.value;
    }
});

// 검색창에 이전 검색 기록 남겨두기
const options = document.querySelectorAll("#orderBy>option");
const searchQuery = document.getElementById("recipeSearch");

// 즉시 실행 함수 (해석되자 마자 실행되는 함수, 속도가 빠름)
(()=>{
  // 주소에 있는 파라미터(쿼리스트링) 얻어오기
  const params = new URL(location.href).searchParams;
  const orderBy = params.get("orderBy"); // 1,2,3,4,5 중 하나
  const inputSearch = params.get("inputSearch"); // 검색어

  // 검색을 했을 경우
  if(orderBy != null && inputSearch != null){
    searchQuery.value = inputSearch; // 검색어를 input에 추가
    for(let op of options){
      if(op.value == orderBy){
        op.selected = true;
        break;
      }
    }
  }
})();