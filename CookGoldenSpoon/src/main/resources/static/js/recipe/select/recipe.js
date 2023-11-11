

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