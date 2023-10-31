const inputSearch = document.getElementById("inputSearch");

/* 검색 */
inputSearch.addEventListener('keyup', e=>{

  if(e.key == "Enter"){
    location.href = "/recipe/select/search?inputSearch=" + e.target.value;
  }
});