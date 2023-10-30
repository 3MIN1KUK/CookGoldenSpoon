const recipeSearchBtn = document.getElementById("recipeSearchBtn");
const recipeSearch = document.getElementById("recipeSearch");
recipeSearchBtn.addEventListener("click", ()=>{
    location.href = "/recipe/select/search?inputSearch=" + recipeSearch.value;
});
recipeSearch.addEventListener("keyup", e=>{
    if(e.key == "Enter"){
        location.href = "/recipe/select/search?inputSearch=" + recipeSearch.value;
    }
});