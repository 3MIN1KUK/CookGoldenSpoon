
const searchNotice = document.getElementById("searchNotice");
const searchNoticeBtn = document.getElementById("searchNoticeBtn");

const searchFn = ()=>{
    location.href = "/myPage/board/search?inputSearch=" + searchNotice.value;    
}

searchNotice.addEventListener("keyup", e=>{
    if(e.key == "Enter"){
        searchFn ();
    }
});

searchNoticeBtn.addEventListener("click", searchFn);

(()=>{
    const params = new URL(location.href).searchParams;
    const inputSearch = params.get("inputSearch");

    if(inputSearch != null){
        searchNotice.value = inputSearch;
    }
})();