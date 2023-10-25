const inputSearch = document.getElementById("inputSearch");

/* 검색 */
inputSearch.addEventListener('keyup', e=>{

  if(e.key == "Enter"){
    console.log(e.target.value);
    location.href = "/recipe/search?inputSearch=" + e.target.value;
  }
});