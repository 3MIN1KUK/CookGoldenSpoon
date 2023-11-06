const inputSearch = document.getElementById("inputSearch");

/* 검색 */
inputSearch.addEventListener('keyup', e=>{

  if(e.key == "Enter"){
    location.href = "/recipe/select/search?inputSearch=" + e.target.value;
  }
});

// const recipeLeftBtn = document.querySelector(".recipeLeftBtn");
// const recipeRightBtn = document.querySelector(".recipeRightBtn");

// recipeLeftBtn.addEventListener("click", e=>{
//   fetch()
//   .
// });
